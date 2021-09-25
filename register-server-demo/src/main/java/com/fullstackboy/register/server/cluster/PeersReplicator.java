package com.fullstackboy.register.server.cluster;

import com.fullstackboy.register.server.web.AbstractRequest;
import com.fullstackboy.register.server.web.CancelRequest;
import com.fullstackboy.register.server.web.HeartbeatRequest;
import com.fullstackboy.register.server.web.RegisterRequest;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 集群同步组件
 *
 * @author Liuyongfei
 * @date 2021/9/26 06:54
 */
public class PeersReplicator {

    private static final PeersReplicator instance = new PeersReplicator();

    private PeersReplicator() {

    }

    public static PeersReplicator getInstance() {
        return instance;
    }

    /**
     * 第一层队列：接收请求的高并发写入，无界队列
     *
     * ConcurrentLinkedQueue 的CAS操作会保证多线程高并发的线程安全
     */
    private ConcurrentLinkedQueue<AbstractRequest> acceptorQueue = new ConcurrentLinkedQueue<AbstractRequest>();

    /**
     * 同步服务注册请求
     * @param request 服务注册请求
     */
    public void replicateRegister(RegisterRequest request) {
        acceptorQueue.offer(request);
    }

    /**
     * 同步服务下线请求
     * @param request 服务下线请求
     */
    public void replicateCancel(CancelRequest request) {
        acceptorQueue.offer(request);
    }

    /**
     * 同步服务发送心跳请求
     * @param request 服务发送心跳请求
     */
    public void replicateHeartbeat(HeartbeatRequest request) {
        acceptorQueue.offer(request);
    }
}
