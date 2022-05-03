package volatiledemo;

/**
 * volatile 不保证原子性demo
 *
 * volatile 还有一个重要的使用场景：单例模式 DCL里的  instance 要加 volatile，来禁止指令重排
 *
 * @author Liuyongfei
 * @date 2022/5/2 22:44
 */
public class YuanZiXing {

    public static void main(String[] args) {
        MyData data = new MyData();
        // 执行 add2方法，用20个线程，每个线程循环1000次，我们首先会想到num最后的值为 20000

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.add2();

                    // 解决 volidate 不能保证原子性的问题
                    data.addMyInteger();
                }
            }).start();
        }

        // 需要等待上面20个线程全部多计算完毕之后，再用main线程取得最终的结果值看看是是多少
        // 第一种办法是直接用 Thread.sleep()，但是这个时间你得预估，不准确
        // 第二种办法就是下面这样：
        // 这三行代码是多线程编码中，等待多线程执行完的 常用代码。
        // 默认后台有两个线程：main线程 + gc线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        // 会发现，最终的输出结果，并不是我们期望的 20000。=》所以，volatile 不保证原子性
        System.out.println(Thread.currentThread().getName() + "int type，finally num value:" + data.num);
        // 这个输出结果是我们预期的 20000
        System.out.println(Thread.currentThread().getName() + "AtomicInteger type finally num value:" + data.myInteger);
    }
}
