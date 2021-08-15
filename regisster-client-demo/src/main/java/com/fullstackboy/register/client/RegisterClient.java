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
}
