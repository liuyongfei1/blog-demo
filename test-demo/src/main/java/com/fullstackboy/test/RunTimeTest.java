package com.fullstackboy.test;

/**
 * 测试 Runtime.getRuntime().addShutdownHook 钩子函数
 *
 * @author Liuyongfei
 * @date 2022/1/31 18:29
 */
public class RunTimeTest {

    public static void main(String[] args) {
        Thread threada = new Thread(() -> {
            System.out.println("thread a ....");
        },"a");

        Thread threadb = new Thread(() -> {
            System.out.println("thread b ....");
        },"b");

        Thread threadShutdown = new Thread(() -> {
            System.out.println("shutdown thread ....");
        },"c");

        Runtime.getRuntime().addShutdownHook(threadShutdown);

        threada.start();
        threadb.start();
    }
}
