package com.fullstackboy.jvm;

/**
 * 053、案例实战：每秒10万并发的BI系统，如何定位和解决频繁Young GC问题？
 * 每秒请求大概新增 100KB的对象，每秒500个请求，大概会新增 50MB的数据到内存中。
 * @author Liuyongfei
 * @date 2022/4/5 18:11
 */
public class Demo53 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);

        while (true) {
            loadData();
        }
    }

    /**
     * 模拟每秒50个请求
     * 每个请求会创建1个100KB的数组
     *
     * 也就是在Eden区，每秒会新增 5MB 的对象。
     * @throws InterruptedException
     */
    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }

        data = null;
        Thread.sleep(1000);
    }
}
