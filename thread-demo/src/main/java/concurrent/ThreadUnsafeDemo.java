package concurrent;

/**
 * 线程不安全 => 解决办法：使用synchronized关键字
 *
 * @author Liuyongfei
 * @date 2021/8/18 23:34
 */
public class ThreadUnsafeDemo {

    private static int data = 0;

    public static void main(String[] args) throws Exception{
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (ThreadUnsafeDemo.class) {
                        ThreadUnsafeDemo.data++;
                        System.out.println(data);
                    }
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (ThreadUnsafeDemo.class) {
                        ThreadUnsafeDemo.data++;
                        System.out.println(data);
                    }
                }
            }
        };
        thread2.start();

        // 在很多情况下，主线程生成并启动了子线程，如果子线程需要j进行大量的耗时运算，主线程往往将于子线程之前就结束了
        // 但是如果主线程处理完其它的事务后，需要用到子线程的处理结果，也就是主线程需要等到子线程执行完毕之后才能结束，这个时候就可以使用join()方法。
        thread1.join();
        thread2.join();
    }
}
