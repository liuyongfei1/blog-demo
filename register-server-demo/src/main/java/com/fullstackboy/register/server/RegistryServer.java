package com.fullstackboy.register.server;

import java.util.UUID;

/**
 * 模拟服务注册中心
 *
 * 它会启动一个web服务器，可以把controller都部署到web服务器里
 *
 * @author Liuyongfei
 * @date 2021/8/16 23:23
 */
public class RegistryServer {
    public static void main(String[] args) {

        RegisterServerController controller = new RegisterServerController();

        String serverInstanceId = UUID.randomUUID().toString().replace("-","");

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setServiceName("inventory-service");
        registerRequest.setServerInstanceId(serverInstanceId);
        registerRequest.setHostName("inventory-01");
        registerRequest.setIp("192.168.10.12");
        registerRequest.setPort(9000);

        // 模拟服务注册请求
        RegisterResponse registerResponse = controller.register(registerRequest);

        // 模拟一次心跳，完成续约
        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        registerRequest.setServiceName("inventory-service");
        registerRequest.setServerInstanceId(serverInstanceId);
        HeartbeatResponse heartbeatResponse = controller.heartbeat(heartbeatRequest);

        // 开启一个后台线程，检测服务实例的存活状态
        new ServiceAliveMonitor().start();

    }
}
