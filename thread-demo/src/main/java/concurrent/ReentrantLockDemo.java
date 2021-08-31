package concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/8/31 23:03
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            // do something
        } finally {
            lock.unlock();
        }

    }
}
