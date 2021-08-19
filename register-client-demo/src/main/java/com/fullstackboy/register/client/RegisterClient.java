package com.fullstackboy.register.client;

import java.util.UUID;

/**
 * 服务注册client
 * 在服务商被创建和启动，负责跟register-server进行通信
 *
 * 优化代码：
 * 1.将服务注册和心跳线程拆完两个内部类；
 * 2.然后服务注册线程使用.join()执行完后，再执行心跳线程。
 * @author Liuyongfei
 * @date 2021/8/16 18:14
 */
public class RegisterClient {
    public static final String SERVICE_NAME = "inventory-service";
    public static final String IP = "192.168.31.207";
    public static final String HOST_NAME = "inventory01";
    public static final int PORT = 9000;

    /**
     * http通信组件
     */
    private HttpSender httpSender;

    /**
     * 服务是否在运行
     */
    public Boolean isRunning;

    /**
     * 心跳线程
     */
    private HeartbeatWorker heartbeatWorker;

    /**
     * 拉取注册表线程
     */
    private ClientCacheServiceRegistry registry;

    /**
     * 服务实例id
     */
    private String serviceInstanceId;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-","");
        this.isRunning = true;
        this.httpSender = new HttpSender();
        this.heartbeatWorker = new HeartbeatWorker();
        this.registry = new ClientCacheServiceRegistry(this, httpSender);
    }

    /**
     * 启动registerClient组件
     */
    public void start() {
        try {
            // 启动服务注册线程
            RegisterWorker registerWorker = new RegisterWorker();
            registerWorker.start();
            registerWorker.join();

            // 启动心跳线程
            heartbeatWorker.start();

            // 启动定时拉取服务注册表线程
            this.registry.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭registerClient组件
     */
    public void shutdown() {
        this.isRunning = false;
        heartbeatWorker.interrupt();
        this.registry.destroy();
    }

    /**
     * 服务注册线程
     */
    private class RegisterWorker extends Thread {
        @Override
        public void run() {
            // 可以从配置文件里获取到当前机器的hostname，ip，以及你的服务的端口号
            RegisterRequest request = new RegisterRequest();
            request.setServiceName(SERVICE_NAME);
            request.setHostName(HOST_NAME);
            request.setIp(IP);
            request.setPort(PORT);
            request.setServerInstanceId(serviceInstanceId);

            RegisterResponse response = httpSender.register(request);

            System.out.println("服务注册的结果是：【" + response.getStatus() + "】......");
        }
    }

    /**
     * 心跳线程
     */
    private class HeartbeatWorker extends Thread {
        @Override
        public void run() {
            HeartbeatRequest request = new HeartbeatRequest();
            request.setServerInstanceId(serviceInstanceId);

            HeartbeatResponse response = null;
            while (isRunning) {
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
