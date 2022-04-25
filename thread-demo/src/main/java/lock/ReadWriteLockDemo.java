package lock;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的案例
 * 1.不使用读写锁
 * @author Liuyongfei
 * @date 2021/11/18 09:16
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
//        MyCache myCache = new MyCache ();
        MyCacheLock myCache = new MyCacheLock ();

        // 启动5个线程，同时向cache中写入数据
        for (int i = 1; i <= 5 ; i++) {
            final int tmp = i;
            new Thread(() -> {
                myCache.put(tmp + "", tmp + " value");
            }, String.valueOf(i)).start();
        }

        // 启动5个线程，同时从cache中读取数据
        for (int i = 1; i <= 5; i++) {
            final  int tmp = i;
            new Thread(() -> {
                myCache.get(tmp + "");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 自定义缓存
 */
class MyCache {
    private volatile HashMap<String, Object> cacheMap = new HashMap<>();

    /**
     * 存，写入的时候，同时只有一个线程写
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + " 写入 " + key);
        cacheMap.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 写入ok");
    }

    /**
     * 取，读，所有人都可以读
     * @param key
     */
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " 读取 " + key);
        Object value = cacheMap.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取ok：" + value);
    }
}
/**
 * 自定义缓存，使用读写锁
 */
class MyCacheLock {
    private volatile HashMap<String, Object> cacheMap = new HashMap<>();

     private Lock lock = new ReentrantLock();
    // 声明一个读写锁：更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    /**
     * 存，写
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 写入 " + key);
            cacheMap.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * 取，读
     * @param key
     */
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 读取 " + key);
            Object value = cacheMap.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取ok：" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}