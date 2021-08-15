package com.fullstackboy.register.client;

import java.util.UUID;

/**
 * 微服务注册-client端
 * 可以打成一个jar包并上传到私服，供其他服务调用，负责跟注册中心进行通信
 *
 * @author Liuyongfei
 * @date 2021/8/15 17:53
 */
public class RegisterClient {

    /**
     * 服务实例id
     */
    private String serviceInstanceId;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 一旦启动了这个组件后，就会在服务商干两件事儿
     * 1.开启一个工作线程，去向注册中心注册服务
     * 2.注册服务成功后，就会开启一个while true死循环，每隔30秒去发送心跳
     */
    public void start() {
        new RegisterClientWorker(serviceInstanceId).start();
    }

    /**
     * 负责向register-server发起服务注册和心跳的线程
     * 注释：
     * RegisterClient是一个核心组件
     * RegisterClientWorker是完成RegisterClient类的核心功能，是属于RegisterClient的一个组件，在面向对象层面来说，比较常见的写法是做为一个内部类来使用，
     * 在开源项目中经常采取这种做法。
     * @author Liuyongfei
     * @date 2021/8/15 17:54
     */
    private class RegisterClientWorker extends Thread{

        private static final String SERVICE_NAME = "inventory-service";
        private static final String IP = "192.168.10.111";
        private static final String HOST_NAME = "inventory01";
        private static final String PORT = "9000";

        /**
         * http通信组件
         */
        private HttpSender httpSender;

        /**
         * 是否完成注册
         */
        private Boolean finishedRegister;

        /**
         * 服务实例id
         */
        private String serverInstanceId;

        public RegisterClientWorker( String serverInstanceId) {
            this.httpSender = new HttpSender();
            this.finishedRegister = false;
            this.serverInstanceId = serverInstanceId;
        }

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
                request.setServerInstanceId(serverInstanceId);
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
                request.setServerInstanceId(serverInstanceId);

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
}
