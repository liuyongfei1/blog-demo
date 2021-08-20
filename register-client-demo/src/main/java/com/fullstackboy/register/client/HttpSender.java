package com.fullstackboy.register.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送各种请求
 *
 * @author Liuyongfei
 * @date 2021/8/15 17:54
 */
public class HttpSender {

    /**
     * 发送注册请求
     * @param request 请求参数
     * @return 响应
     */
    public RegisterResponse register(RegisterRequest request) {
        System.out.println("服务实例【" + request + "】开始发送注册请求");
        RegisterResponse response = new RegisterResponse();
        response.setStatus(RegisterResponse.SUCCESS);
        return response;
    }

    /**
     * 发送心跳请求
     * @param request 请求参数
     * @return 响应
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest request) {
        System.out.println("服务实例【" + request.getServerInstanceId() + "】开始发送心跳请求");
        HeartbeatResponse response = new HeartbeatResponse();
        response.setStatus(HeartbeatResponse.SUCCESS);
        return response;
    }

    /**
     * 拉取注册表
     * 没有学习到网路请求，所以这里暂时模拟数据
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> fetchServiceRegistry() {
        Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();

        ServiceInstance serviceInstance = new ServiceInstance();
        serviceInstance.setHostName("finance-service-01");
        serviceInstance.setIp("192.168.10.111");
        serviceInstance.setPort(9000);
        serviceInstance.setServiceInstanceId("FINANCE-SERVICE-asdaadfasjjljjl");
        serviceInstance.setServiceName("FINANCE-SERVICE");

        Map<String,ServiceInstance> serviceInstances = new HashMap<>();
        serviceInstances.put("FINANCE-SERVICE-asdaadfasjjljjl", serviceInstance);

        registry.put("FINANCE-SERVICE", serviceInstances);

        System.out.println("拉取注册表：" +registry);
        return registry;
    }

    /**
     * 服务下线
     * @param serviceName 服务名称
     * @param serviceInstanceId 服务实例id
     */
    public void cancel(String serviceName,String serviceInstanceId) {
        System.out.println("服务实例下线：【" + serviceName + "," + serviceInstanceId + "】");
    }
}
