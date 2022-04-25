package producerconsumer.fuxi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/4/25 10:14
 */
public class MyDataUseLock {

    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void increment() throws InterruptedException {
        lock.lock();

        try {
            if (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            // 通知其它线程，我-1完毕了
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();

        try {
            if (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            // 通知其它线程，我-1完毕了
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
