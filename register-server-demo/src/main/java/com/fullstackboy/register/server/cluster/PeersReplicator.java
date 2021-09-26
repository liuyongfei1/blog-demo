package com.fullstackboy.register.server.cluster;

import com.fullstackboy.register.server.web.AbstractRequest;
import com.fullstackboy.register.server.web.CancelRequest;
import com.fullstackboy.register.server.web.HeartbeatRequest;
import com.fullstackboy.register.server.web.RegisterRequest;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 集群同步组件
 *
 * @author Liuyongfei
 * @date 2021/9/26 06:54
 */
public class PeersReplicator {

    /**
     * 集群同步时数据生成batch的间隔时间：500ms
     */
    private static final long PEERS_REPLICATE_BATCH_INTERVAL = 500;

    private static final PeersReplicator instance = new PeersReplicator();

    private PeersReplicator() {

    }

    public static PeersReplicator getInstance() {
        return instance;
    }

    /**
     * 第一层队列：无界队列，接收请求的高并发写入
     *
     * ConcurrentLinkedQueue 的CAS操作会保证多线程高并发的线程安全
     */
    private ConcurrentLinkedQueue<AbstractRequest> acceptorQueue = new ConcurrentLinkedQueue<AbstractRequest>();

    /**
     * 第二层队列：有界队列，用于batch生成
     */
    private LinkedBlockingQueue<AbstractRequest> batchQueue = new LinkedBlockingQueue<>();

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

    /**
     * 负责接收数据以及打包为batch的后台线程
     */
    class AcceptorBatchThread extends Thread {

        long latestBatchGenerationTime = System.currentTimeMillis();

        @Override
        public void run() {
            while (true) {
                // 接收第一层队列的数据，并将数据放入第二层队列
                AbstractRequest request = acceptorQueue.poll();
                if (request != null) {
                    batchQueue.offer(request);
                }

                // 采取一定的策略，将数据打包为batch
                long now = System.currentTimeMillis();
                if (now - latestBatchGenerationTime >= PEERS_REPLICATE_BATCH_INTERVAL) {
                    // 此时如果第二层队列里有数据，则生成一个batch
                    if (batchQueue.size() > 0) {
                        PeersReplicateBatch batch = createBatch();
                    }
                }
            }
        }

        /**
         * 创建一个batch
         * @return PeersReplicateBatch
         */
        private PeersReplicateBatch createBatch() {
            PeersReplicateBatch batch = new PeersReplicateBatch();

            Iterator<AbstractRequest> iterator = batchQueue.iterator();
            while (iterator.hasNext()) {
                batch.add(iterator.next());
            }
            batchQueue.clear();
            return batch;
        }
    }
}
