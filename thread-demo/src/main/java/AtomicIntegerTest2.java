/**
 * volatile 保证变量在线程间可见，对volatile变量所有的写操作都能立即反应到其他线程中。
 * 换句话说，volatile变量在各个线程中是一致的。
 *
 * 该demo结论：换成volatile修饰count变量后，每次输出的仍是小于20000的数字。
 * 由此看来，并不能得出"基于volatile变量的运算在并发下是安全的"这个结论，因为核心点在于java里的运算（比如自增）并不是原子性的。
 *
 * @author Liuyongfei
 * @date 2021/7/7 17:45
 */
public class AtomicIntegerTest2 {
    private static final int THREADS_COUNT = 20;
    public static volatile int count = 0;

    public static void increase() {
        count++;
    }

    public static void main(String[] args) {
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
        System.out.println(count);
    }
}
