package com.fullstackboy.register.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 服务注册中心的客户端缓存的一个服务注册表
 * @author Liuyongfei
 *
 */
public class CachedServiceRegistry {
	
	/**
	 * 服务注册表拉取间隔时间
	 */
	private static final Long SERVICE_REGISTRY_FETCH_INTERVAL = 30 * 1000L;

	/**
	 * 客户端缓存的服务注册表
	 */
//	private Map<String, Map<String, ServiceInstance>> registry =
//			new HashMap<String, Map<String, ServiceInstance>>();
	/**
	 * 负责定时拉取注册表到客户端进行缓存的后台线程
	 */
	private FetchDeltaRegistryWorker fetchDeltaRegistryWorker;
	/**
	 * RegisterClient
	 */
	private RegisterClient registerClient;
	/**
	 * http通信组件
	 */
	private HttpSender httpSender;

	/**
	 * 客户端缓存的所有的实例信息
	 */
	private AtomicStampedReference<Applications> applications;
	
	/**
	 * 构造函数
	 * @param registerClient
	 * @param httpSender
	 */
	public CachedServiceRegistry(
			RegisterClient registerClient,
			HttpSender httpSender) {
		this.fetchDeltaRegistryWorker = new FetchDeltaRegistryWorker();
		this.registerClient = registerClient;
		this.httpSender = httpSender;
		this.applications = new AtomicStampedReference<>(new Applications(), 0);
	}
	
	/**
	 * 初始化
	 */
	public void initialize() {
		// 启动全量拉取注册表的线程
		FetchFullRegistryWorker fetchFullRegistryWorker = 
				new FetchFullRegistryWorker();
		fetchFullRegistryWorker.start();  
		// 启动增量拉取注册表的线程
		this.fetchDeltaRegistryWorker.start();
	}
	  
	/**
	 * 销毁这个组件
	 */
	public void destroy() {
		this.fetchDeltaRegistryWorker.interrupt();
	}
	
	/**
	 * 全量拉取注册表的后台线程
	 * @author Liuyongfei
	 *
	 */
	private class FetchFullRegistryWorker extends Thread {
		
		@Override
		public void run() {
			Applications fetchedApplications = httpSender.fetchFullRegistry();

			while (true) {
				Applications expectedApplications = applications.getReference();
				int expectedStamp = applications.getStamp();
				if (applications.compareAndSet(expectedApplications, fetchedApplications, expectedStamp,
						expectedStamp + 1)) {
					break;
				}
			}

		}
		
	}
	
	/**
	 * 增量拉取注册表的后台线程
	 * @author Liuyongfei
	 *
	 */
	private class FetchDeltaRegistryWorker extends Thread {
		
		@Override
		public void run() {
			while(registerClient.isRunning()) {  
				try {
					Thread.sleep(SERVICE_REGISTRY_FETCH_INTERVAL);

					// 拉取最近3分钟有变化的服务实例
					DeltaRegistry deltaRegistry = httpSender.fetchDeltaServiceRegistry();

					// 一类是注册，一类是删除
					// 这里其实是要大量的修改本地缓存的注册表，所以这里使用了synchronized加锁。
//					synchronized (registry) {
						mergeDeltaRegistry(deltaRegistry);
//					}

					// 再检查一下服务端的注册表数量与客户端的是否一致
					// 封装一下增量注册表对象，也就是增量拉取注册表的时候，同时返回服务端的注册表数量
					reconcileRegistry(deltaRegistry);
				} catch (Exception e) {
					e.printStackTrace();  
				}
			}
		}
		
	}

	/**
	 * 合并增量注册表到本地缓存注册表中去
	 * @param deltaRegistry 拉取到的有变动的注册表
	 */
	private void mergeDeltaRegistry(DeltaRegistry deltaRegistry) {
		synchronized (applications) {
			Map<String,Map<String, ServiceInstance>> registry = applications.getReference().getRegistry();
			LinkedList<RecentlyChangedServiceInstance> recentlyChangedQueue = deltaRegistry.getRecentlyChangedQueue();

			for (RecentlyChangedServiceInstance changeItem : recentlyChangedQueue) {
				String serviceName = changeItem.serviceInstance.getServiceName();
				String serviceInstanceId = changeItem.serviceInstance.getServiceInstanceId();

				Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
				// 如果是注册操作
				if (changeItem.serviceInstanceOperation.equals(ServiceInstanceOperation.REGISTER)) {
					if (serviceInstanceMap == null) {
						serviceInstanceMap = new HashMap<>();
						registry.put(changeItem.serviceInstance.getServiceName(), serviceInstanceMap);
					}

					ServiceInstance serviceInstance =
							serviceInstanceMap.get(changeItem.serviceInstance.getServiceInstanceId());

					if (serviceInstance == null) {
						serviceInstanceMap.put(
								changeItem.serviceInstance.getServiceInstanceId(),
								changeItem.serviceInstance);
					}
				}
				// 如果是删除操作
				else if (changeItem.serviceInstanceOperation.equals(ServiceInstanceOperation.REMOVE)) {
					if (serviceInstanceMap != null) {
						serviceInstanceMap.remove(changeItem.serviceInstance.getServiceInstanceId());
					}
				}
			}
		}
	}

	/**
	 * 校对调整注册表
	 * @param deltaRegistry 拉取到的有变动的注册表
	 */
	private void reconcileRegistry(DeltaRegistry deltaRegistry) {
//		synchronized (applications) {
			Map<String, Map<String,ServiceInstance>> registry = applications.getReference().getRegistry();

			long serviceInstanceTotalCount = deltaRegistry.getServiceInstanceTotalCount();
			long clientSideTotalCount = 0L;

			for(Map<String,ServiceInstance> serviceInstanceMap : registry.values()) {
				clientSideTotalCount += serviceInstanceMap.size();
			}

			if (serviceInstanceTotalCount != clientSideTotalCount) {
				// 重新拉取全量注册表进行纠正
				Applications fetchedApplications = httpSender.fetchFullRegistry();
				while (true) {
					Applications expectedApplications = applications.getReference();
					int expectedStamp = applications.getStamp();
					if (applications.compareAndSet(expectedApplications, fetchedApplications, expectedStamp,
							expectedStamp + 1)) {
						break;
					}
				}
			}
//		}
	}
	
	/**
	 * 获取服务注册表
	 * @return
	 */
	public Map<String, Map<String, ServiceInstance>> getRegistry() {
		return applications.getReference().getRegistry();
	}

	/**
	 * 服务实例操作类型
	 */
	class ServiceInstanceOperation {
		private static final String REGISTER = "REGISTER";
		private static final String REMOVE = "REMOVE";
	}
	
	/**
	 * 最近变更的实例信息
	 * @author Liuyongfei
	 *
	 */
	static class RecentlyChangedServiceInstance {
		
		/**
		 * 服务实例
		 */
		ServiceInstance serviceInstance;
		/**
		 * 发生变更的时间戳
		 */
		Long changedTimestamp;
		/**
		 * 变更操作
		 */
		String serviceInstanceOperation;
		
		public RecentlyChangedServiceInstance(
				ServiceInstance serviceInstance, 
				Long changedTimestamp,
				String serviceInstanceOperation) {
			this.serviceInstance = serviceInstance;
			this.changedTimestamp = changedTimestamp;
			this.serviceInstanceOperation = serviceInstanceOperation;
		}

		@Override
		public String toString() {
			return "RecentlyChangedServiceInstance [serviceInstance=" + serviceInstance + ", changedTimestamp="
					+ changedTimestamp + ", serviceInstanceOperation=" + serviceInstanceOperation + "]";
		}
		
	}
	
}
