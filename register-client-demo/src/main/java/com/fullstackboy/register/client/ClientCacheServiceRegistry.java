package com.fullstackboy.register.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务注册中心的客户端缓存的一个服务注册表
 *
 * @author Liuyongfei
 * @date 2021/8/19 12:58
 */
public class ClientCacheServiceRegistry {

    /**
     * 定时拉取服务注册表的时间间隔
     */
    private static final Long SERVICE_REGISTRY_FETCH_INTERVAL = 30 * 1000L;

    /**
     * RegisterClient
     */
    private RegisterClient registerClient;

    /**
     * 定时拉取服务注册表的后台线程
     */
    private Daemon daemon;

    /**
     * http通信组件
     */
    private HttpSender httpSender;

    /**
     * 客户端缓存的服务注册表
     */
    private Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();

    ClientCacheServiceRegistry(RegisterClient registerClient, HttpSender httpSender) {
        this.registerClient = registerClient;
        this.httpSender = httpSender;
        this.daemon = new Daemon();
    }

    /**
     * 启动定时拉取服务注册表线程
     */
    public void start() {
        this.daemon.start();
    }

    /**
     * 销毁定时拉取服务注册表线程
     */
    public void destroy() {
        this.daemon.interrupt();
    }

    /**
     * 后台线程，负责定时拉取服务注册表到本地来进行缓存
     */
    private class Daemon extends Thread {

        @Override
        public void run() {
            while (registerClient.isRunning) {

                try {
                    // 定时从注册中心拉取服务注册表
                    registry = httpSender.fetchServiceRegistry();

                    Thread.sleep(SERVICE_REGISTRY_FETCH_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取注册表
     * @return 注册表
     */
    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }
}
