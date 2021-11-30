package com.fullstackboy.mybatis.register.server.core;

import java.util.Queue;

/**
 * 包装增量注册表
 *
 * @author Liuyongfei
 * @date 2021/8/28 18:43
 */
public class DeltaRegistry {
//    LinkedList<ServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue = new LinkedList<>();
    Queue<ServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue;

    private Long serviceInstanceTotalCount;

    public DeltaRegistry(Queue<ServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue,
                         Long serviceInstanceTotalCount) {
        this.recentlyChangedQueue = recentlyChangedQueue;
        this.serviceInstanceTotalCount = serviceInstanceTotalCount;
    }

    public Queue<ServiceRegistry.RecentlyChangedServiceInstance> getRecentlyChangedQueue() {
        return recentlyChangedQueue;
    }

    public void setRecentlyChangedQueue(Queue<ServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue) {
        this.recentlyChangedQueue = recentlyChangedQueue;
    }

    public Long getServiceInstanceTotalCount() {
        return serviceInstanceTotalCount;
    }

    public void setServiceInstanceTotalCount(Long serviceInstanceTotalCount) {
        this.serviceInstanceTotalCount = serviceInstanceTotalCount;
    }
}
