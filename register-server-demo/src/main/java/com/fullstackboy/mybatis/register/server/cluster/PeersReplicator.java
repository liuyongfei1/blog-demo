package com.fullstackboy.mybatis.register.server.cluster;

import com.fullstackboy.mybatis.register.server.web.AbstractRequest;
import com.fullstackboy.mybatis.register.server.web.CancelRequest;
import com.fullstackboy.mybatis.register.server.web.HeartbeatRequest;
import com.fullstackboy.mybatis.register.server.web.RegisterRequest;

import java.util.Iterator;
import java.util.concurrent.*;

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
        // 启动接收请求和打包batch的线程
        AcceptorBatchThread batchThread = new AcceptorBatchThread();
        batchThread.setDaemon(true);
        batchThread.start();

        // 启动同步batch的线程
        PeersReplicateThread replicateThread = new PeersReplicateThread();
        replicateThread.setDaemon(true);
        replicateThread.start();
    }

    public static PeersReplicator getInstance() {
        return instance;
    }

    /**
     * 第一层队列：无界队列，接收请求的高并发写入
     *
     * ConcurrentLinkedQueue 的CAS操作会保证多线程高并发的线程安全
     */
    private ConcurrentLinkedQueue<AbstractRequest> acceptorQueue = new ConcurrentLinkedQueue<>();

    /**
     * 第二层队列：有界队列，用于batch生成
     */
    private LinkedBlockingQueue<AbstractRequest> batchQueue = new LinkedBlockingQueue<>();

    /**
     * 第三层队列：有界队列，用于batch的同步发送
     */
    private LinkedBlockingQueue<PeersReplicateBatch> replicateQueue = new LinkedBlockingQueue<>();

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
                try {
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
                            replicateQueue.offer(batch);
                        }
                        this.latestBatchGenerationTime = now;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    /**
     * 集群同步线程
     */
    class PeersReplicateThread extends Thread {

        // 线程数量跟部署的register-server数量一致即可
        ExecutorService threadPoor = Executors.newFixedThreadPool(RegisterServerCluster.getPeers().size());

        @Override
        public void run() {
            while (true) {
                try {
                    PeersReplicateBatch batch = replicateQueue.take();
                    if (batch != null) {
                        threadPoor.execute(new Runnable() {
                            @Override
                            public void run() {
                                for (String peer : RegisterServerCluster.getPeers()) {
                                    // 遍历所有的其他的register-server地址
                                    // 给每个register-server都发送一个http请求
                                    System.out.println("给register-server【" + peer + "】发送请求，同步batch过去......");
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
