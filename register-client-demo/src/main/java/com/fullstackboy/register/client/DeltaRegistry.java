package com.fullstackboy.register.client;

import java.util.LinkedList;
import java.util.Queue;

import com.fullstackboy.register.client.CachedServiceRegistry.RecentlyChangedServiceInstance;
/**
 * 增量注册表
 *
 * @author Liuyongfei
 * @date 2021/8/23 23:35
 */
public class DeltaRegistry {

    /**
     * 最近3分钟有变更实例的队列
     */
    public Queue<RecentlyChangedServiceInstance> recentlyChangedQueue;

    /**
     * 服务实例的总数量
     */
    public long serviceInstanceTotalCount;

    public DeltaRegistry(Queue<RecentlyChangedServiceInstance> recentlyChangedQueue, long serviceInstanceTotalCount) {
        this.recentlyChangedQueue = recentlyChangedQueue;
        this.serviceInstanceTotalCount = serviceInstanceTotalCount;
    }

    public Queue<RecentlyChangedServiceInstance> getRecentlyChangedQueue() {
        return recentlyChangedQueue;
    }

    public void setRecentlyChangedQueue(Queue<RecentlyChangedServiceInstance> recentlyChangedQueue) {
        this.recentlyChangedQueue = recentlyChangedQueue;
    }

    public long getServiceInstanceTotalCount() {
        return serviceInstanceTotalCount;
    }

    public void setServiceInstanceTotalCount(long serviceInstanceTotalCount) {
        this.serviceInstanceTotalCount = serviceInstanceTotalCount;
    }
}
