package unsafe;

/**
 * 多个线程同时对一个变量进行操作的时候，可能会产生线程不安全的情况
 *
 * 比如：
 *
 * 加操作线程 => 执行加操作: 2
 * 加操作线程 => 执行加操作: 3
 * 加操作线程 => 执行加操作: 2
 * 加操作线程 => 执行加操作: 4
 * 加操作线程 => 执行加操作: 5
 * 加操作线程 => 执行加操作: 6
 * 加操作线程 => 执行加操作: 7
 * 加操作线程 => 执行加操作: 8
 * 加操作线程 => 执行加操作: 9
 * 加操作线程 => 执行加操作: 10
 * 减操作线程 => 执行减操作: 9
 * 减操作线程 => 执行减操作: 8
 * 减操作线程 => 执行减操作: 7
 * 减操作线程 => 执行减操作: 6
 * 减操作线程 => 执行减操作: 5
 * 减操作线程 => 执行减操作: 4
 * 减操作线程 => 执行减操作: 3
 * 减操作线程 => 执行减操作: 2
 * 减操作线程 => 执行减操作: 1
 * 减操作线程 => 执行减操作: 0
 *
 * @author Liuyongfei
 * @date 2022/4/11 11:39
 */
public class Test1 {

    private static int data = 0;


    public static void main(String[] args) {

        // 执行加操作
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                data++;
                System.out.println(Thread.currentThread().getName() + " => 执行加操作: " + data);
            }, "加操作线程").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                data--;
                System.out.println(Thread.currentThread().getName() + " => 执行减操作: " + data);
            },"减操作线程").start();
        }
    }
}
