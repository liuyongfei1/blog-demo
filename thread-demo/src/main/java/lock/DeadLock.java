package lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试
 *
 * @author Liuyongfei
 * @date 2021/11/22 23:44
 */
public class DeadLock {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new MyDeadLock(lockA, lockB),"T1").start();
        new Thread(new MyDeadLock(lockB, lockA),"T2").start();
    }
}

class MyDeadLock implements Runnable{

    private String lockA;
    private String lockB;

    public MyDeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "lock:" + lockA + "=>get" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "lock:" + lockB + "=>get" + lockA);
            }
        }
    }
}
