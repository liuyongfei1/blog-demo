package com.fullstackboy;


import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Redis分布式锁测试
 *
 * @author Liuyongfei
 * @date 2021/8/6 23:55
 */
public class TestRedisLock {

    public static UUID id;
    public static void main(String[] args) {
        Config config = new Config();

        System.out.println(UUID.randomUUID().toString());

        // 这里本地没有搭建redis集群
        config.useClusterServers().addNodeAddress("localhost:6379");

        RedissonClient redisson = Redisson.create(config);

//        RLock lock = redisson.getFairLock("testLock");
//
//        try {
//            lock.tryLock(100,10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        lock.lock();
//        lock.unlock();

        RLock lock1 = redisson.getLock("lock1");
        RLock lock2 = redisson.getLock("lock2");
        RLock lock3 = redisson.getLock("lock3");
//
//        RedissonMultiLock lock = new RedissonMultiLock(lock1,lock2,lock3);
//        lock.lock();
//        lock.unlock();
//        RedissonRedLock lock = new RedissonRedLock();
//        lock.lock();
        ReadWriteLock rwlock = redisson.getReadWriteLock("readWriteLock");
        rwlock.readLock().lock();
        rwlock.readLock().unlock();

        rwlock.writeLock().lock();
        rwlock.writeLock().unlock();

        final RSemaphore semaphore = redisson.getSemaphore("semaphore");
        semaphore.trySetPermits(3);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(new Date() + ": 线程[" + Thread.currentThread().getName() + "]尝试获取semaphore锁");
                        semaphore.acquire();
                        System.out.println(new Date() + ": 线程[" + Thread.currentThread().getName() + "]成功获取到了semaphore"
                                + "锁");
                        Thread.sleep(3000);
                        semaphore.release();
                        System.out.println(new Date() + ": 线程[" + Thread.currentThread().getName() + "]释放了semaphore锁");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
