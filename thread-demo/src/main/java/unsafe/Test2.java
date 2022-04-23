package unsafe;

/**
 * 多个线程同时对一个变量进行操作的时候，可能会产生线程不安全的情况
 * 可以使用 synchronized 关键字来解决
 *
 * @author Liuyongfei
 * @date 2022/4/11 13:05
 */
public class Test2 {

    private static int data = 0;

    public synchronized void increase() {
        data++;
        System.out.println(Thread.currentThread().getName() + " => 执行加操作: " + data);
    }

    public synchronized void decrease() {
        data--;
        System.out.println(Thread.currentThread().getName() + " => 执行减操作: " + data);
    }


    public static void main(String[] args) {
        Test2 test2 = new Test2();

        // 执行加操作
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                test2.increase();
            }, "加操作线程").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                test2.decrease();
            },"减操作线程").start();
        }
    }
}
