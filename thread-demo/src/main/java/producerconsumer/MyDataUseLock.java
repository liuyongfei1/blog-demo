package producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这里写业务逻辑，使用 Lock Condition
 *
 * 一个执行加操作的方法： 0 -》1
 * 一个执行减操作的方法： 1 -》0
 *
 * 判断是否需要等待
 *
 * 业务
 *
 * 通知
 * @author Liuyongfei
 * @date 2021/11/23 08:57
 */
public class MyDataUseLock {

    private int number = 0;

    Lock lock  = new ReentrantLock();
    Condition condition = lock.newCondition();


    /**
     * 执行加操作
     */
    public void increment() throws InterruptedException {

        lock.lock();

        try {
            if (number != 0) {
                // 等待
                condition.await();
            }

            number++;

            System.out.println(Thread.currentThread().getName() + "=>" + number);

            // 通知其它线程，我+1完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 执行减操作
     */
    public void decrement() throws InterruptedException {

       lock.lock();
        try {
            if (number == 0) {
                // 等待
                condition.await();
            }

            number--;

            System.out.println(Thread.currentThread().getName() + "=>" + number);

            // 通知其它线程，我-1完毕了
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
