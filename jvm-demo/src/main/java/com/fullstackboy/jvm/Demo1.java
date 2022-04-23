package com.fullstackboy.jvm;

/**
 * 043、动手实验：自己动手模拟出频繁Young GC的场景体验一下！
 *
 * 对应的JVM启动参数为：
 * -XX:InitialHeapSize 初始化堆内存大小
 * -XX:MaxHeapSize 可分配的最大堆内存大小
 * -XX:NewSize 初始化的新生代大小
 * -XX:MaxNewSize 可分配的最大的新生代大小
 * -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:NewSize=5242880 -XX:MaxNewSize=5242880  -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log
 * @author Liuyongfei
 * @date 2022/04/03 16:32
 */
public class Demo1 {
    public static void main(String[] args) {
        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte[] array3 = new byte[2 * 1024 * 1024];
    }
}
