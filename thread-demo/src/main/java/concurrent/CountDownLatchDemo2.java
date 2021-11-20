package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的使用demo
 *
 * 教室里有6个小学生，在6个小学生都离开教室后，保安会把教室的门锁上。
 *
 * @author Liuyongfei
 * @date 2021/11/17 23:23
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "Go Out");

                // 数量减一
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        // 等待计数器归零，再向下执行
        countDownLatch.await();
        System.out.println("Close Door");
    }
}
