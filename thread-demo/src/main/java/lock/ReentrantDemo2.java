package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁-Lock
 *
 * @author Liuyongfei
 * @date 2021/11/22 22:46
 */
public class ReentrantDemo2 {

    public static void main(String[] args) {
        ReentrantDemo2 demo = new ReentrantDemo2();
        demo.test();
    }

    public void test() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        try {
            System.out.println("第一次获得这个锁，这个锁是" + this);

            int index =1;
            while (true) {
                lock.lock();
                try {
                    System.out.println("第" + (++index) + "次获得这个锁，这个锁是" + this);

                    if (index == 10) {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
