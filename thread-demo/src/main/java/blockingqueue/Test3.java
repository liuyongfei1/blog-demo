package blockingqueue;

import java.lang.reflect.Array;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列
 *
 * 第三组API：
 * 阻塞、等待
 * put()、take()
 * @author Liuyongfei
 * @date 2021/11/25 13:25
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue<String>(3);

        queue.put("a");
        queue.put("b");
        queue.put("c");
//        queue.put("d"); // 队列没有位置了，因此会一直阻塞，进程不会退出

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take()); // 队列没有元素了，因此会一直阻塞，进程不会退出

    }
}
