package com.fullstackboy.register.server;

import java.util.Map;

/**
 * 服务实例存活监控线程
 *
 * 优化：加入自我保护机制
 *
 * @author Liuyongfei
 * @date 2021/8/16 23:31
 */
public class ServiceAliveMonitor {
    /**
     * 检查服务实例是否存活的间隔
     */
    private static final Long CHECK_ALIVE_INTERVAL = 60 * 1000L;

    /**
     * 监控服务是否存活的线程
     */
    private Daemon daemon;

    public ServiceAliveMonitor() {
        this.daemon = new Daemon();
        daemon.setDaemon(true);
        daemon.setName("ServiceAliveMonitor");
    }

    /**
     * 启动后台线程
     */
    public void start() {
        daemon.start();
    }

    /**
     * 监控服务是否存活线程
     */
    private class Daemon extends Thread {
        private ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();


        @Override
        public void run() {
            Map<String, Map<String, ServiceInstance>> registryMap = null;
            while (true) {
                try {
                    // 遍历注册表
                    registryMap = serviceRegistry.getRegistry();
                    for (String serviceName : registryMap.keySet()) {
                        Map<String, ServiceInstance> serviceInstanceMap = registryMap.get(serviceName);

                        for (ServiceInstance serviceInstance : serviceInstanceMap.values()) {
                            if (!serviceInstance.isAlive()) {
                                serviceRegistry.remove(serviceName, serviceInstance.getServiceInstanceId());

                                // 更新自我保护机制的阈值
                                synchronized (SelfProtectionPolicy.class) {
                                    SelfProtectionPolicy policy = SelfProtectionPolicy.getInstance();
                                    policy.setExpectedHeartbeatRate(policy.getExpectedHeartbeatRate() + 2);
                                    policy.setExpectedHeartbeatThreshold((long) (policy.getExpectedHeartbeatRate() * 0.85));
                                }
                            }
                        }
                    }
                    Thread.sleep(CHECK_ALIVE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
