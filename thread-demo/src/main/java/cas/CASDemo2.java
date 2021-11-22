package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS会引起ABA问题  =》 解决办法：使用带版本号的原子操作（AtomicStampedReference）
 *
 * 加上延时，可以保证每个线程刚进来时拿到的版本号是同一个
 * @author Liuyongfei
 * @date 2021/11/22 13:11
 */
public class CASDemo2 {
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();

            System.out.println("a1 => " + stamp);  // 1

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(1, 2, atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);

            System.out.println("a2 => " + atomicStampedReference.getStamp());  // 2

            boolean res = atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);

            System.out.println("a1.res => " + res); // true

            System.out.println("a3 => " + atomicStampedReference.getStamp());  // 3

        }).start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();

            System.out.println("b1 => " + stamp);  // 1

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 这个线程里，我期望的值应该是最先拿到的这个版本号对应的值
            boolean res = atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1);
            System.out.println("b1.res => " + res); // false

            System.out.println("b2 => " + atomicStampedReference.getStamp());

        }).start();
    }
}
