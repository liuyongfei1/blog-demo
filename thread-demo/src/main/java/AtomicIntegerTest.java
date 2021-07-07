/**
 * 模拟多线程同时对一个变量进行操作
 * 这里运行20个线程，每个线程对count变量进行1000次自增操作
 * 如果这段代码能够正常并发的话，最后的结果应该是20000才对，但实际结果却发现每次运行的结果都不相同，都是一个小于20000的数字。
 * @author Liuyongfei
 * @date 2021/7/7 17:30
 */
public class AtomicIntegerTest {
    private static final int THREADS_COUNT = 20;
    public static int count = 0;

    public static void increase() {
        count++;
    }

    public static void main(String[] args) {
        System.out.println("test");

        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }

        System.out.println(count);
    }
}
