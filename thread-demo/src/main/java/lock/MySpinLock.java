package lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手动创建一个自旋锁
 *
 * 什么是自旋锁？
 *  自旋锁是指 当一个线程尝试获取锁时，如果该锁已经被其它线程占用，就一直循环检测是否被释放，而不是进入线程挂起或睡眠状态。
 *  https://blog.csdn.net/weixin_46146718/article/details/120647711
 *
 * @author Liuyongfei
 * @date 2021/11/22 23:04
 */
public class MySpinLock {

    // 如果泛型是 int，参数不写则默认就是 0
    // 如果泛型是 Thread，是个引用类型，参数不写则默认就是 null
    // 持有锁的线程，null表示锁未被线程持有
    AtomicReference<Thread>  atomicReference = new AtomicReference();


    /**
     * 加锁
     *
     * 调用myLock方法时，如果 atomicReference 当前值为null，则表明自旋锁还没有被占用，所以可以将atomicReference设置为 currentThread，并进行锁定
     * 如果调用myLock方法时， atomicReference 值不为null，则表明自旋锁已经被其它线程占用了，当前线程就会在while循环中继续循环检测。
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(Thread.currentThread().getName() + "=> myLock");
    }

    /**
     * 解锁
     */
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "=> myUnlock");

        atomicReference.compareAndSet(thread, null);
    }
}
