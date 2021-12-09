package com.fullstackboy.register.server.core;

import java.util.ArrayList;
import java.util.List;
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
     * 服务注册表
     */
    private ServiceRegistry registry = ServiceRegistry.getInstance();

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
                    // 判断一下是否需要开启自我保护机制
                    SelfProtectionPolicy policy = SelfProtectionPolicy.getInstance();
                    // 开启自我保护机制
                    if (policy.enabled()) {
                        Thread.sleep(CHECK_ALIVE_INTERVAL);
                        continue;
                    }

                    // 遍历注册表
                    try {
                        // 对整个服务注册表加读锁
                        registry.readLock();

                        // 将要删除的实例
                        List<ServiceInstance> removingInstances = new ArrayList<>();

                        registryMap = serviceRegistry.getRegistry();
                        for (String serviceName : registryMap.keySet()) {
                            Map<String, ServiceInstance> serviceInstanceMap = registryMap.get(serviceName);

                            for (ServiceInstance serviceInstance : serviceInstanceMap.values()) {
                                if (!serviceInstance.isAlive()) {
//                                    serviceRegistry.remove(serviceName, serviceInstance.getServiceInstanceId());

                                    removingInstances.add(serviceInstance);

                                    // 更新自我保护机制的阈值
//                                    synchronized (SelfProtectionPolicy.class) {
//                                        policy.setExpectedHeartbeatRate(policy.getExpectedHeartbeatRate() - 2);
//                                        policy.setExpectedHeartbeatThreshold((long) (policy.getExpectedHeartbeatRate() * 0.85));
//                                    }
//                                }
                                }
                            }

                            // 将要删除的实例从注册表里删除
                            for (ServiceInstance serviceInstance : removingInstances) {
                                serviceRegistry.remove(serviceInstance.getServiceName(), serviceInstance.getServiceInstanceId());

                                // 更新自我保护机制的阈值
                                synchronized (SelfProtectionPolicy.class) {
                                    policy.setExpectedHeartbeatRate(policy.getExpectedHeartbeatRate() - 2);
                                    policy.setExpectedHeartbeatThreshold((long) (policy.getExpectedHeartbeatRate() * 0.85));
                                }
                            }

                            // 过期注册表缓存
                            ServiceRegistryCache.getInstance().invalidate();
                        }
                    } finally {
                        registry.readUnLock();
                    }
                    Thread.sleep(CHECK_ALIVE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
