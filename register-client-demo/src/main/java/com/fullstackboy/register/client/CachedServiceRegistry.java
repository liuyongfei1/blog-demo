package com.fullstackboy.register.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
	private Map<String, Map<String, ServiceInstance>> registry = 
			new HashMap<String, Map<String, ServiceInstance>>();
	/**
	 * 负责定时拉取注册表到客户端进行缓存的后台线程
	 */
	private FetchDeltaRegistryWorker fetchDeltaRegistryWorker;
	/**
	 * com.fullstackboy.register.client.RegisterClient
	 */
	private RegisterClient registerClient;
	/**
	 * http通信组件
	 */
	private HttpSender httpSender;
	
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
			registry = httpSender.fetchServiceRegistry();
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
//					registry = httpSender.fetchServiceRegistry();
					Thread.sleep(SERVICE_REGISTRY_FETCH_INTERVAL);

					// 拉取最近3分钟有变化的服务实例
					LinkedList<RecentlyChangedServiceInstance> deltaRegistry = httpSender.fetchDeltaServiceRegistry();

					// 一类是注册，一类是删除
					// 这里其实是要大量的修改本地缓存的注册表，所以这里使用了synchronized加锁。
					synchronized (registry) {
						mergeDeltaRegistry(deltaRegistry);
					}

				} catch (Exception e) {
					e.printStackTrace();  
				}
			}
		}
		
	}

	/**
	 * 合并增量注册表到本地缓存注册表中去
	 * @param deltaRegistry
	 */
	private void mergeDeltaRegistry(LinkedList<RecentlyChangedServiceInstance> deltaRegistry) {

		for (RecentlyChangedServiceInstance changeItem : deltaRegistry) {
			Map<String, ServiceInstance> serviceInstanceMap = registry.get(changeItem.serviceInstance.getServiceName());
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
	
	/**
	 * 获取服务注册表
	 * @return
	 */
	public Map<String, Map<String, ServiceInstance>> getRegistry() {
		return registry;
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
