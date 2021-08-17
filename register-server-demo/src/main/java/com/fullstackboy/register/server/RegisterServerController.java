package com.fullstackboy.register.server;

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
    private Registry registry = Registry.getInstance();

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
            registry.register(instance);

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
            ServiceInstance serviceInstance = registry.getServiceInstance(heartbeatRequest.getServerName(),
                    heartbeatRequest.getServerInstanceId());

            // 服务续约
            serviceInstance.renew();

            response.setStatus(HeartbeatResponse.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HeartbeatResponse.FAILURE);
        }
        return response;
    }
}
