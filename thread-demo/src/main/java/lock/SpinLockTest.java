package lock;

import java.util.concurrent.TimeUnit;

/**
 * 自旋锁测试
 *
 * @author Liuyongfei
 * @date 2021/11/22 23:14
 */
public class SpinLockTest {

    public static void main(String[] args) throws InterruptedException {
        MySpinLock lock = new MySpinLock();

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();
            }
        },"T1").start();

        // 这里的目的是保证T1线程先拿到锁
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnlock();
            }
        },"T2").start();
    }
}
