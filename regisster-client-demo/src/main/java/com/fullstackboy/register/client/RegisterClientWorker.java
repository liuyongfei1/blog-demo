package com.fullstackboy.register.client;

/**
 * 服务注册客户端工作线程
 *
 * @author Liuyongfei
 * @date 2021/8/15 17:54
 */
public class RegisterClientWorker extends Thread{

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
