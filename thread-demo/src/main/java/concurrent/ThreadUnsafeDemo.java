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
                    }
                    System.out.println(data);
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
                    }
                    System.out.println(data);
                }
            }
        };
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
