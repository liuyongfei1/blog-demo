package com.fullstackboy.register.server;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册表
 *
 * @author Liuyongfei
 * @date 2021/8/16 22:10
 */
public class Registry {

    private static final Registry instance = new Registry();

    private Registry() {

    }

    /**
     * 注册表
     */
    private Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();

    /**
     * 服务注册
     */
    public void register(ServiceInstance instance) {
        Map<String, ServiceInstance> instanceMap = registry.get(instance.getServiceName());
        if (instanceMap == null) {
            instanceMap = new HashMap<>();
            registry.put(instance.getServiceName(), instanceMap);
        }

        instanceMap.put(instance.getServiceInstanceId(),instance);

        System.out.println("服务实例【" + instance + "】，完成注册");
        System.out.println("注册表：" + registry);

        // ???
//        ServiceInstance instance = new ServiceInstance();
//        instance.setHostName(registerRequest.getHostName());
//        instance.setIp(registerRequest.getIp());
//        instance.setServiceName(registerRequest.getServiceName());
//        // Lease???
//        instance.setLease();
//        instanceMap.put(registerRequest.getServerInstanceId(),instance);
//
//        registry.put(registerRequest.getServiceName(), instanceMap);
    }

    /**
     * 获取实例信息
     * @param serviceName
     * @param serverInstanceId
     * @return ServiceInstance
     */
    public ServiceInstance getServiceInstance(String serviceName, String serverInstanceId) {
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        return serviceInstanceMap.get(serverInstanceId);
    }

    /**
     * 服务摘除
     * @param serviceName 服务名称
     * @param serverInstanceId 服务实例id
     * @author Liuyongfei
     * @date 2021/8/16 下午10:40
     */
    public void remove(String serviceName, String serverInstanceId) {
        // 获取注册表
        Map<String,ServiceInstance> serverInstanceMap = registry.get(serviceName);
        serverInstanceMap.remove(serverInstanceId);

        System.out.println("服务实例【" + serverInstanceId + "】被摘除");
    }

    public static Registry getInstance() {
        return instance;
    }

    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }
}
