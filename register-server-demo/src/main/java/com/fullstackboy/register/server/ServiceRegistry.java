package com.fullstackboy.register.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 注册表
 *
 * @author Liuyongfei
 * @date 2021/8/16 22:10
 */
public class ServiceRegistry {

    public static final long RECENTLY_CHANGE_ITEM_CHECK_INTERVAL = 3000L;
    public static final long RECENTLY_CHANGE_ITEM_EXPIRED = 3 * 60 * 1000L;

    /**
     * 注册表是一个单例
     */
    private static final ServiceRegistry instance = new ServiceRegistry();

    /**
     * 服务注册表锁
     */
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock  readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private ServiceRegistry() {
        // 启动后台线程监控最近变更的队列
        RecentlyChangedQueueMonitor monitor = new RecentlyChangedQueueMonitor();
        monitor.setDaemon(true);
        monitor.start();
    }

    /**
     * 注册表
     * Map： key是服务名称，value是这个服务的所有的服务实例；
     * Map<String,ServiceInstance>: key是服务实例id，value是服务实例的信息
     */
//    private Map<String, Map<String, ServiceInstance>> registry = new HashMap<String,Map<String, ServiceInstance>>();
    private Map<String, Map<String, ServiceInstance>> registry = new ConcurrentHashMap<>();

    /**
     * 最近变更的服务实例队列
     */
//    private LinkedList<RecentlyChangedServiceInstance> recentlyChangedQueue = new LinkedList<>();
    private Queue<RecentlyChangedServiceInstance> recentlyChangedQueue = new ConcurrentLinkedQueue<>();

    /**
     * 加读锁
     */
    public void readLock() {
        this.readLock.lock();
    }

    /**
     * 释放读锁
     */
    public void readUnLock() {
        this.readLock.unlock();
    }

    /**
     * 加写锁
     */
    public void writeLock() {
        this.writeLock.lock();
    }


    /**
     * 释放写锁
     */
    public void writeUnLock() {
        this.writeLock.unlock();
    }

    /**
     * 服务注册
     */
    public /**synchronized**/ void register(ServiceInstance instance) {
        try {
            // 加写锁
            writeLock.lock();
            Map<String, ServiceInstance> instanceMap = registry.get(instance.getServiceName());
            if (instanceMap == null) {
//                instanceMap = new HashMap<>();
                instanceMap = new ConcurrentHashMap<>();
                registry.put(instance.getServiceName(), instanceMap);
            }

            instanceMap.put(instance.getServiceInstanceId(),instance);

            // 将新注册的服务实例加入最近变更队列
            RecentlyChangedServiceInstance recentlyChangedServiceInstance = new RecentlyChangedServiceInstance(
                    instance, ServiceInstanceOperation.REGISTER, System.currentTimeMillis()
            );
            recentlyChangedQueue.add(recentlyChangedServiceInstance);

            System.out.println("服务实例【" + instance + "】，完成注册");
            System.out.println("注册表：" + registry);
        } finally {
            // 释放写锁
            writeLock.unlock();
        }
    }

    /**
     * 获取实例信息
     * @param serviceName
     * @param serverInstanceId
     * @return ServiceInstance
     */
    public /**synchronized*/ ServiceInstance getServiceInstance(String serviceName, String serverInstanceId) {
        try {
            // 加读锁
            readLock.lock();
            Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
            return serviceInstanceMap.get(serverInstanceId);
        } finally {
            // 释放读锁
            readLock.unlock();
        }
    }

    /**
     * 服务摘除
     * @param serviceName 服务名称
     * @param serverInstanceId 服务实例id
     * @author Liuyongfei
     * @date 2021/8/16 下午10:40
     */
    public /**synchronized**/ void remove(String serviceName, String serverInstanceId) {
        try {
            // 加写锁
            writeLock.lock();
            // 获取注册表
            Map<String,ServiceInstance> serverInstanceMap = registry.get(serviceName);
            serverInstanceMap.remove(serverInstanceId);

            System.out.println("服务实例【" + serverInstanceId + "】被摘除");


            // 将新注册的服务实例加入最近变更队列
            RecentlyChangedServiceInstance recentlyChangedServiceInstance = new RecentlyChangedServiceInstance(
                    serverInstanceMap.get(serverInstanceId), ServiceInstanceOperation.REGISTER, System.currentTimeMillis()
            );
            recentlyChangedQueue.add(recentlyChangedServiceInstance);
        } finally {
            // 释放写锁
            writeLock.unlock();
        }
    }

    /**
     * 获取注册表的单例实例
     * @return
     */
    public static ServiceRegistry getInstance() {
        return instance;
    }

    /**
     * 获取全量注册表
     * @return 全量注册表
     */
    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }

    /**
     * 获取最近有变化的注册表
     * 像 registry.values(),serviceInstanceMap.size() 这种操作就没必要加锁了，因为本身就是基于volatile可以保证读到最新的
     * @return 最近有变化的注册表
     */
    public DeltaRegistry getDeltaRegistry() {
        long totalServiceInstanceCount = 0;
        for (Map<String,ServiceInstance> serviceInstanceMap : registry.values()) {
            totalServiceInstanceCount += serviceInstanceMap.size();
        }
        return new DeltaRegistry(recentlyChangedQueue, totalServiceInstanceCount);
    }

    /**
     * 最近变化的服务实例
     */
    class RecentlyChangedServiceInstance {

        /**
         * 服务实例
         */
        ServiceInstance serviceInstance;

        /**
         * 变更操作
         */
        String serviceInstanceOperation;

        /**
         * 发生变更时的时间戳
         */
        long changedTimestamp;

        public RecentlyChangedServiceInstance(ServiceInstance serviceInstance,
                                              String serviceInstanceOperation,
                                              long changedTimestamp) {
            this.serviceInstance = serviceInstance;
            this.serviceInstanceOperation = serviceInstanceOperation;
            this.changedTimestamp = changedTimestamp;
        }
    }

    /**
     * 服务实例变更操作
     */
    class ServiceInstanceOperation {
        private static final String REGISTER = "REGISTER";
        private static final String REMOVE = "REMOVE";
    }

    /**
     * 监控最近变化的服务实例后台线程
     */
    class RecentlyChangedQueueMonitor extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    try {
//                        synchronized (instance) {
                            // 由于这里存在 读，又存在写这个内存数据结构，所以光简简单单的用基于ConcurrentLinkedQueue本身的读写并发保障机制
                            // 还需要加写锁（写锁互斥，register,remove 也都加了写锁），这个过程中是不允许队列的元素有任何变化的。
                            writeLock();
                            long currentTimeStamp = System.currentTimeMillis();
                            RecentlyChangedServiceInstance recentlyChangedServiceInstance = null;
                            // 队列头不为null
                            while ((recentlyChangedServiceInstance = recentlyChangedQueue.peek()) != null) {
                                // 如果队列中最早的一个元素的变更信息在队列中已经超过3分钟了，则从队列中移除
                                if (currentTimeStamp - recentlyChangedServiceInstance.changedTimestamp > RECENTLY_CHANGE_ITEM_EXPIRED) {
                                    recentlyChangedQueue.poll();
                                }
                            }
//                        }
                    } finally {
                        writeUnLock();
                    }
                    Thread.sleep(RECENTLY_CHANGE_ITEM_CHECK_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
