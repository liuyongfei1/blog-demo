package com.fullstackboy.mybatis.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * zookeeper分布式锁
 *
 * @author Liuyongfei
 * @date 2021/8/12 08:37
 */
public class ZkLock {
    public static void main(String[] args) throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                "10.100.23.3:2181,10.100.23.7:2181,10.100.23.9:2181", retryPolicy);
        client.start();

//        client.create().creatingParentsIfNeeded().forPath("/locks/lock_01", "hello world".getBytes());
//
//        System.out.println(new String(client.getData().forPath("/locks/lock_01"))); // hello world
//
//
//        // *******************获取普通锁（其实就是可重入锁）*****************
//        InterProcessMutex lock = new InterProcessMutex(client, "/locks/lock_01");
//        lock.acquire();
//        Thread.sleep(3000);
//        lock.release();
        // *******************获取普通锁（其实就是可重入锁）end*****************


        // ************Semaphore锁*************
//        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, "/semaphores/semaphore_01", 3);
//
//        Lease lease = semaphore.acquire();
//        Thread.sleep(3000);
//
//        semaphore.returnLease(lease);
        // ************Semaphore锁 end*************

        // ******************基于semaphore实现的非重入锁***************
//        InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, "/locks/lock_01");
//        lock.acquire();
//        Thread.sleep(3000);
//        lock.release();
        // ******************非重入锁 end***************

        // ***************读写锁******************
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, "/locks/lock_01");

//        Thread.sleep(3000);
//        lock.readLock().release();

//        lock.writeLock().acquire();
//        // lock.readLock().acquire();
//        lock.writeLock().acquire();
//        lock.writeLock().release();
        // ***************读写锁 end******************


        // ***************MultiLock *********************
        InterProcessLock lock1 = new InterProcessMutex(client,"/locks/lock_01");
        InterProcessLock lock2 = new InterProcessMutex(client,"/locks/lock_02");
        InterProcessLock lock3 = new InterProcessMutex(client,"/locks/lock_03");

        List<InterProcessLock> locks = new ArrayList<>();
        locks.add(lock1);
        locks.add(lock2);
        locks.add(lock3);

        InterProcessMultiLock multiLock = new InterProcessMultiLock(locks);
        multiLock.acquire();
        multiLock.release();
        // ***************MultiLock end******************



    }
}
