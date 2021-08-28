package com.fullstackboy.register.server;

import java.util.LinkedList;

/**
 * 包装增量注册表
 *
 * @author Liuyongfei
 * @date 2021/8/28 18:43
 */
public class DeltaRegistry {
    LinkedList<ServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue = new LinkedList<>();

    private Long serviceInstanceTotalCount;

    public DeltaRegistry(LinkedList<ServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue,
                         Long serviceInstanceTotalCount) {
        this.recentlyChangedQueue = recentlyChangedQueue;
        this.serviceInstanceTotalCount = serviceInstanceTotalCount;
    }

    public LinkedList<ServiceRegistry.RecentlyChangedServiceInstance> getRecentlyChangedQueue() {
        return recentlyChangedQueue;
    }

    public void setRecentlyChangedQueue(LinkedList<ServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue) {
        this.recentlyChangedQueue = recentlyChangedQueue;
    }

    public Long getServiceInstanceTotalCount() {
        return serviceInstanceTotalCount;
    }

    public void setServiceInstanceTotalCount(Long serviceInstanceTotalCount) {
        this.serviceInstanceTotalCount = serviceInstanceTotalCount;
    }
}
