package com.fullstackboy.test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最少访问
 *  LinkedHashMap实现(继承方式)
 *
 *  1. 新数据插入链表头部；
 *  2.每当缓存命中（即缓存数据被访问），则将数据转移到头部
 *  3.当链表满的时候，将链表尾部的数据丢弃
 *
 *  命中时需要遍历链表，找到命中的数据块的索引，然后需要将数据转移到头部
 * @param <K>
 * @param <V>
 */
public class LRUCache2<K, V> extends LinkedHashMap<K, V> {
    //最大缓存大小
    private final int MAX_CACHE_SIZE;
 
    /**
     * 构造函数，当参数accessOrder为true时，即会按照访问顺序排序，最近访问的放在最前，最早访问的放在后面
     * @param cacheSize
     */
    public LRUCache2(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        MAX_CACHE_SIZE = cacheSize;
    }
 
    /**
     * LinkedHashMap自带的判断是否删除最老的元素方法，默认返回false，即不删除老数据
     * 判断当缓存大小超过最大缓存大小时返回true
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CACHE_SIZE;
    }
 
    /**
     * 测试
     */
    public static void main(String[] args) {
        LRUCache2 lruCache = new LRUCache2(8);
        for (int i = 0; i < 10; i++) {
            lruCache.put(i, i);
            System.out.println(lruCache);
        }
    }
}