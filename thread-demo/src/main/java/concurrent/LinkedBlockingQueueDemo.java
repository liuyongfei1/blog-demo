package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程安全的有界队列
 *
 * @author Liuyongfei
 * @date 2021/9/1 07:20
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws Exception{
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
        queue.offer("张三");
        queue.offer("李四");
//        System.out.println(queue.take());

        ArrayBlockingQueue queue1 = new ArrayBlockingQueue(2);
        queue1.add("张三");
        queue1.add("赵四");

        System.out.println(queue1);
    }
}
