package lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手动创建一个自旋锁
 *
 * @author Liuyongfei
 * @date 2021/11/22 23:04
 */
public class MySpinLock {

    // 如果泛型是 int，参数不写则默认就是 0
    // 如果泛型是 Threa，是个引用类型，参数不写则默认就是 null
    AtomicReference<Thread>  atomicReference = new AtomicReference();

    /**
     * 加锁
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
