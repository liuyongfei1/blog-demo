package com.fullstackboy.mybatis.register.client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 负责发送各种http请求的组件
 * @author Liuyongfei
 *
 */
public class HttpSender {

	/**
	 * 发送注册请求
	 * @param request
	 * @return
	 */
	public RegisterResponse register(RegisterRequest request) { 
		// 实际上会基于类似HttpClient这种开源的网络包
		// 你可以去构造一个请求，里面放入这个服务实例的信息，比如服务名称，ip地址，端口号
		// 然后通过这个请求发送过去
		System.out.println("服务实例【" + request + "】，发送请求进行注册......");  
		
		// 收到register-server响应之后，封装一个Response对象
		RegisterResponse response = new RegisterResponse();
		response.setStatus(RegisterResponse.SUCCESS); 
		
		return response;
	}
	
	/**
	 * 发送心跳请求
	 * @param request
	 * @return
	 */
	public HeartbeatResponse heartbeat(HeartbeatRequest request) { 
		System.out.println("服务实例【" + request + "】，发送请求进行心跳......");  
		
		HeartbeatResponse response = new HeartbeatResponse();
		response.setStatus(RegisterResponse.SUCCESS); 
		
		return response;
	}
	
	/**
	 * 全量拉取服务注册表
	 * @return
	 */
	public Applications fetchFullRegistry() {
		Map<String, Map<String, ServiceInstance>> registry = 
				new HashMap<String, Map<String, ServiceInstance>>();
		
		ServiceInstance serviceInstance = new ServiceInstance();
		serviceInstance.setHostname("finance-service-01");  
		serviceInstance.setIp("192.168.31.1207");  
		serviceInstance.setPort(9000);  
		serviceInstance.setServiceInstanceId("FINANCE-SERVICE-192.168.31.207:9000");  
		serviceInstance.setServiceName("FINANCE-SERVICE");  
		
		Map<String, ServiceInstance> serviceInstances = new HashMap<String, ServiceInstance>();
		serviceInstances.put("FINANCE-SERVICE-192.168.31.207:9000", serviceInstance);   
		
		registry.put("FINANCE-SERVICE", serviceInstances);
		
		System.out.println("拉取注册表：" + registry);  
		
		return new Applications(registry);
	}
	
	/**
	 * 增量拉取服务注册表
	 * @return
	 */
	public DeltaRegistry fetchDeltaServiceRegistry() {
		ConcurrentLinkedQueue<CachedServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue =
				new ConcurrentLinkedQueue<CachedServiceRegistry.RecentlyChangedServiceInstance>();
		
		ServiceInstance serviceInstance = new ServiceInstance();
		serviceInstance.setHostname("order-service-01");  
		serviceInstance.setIp("192.168.31.288");  
		serviceInstance.setPort(9000);  
		serviceInstance.setServiceInstanceId("ORDER-SERVICE-192.168.31.288:9000");  
		serviceInstance.setServiceName("ORDER-SERVICE");  
		
		CachedServiceRegistry.RecentlyChangedServiceInstance recentlyChangedItem = new CachedServiceRegistry.RecentlyChangedServiceInstance(
				serviceInstance,
				System.currentTimeMillis(),
				"register");  
		
		recentlyChangedQueue.add(recentlyChangedItem);
		
		System.out.println("拉取增量注册表：" + recentlyChangedQueue);
		DeltaRegistry deltaRegistry = new DeltaRegistry(recentlyChangedQueue, 2L);

		return deltaRegistry;
	}
	
	/**
	 * 服务下线
	 * @param serviceName 服务名称
	 * @param serviceInstanceId 服务实例id
	 */
	public void cancel(String serviceName, String serviceInstanceId) {
		System.out.println("服务实例下线【" + serviceName + ", " + serviceInstanceId + "】");  
	}
	
}
