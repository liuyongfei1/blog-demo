package unsafe;

/**
 * 多个线程并发同时操作内存中的同一个变量时，有可能会产生错误的结果。
 * 比如data=0，一个线程执行+1，另一个线程也执行+1，期望的是 data =2，但最后的结果可能不是我们期望的。
 *
 * @author Liuyongfei
 * @date 2022/4/11 14:22
 */
public class Test3 {
    private int data = 0;

    public void increase() {
        data++;
        System.out.println(Thread.currentThread().getName() + " => 执行加操作: " + data);
    }

    public static void main(String[] args) {
        Test3 test3 = new Test3();

        new Thread(() -> {
           test3.increase();
        },"线程一").start();

        new Thread(() -> {
            test3.increase();
        },"线程二").start();
    }
}
