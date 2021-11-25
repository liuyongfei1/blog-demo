package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 *
 * 第二组API：
 * offer()、poll()、peak()
 * @author Liuyongfei
 * @date 2021/11/25 13:16
 */
public class Test2 {
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue<>(3);

        System.out.println(queue.offer("a")); // true
        System.out.println(queue.offer("b")); // true
        System.out.println(queue.offer("c")); // true
//        System.out.println(queue.offer("d"));  // 直接返回false，不抛出异常

        System.out.println(queue.poll()); // a
        System.out.println(queue.poll()); // b
        System.out.println(queue.poll()); // c
//        System.out.println(queue.poll()); // 返回null，不抛出异常
//
        System.out.println(queue.peek()); // null
    }
}
