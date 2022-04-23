package com.fullstackboy.jvm;

/**
 * 084、动手实验：JVM栈内存溢出的时候，应该如何解决？
 *
 * -XX:ThreadStackSize=1m -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 *
 * @author Liuyongfei
 * @date 2022/4/7 12:32
 */
public class Demo84 {
    private static long counter = 0;

    public static void main(String[] args) {
        work();
    }

    public static void work() {
        System.out.println("目前是第" + (++counter) + "次调用方法");
        work();
    }
}
