package com.fullstackboy.register.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 服务注册表的缓存
 *
 * 采用多级缓存机制（Eureka中的服务注册中心的多级缓存机制）
 * @author Liuyongfei
 * @date 2021/8/28 15:27
 */
public class ServiceRegistryCache {

    /**
     * 两级缓存同步的间隔
     */
    private static final Long CACHE_MAP_SYNC_INTERVAL = 30 * 1000L;

    /**
     * 服务注册表的缓存的单例
     */
    private static final ServiceRegistryCache getInstance = new ServiceRegistryCache();


    /**
     * 获取注册表的单例
     */
    private ServiceRegistry registry = ServiceRegistry.getInstance();

    /**
     * 只读缓存Map
     */
    private Map<String, Object> readOnlyCacheMap = new HashMap<>();

    /**
     * 读写缓存Map
     */
    private Map<String,Object> readWriteCacheMap = new HashMap<>();

    /**
     * 内部锁
     */
    private Object lock = new Object();

    /**
     * 只读缓存的读写锁
     */
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public ServiceRegistryCache() {
        // 启动多级缓存同步的后台线程
        CacheMapSyncDaemon daemon = new CacheMapSyncDaemon();
        daemon.setDaemon(true);
        daemon.start();
    }

    /**
     * 获取注册表的数据
     * @param key key
     * @return 注册表数据
     */
    public Object get(String key) {

        readLock.lock();
        // 先从只读缓存Map里取
        Object cacheValue = readOnlyCacheMap.get(key);
        if (cacheValue == null) {
            synchronized (lock) {
                if (readOnlyCacheMap.get(key) == null) {
                    // 从读写缓存里取数据
                    cacheValue = readWriteCacheMap.get(key);
                    // 如果读写缓存里的数据也为空，则从实际的注册表里去读数据
                    if (cacheValue == null) {
                        cacheValue = getCacheValue(key);
                        readWriteCacheMap.put(key, cacheValue);
                    }
                    readOnlyCacheMap.put(key, cacheValue);
                }
            }
        }
        readLock.unlock();
        return cacheValue;
    }

    /**
     * 缓存的key
     */
    class CacheKey {
        public static final String FULL_SERVICE_REGISTRY = "full_service_key";

        public static final String DELTA_SERVICE_REGISTRY = "delta_service_registry";
    }

    /**
     * 从注册表里获取注册数据
     * @param key 注册表对应的key
     * @return 注册表
     */
    public Object getCacheValue(String key) {
        if (CacheKey.FULL_SERVICE_REGISTRY.equals(key)) {
            return registry.getRegistry();
        }
        else if (CacheKey.DELTA_SERVICE_REGISTRY.equals(key)) {
            return registry.getRecentlyChangedQueue();
        }
        return null;
    }

    /**
     * 过期掉对应的缓存
     */
    public void invalidate() {
        synchronized (lock) {
            readWriteCacheMap.remove(CacheKey.FULL_SERVICE_REGISTRY);
            readWriteCacheMap.remove(CacheKey.DELTA_SERVICE_REGISTRY);
        }
    }

    /**
     * 同步两个缓存Map的后台线程
     */
    class CacheMapSyncDaemon extends Thread{
        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (lock) {
                        if (readWriteCacheMap.get(CacheKey.FULL_SERVICE_REGISTRY) == null) {
                            try {
                                writeLock.lock();
                                readOnlyCacheMap.put(CacheKey.FULL_SERVICE_REGISTRY, null);
                            } finally {
                                writeLock.unlock();
                            }
                        }
                        else if (readWriteCacheMap.get(CacheKey.DELTA_SERVICE_REGISTRY) == null) {
                            try {
                                writeLock.lock();
                                readOnlyCacheMap.put(CacheKey.DELTA_SERVICE_REGISTRY, null);
                            } finally {
                                writeLock.unlock();
                            }
                        }
                        Thread.sleep(CACHE_MAP_SYNC_INTERVAL);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
