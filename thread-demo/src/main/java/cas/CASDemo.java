package cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 比较并交换
 *
 * @author Liuyongfei
 * @date 2021/11/21 22:36
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2000);

        // 期望，更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果是我期望的值，就更新；否则，就不更新，CAS是CPU的并发原语。
        System.out.println(atomicInteger.compareAndSet(2000, 2001));
        System.out.println(atomicInteger.get());
    }
}
