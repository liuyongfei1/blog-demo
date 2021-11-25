package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * 第二组API：等待一定时间后，退出，不抛出异常
 * offer()、poll()
 * 第二个参数传超时时间
 *
 *
 * @author Liuyongfei
 * @date 2021/11/25 13:16
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue<>(3);

        System.out.println(queue.offer("a")); // true
        System.out.println(queue.offer("b")); // true
        System.out.println(queue.offer("c")); // true
//        System.out.println(queue.offer("d",2, TimeUnit.SECONDS));  // 等待2秒后，返回false，退出

        System.out.println(queue.poll()); // a
        System.out.println(queue.poll()); // b
        System.out.println(queue.poll()); // c
        System.out.println(queue.poll(2, TimeUnit.SECONDS)); // 等待2秒后，返回null，退出。
//
    }
}
