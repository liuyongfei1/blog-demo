package producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这里写业务逻辑，使用 Lock Condition
 * 利用多个Condition（监视器），可以实现多个线程有序执行
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
public class MyDataUseLock2 {

    private int number = 1;

    Lock lock  = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public void printA() {
        lock.lock();

        try {
            while (number  != 1) {
                condition1.await();
            }

            System.out.println(Thread.currentThread().getName()+"=>AAAAAAA");

            // 唤醒执行的人：B
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();

        try {
            while (number != 2) {
                condition2.await();
            }

            System.out.println(Thread.currentThread().getName()+"=>BBBBBBB");

            // 唤醒执行的人：C
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();

        try {
            while (number != 3) {
                condition3.await();
            }

            System.out.println(Thread.currentThread().getName()+"=>CCCCCC");

            // 唤醒执行的人：A
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
