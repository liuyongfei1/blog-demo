package concurrent;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程安全的有界队列
 *
 * @author Liuyongfei
 * @date 2021/9/1 07:20
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws Exception{
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("张三");
        System.out.println(queue.take());
    }
}
