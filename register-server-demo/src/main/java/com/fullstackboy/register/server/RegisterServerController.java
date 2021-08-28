package com.fullstackboy.register.server;

import java.util.LinkedList;
import java.util.Map;

/**
 * 这里接收register-client发送过来的http请求
 * 在Spring Cloud Eureka中，使用的是 jersey，spring mvc， restful框架，在国外用的比较多
 * @author Liuyongfei
 * @date 2021/8/16 22:48
 */
public class RegisterServerController {

    /**
     * 注册表
     */
    private ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();

    /**
     * 注册表缓存
     */
    private ServiceRegistryCache serviceRegistryCache = ServiceRegistryCache.getInstance();


    /**
     * 接收服务注册请求
     * @param registerRequest 服务注册请求
     * @return 响应
     */
    public RegisterResponse register(RegisterRequest registerRequest) {
        RegisterResponse response = new RegisterResponse();

        try {
            ServiceInstance instance = new ServiceInstance();
            instance.setServiceName(registerRequest.getServiceName());
            instance.setServiceInstanceId(registerRequest.getServerInstanceId());
            instance.setHostName(registerRequest.getHostName());
            instance.setIp(registerRequest.getIp());
            instance.setPort(registerRequest.getPort());

            serviceRegistry.register(instance);

            // 更新自我保护机制的阈值
            synchronized (SelfProtectionPolicy.class) {
                SelfProtectionPolicy policy = SelfProtectionPolicy.getInstance();
                policy.setExpectedHeartbeatRate(policy.getExpectedHeartbeatRate() + 2);
                policy.setExpectedHeartbeatThreshold((long) (policy.getExpectedHeartbeatRate() * 0.85));
            }

            // 过期掉服务注册表中的缓存
            serviceRegistryCache.invalidate();
            response.setStatus(RegisterResponse.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(RegisterResponse.FAILURE);
        }
        return response;
    }

    /**
     * 接收心跳请求
     * @param heartbeatRequest 心跳请求
     * @return 响应
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest) {
        HeartbeatResponse response = new HeartbeatResponse();

        try {
            // 获取服务的实例信息
            ServiceInstance serviceInstance = serviceRegistry.getServiceInstance(heartbeatRequest.getServiceName(),
                    heartbeatRequest.getServerInstanceId());

            // 服务续约
            serviceInstance.renew();

            // 记录每分钟的心跳次数
            HeartbeatCounter rate = new HeartbeatCounter();
            rate.increment();

            response.setStatus(HeartbeatResponse.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HeartbeatResponse.FAILURE);
        }
        return response;
    }

    /**
     * 服务下线
     * @param serviceName 服务名称
     * @param serviceInstanceId 服务实例id
     */
    public void cancel(String serviceName, String serviceInstanceId) {
        serviceRegistry.remove(serviceName, serviceInstanceId);

        // 更新自我保护机制的阈值
        synchronized (SelfProtectionPolicy.class) {
            SelfProtectionPolicy policy = SelfProtectionPolicy.getInstance();
            policy.setExpectedHeartbeatRate(policy.getExpectedHeartbeatRate() + 2);
            policy.setExpectedHeartbeatThreshold((long) (policy.getExpectedHeartbeatRate() * 0.85));
        }

        // 过期掉服务注册表中的缓存
        serviceRegistryCache.invalidate();
    }

    /**
     * 拉取全量注册表
     * 优化为：优先从缓存中读取全量注册表
     * @return 全量注册表
     */
//    public Map<String, Map<String, ServiceInstance>> fetchFullRegistry() {
////        return serviceRegistry.getRegistry();
//        return serviceRegistryCache.get(ServiceRegistryCache.CacheKey.FULL_SERVICE_REGISTRY);
//    }
    public Applications fetchFullRegistry() {
        return (Applications) serviceRegistryCache.get(ServiceRegistryCache.CacheKey.FULL_SERVICE_REGISTRY);
    }

    /**
     * 拉取增量注册表
     * 优化为：优先从缓存中读取增量注册表
     * @return 增量注册表
     */
//    public LinkedList<ServiceRegistry.RecentlyChangedServiceInstance> fetchRecentlyChangedQueue() {
//        return serviceRegistry.getRecentlyChangedQueue();
//    }
    public DeltaRegistry fetchRecentlyChangedQueue() {
        return (DeltaRegistry) serviceRegistryCache.get(ServiceRegistryCache.CacheKey.DELTA_SERVICE_REGISTRY);
    }
}
