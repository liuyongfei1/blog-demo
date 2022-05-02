package volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/5/2 22:16
 */
public class MyData {

//    int num = 0;
    volatile int num = 0;

    public void add1() {
        this.num = 60;
    }


    // 即使 num 加了 volatile 关键字，我们的demo输出结果证明了是不能保证原子性的
    public void add2() {
        this.num++;
    }
    // 解决方法，在方法前面加上 synchronized 关键字
    // 备注：但是仅仅是一个++操作，使用 synchronized 杀鸡用牛刀，有点太重。
    // 可以使用 juc 包下面的原子类 AutomicInteger
//    public synchronized void add2() {
//        this.num++;
//    }

    AtomicInteger myInteger = new AtomicInteger();
    public void addMyInteger() {
        myInteger.getAndIncrement();
    }
}
