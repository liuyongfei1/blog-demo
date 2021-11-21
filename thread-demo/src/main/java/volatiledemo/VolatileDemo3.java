package volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile不保证原子性
 *
 * 我们期望拿到的num值是20000，但实际输出的不是这样。
 *
 * @author Liuyongfei
 * @date 2021/11/21 15:23
 */
public class VolatileDemo3 {

//    private static volatile int num = 0;
    private static volatile AtomicInteger num = new AtomicInteger();

    private static void add() {
//        num++;
        num.getAndIncrement(); // AtomicInteger + 1，使用CAS。
    }
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    add();
                }
            }).start();
        }

        // main,gc
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
