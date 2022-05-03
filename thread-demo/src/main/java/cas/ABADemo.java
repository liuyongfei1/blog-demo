package cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA demo
 * 虽然说可能不会影响最终的值，但是中间是被动过的，是有猫腻的。
 * @author Liuyongfei
 * @date 2022/5/3 22:45
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {

        System.out.println("=========================以下是ABA问题的产生================================");

        // 第一个线程
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        },"t1").start();

        new Thread(() -> {
            // 暂停1秒钟，确保t1线程完成了一次ABA操作
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicReference.compareAndSet(100, 2022));
            System.out.println(atomicReference.get());
        },"t2").start();

        System.out.println("=========================以下是ABA问题的解决================================");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 第一次版本号: " + stamp);
            try {
                // 暂停1秒钟
                // 注意这里 这个暂停1秒钟很关键
                // 意思是我我t3线程拿到版本号之后，sleep 1秒钟，确保t4线程也拿到了版本号，也就是说 t3和t4线程拿到的是同样的版本号。
                // 怕t4线程还没有拿到版本号，然后t3线程就进行修改操作了。
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + " 第二次版本号: " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第三次版本号: " + atomicStampedReference.getStamp());

        },"t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 第一次版本号: " + stamp);
            try {
                // 注意：暂停3秒钟t4线程，确保t3线程完成了一次ABA操作
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = atomicStampedReference.compareAndSet(100, 2022,
                    stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + " 修改成功否：" + b);
            System.out.println(Thread.currentThread().getName() + " 最新版本号为：" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + " 实际值为：" + atomicStampedReference.getReference());
        },"t4").start();

    }
}
