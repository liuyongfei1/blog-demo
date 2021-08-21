package concurrent;

import java.util.LinkedList;

/**
 * 在队列中的wait()和notify()用法
 *
 * @author Liuyongfei
 * @date 2021/8/21 11:31
 */
public class QueueWaitAndNotifyDemo {

    /**
     * 队列的长度
     */
    private static final long QUEUE_MAX_SIZE = 100;

    /**
     * 定义一个队列
     */
    private LinkedList<String> queue = new LinkedList<>();

    /**
     * 往队列中放入数据
     * @param element
     */
    public synchronized void offer (String element){
        try {
            // 进到这里，说明就已经获取到一把锁了
            // 队列已经满了
            if (queue.size() == QUEUE_MAX_SIZE) {
                wait(); // 释放掉这把锁，进入阻塞
            }
            // 队列没有满，则向队列中放入数据
            queue.add(element);
            notifyAll(); // 唤醒当前在等待这个锁的那些线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从队列中取出数据
     */
    public synchronized String take(){
        String element = null;
        try {
            // 进到这里，说明就已经获取到一把锁了
            // 队列里已经空了
            if (queue.size() == 0) {
                wait();// 释放掉这把锁，进入阻塞，这时其它线程加锁成功，往队列中放入数据
            }
            element = queue.removeFirst();
            notifyAll(); // 唤醒当前在等待这个锁的那些线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return element;
    }

    public static void main(String[] args) {

    }
}
