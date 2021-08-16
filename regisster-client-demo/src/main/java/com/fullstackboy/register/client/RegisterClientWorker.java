package com.fullstackboy.register.client;

import java.util.UUID;

/**
 * 微服务注册-client端
 * 可以打成一个jar包并上传到私服，供其他服务调用，负责跟注册中心进行通信
 *
 * @author Liuyongfei
 * @date 2021/8/15 17:53
 */
public class RegisterClientWorker extends Thread {

    public static final String SERVICE_NAME = "inventory-service";
    public static final String IP = "192.168.31.207";
    public static final String HOST_NAME = "inventory01";
    public static final int PORT = 9000;

    /**
     * http通信组件
     */
    private HttpSender httpSender;

    /**
     * 是否完成服务注册
     */
    private Boolean finishedRegister;

    /**
     * 服务实例id
     */
    private String serviceInstanceId;

    public RegisterClientWorker(String serviceInstanceId) {
        this.httpSender = new HttpSender();
        this.finishedRegister = false;
        this.serviceInstanceId = serviceInstanceId;
    }

    /**
     * 一旦启动了这个组件后，就会在服务商干两件事儿
     * 1.开启一个工作线程，去向注册中心注册服务
     * 2.注册服务成功后，就会开启一个while true死循环，每隔30秒去发送心跳
     */

    @Override
    public void run() {
        // 如果还没有完成注册
        if (!finishedRegister) {
            // 可以从配置文件里获取到当前机器的hostname，ip，以及你的服务的端口号
            RegisterRequest request = new RegisterRequest();
            request.setServiceName(SERVICE_NAME);
            request.setHostName(HOST_NAME);
            request.setIp(IP);
            request.setPort(PORT);
            request.setServerInstanceId(serviceInstanceId);
            RegisterResponse response = httpSender.register(request);
            System.out.println("服务注册的结果是：【" + response.getStatus() + "】......");

            // 如果注册成功
            if (RegisterResponse.SUCCESS.equals(response.getStatus())) {
                finishedRegister = true;
            } else {
                return;
            }
        }

        // 如果说完成注册，则每隔30秒向注册中心发送心跳
        if (finishedRegister) {
            HeartbeatRequest request = new HeartbeatRequest();
            request.setServerInstanceId(serviceInstanceId);

            HeartbeatResponse response = null;
            while (true) {
                try {
                    response = httpSender.heartbeat(request);
                    System.out.println("心跳的结果为：【" + response.getStatus() + "】......");
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
