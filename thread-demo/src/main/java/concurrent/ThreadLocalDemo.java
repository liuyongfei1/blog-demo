package concurrent;

/**
 * ThreadLocal Demo
 *
 * @author Liuyongfei
 * @date 2021/8/25 09:31
 */
public class ThreadLocalDemo {

    private static ThreadLocal<Long> requestId = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                requestId.set(1L);
                System.out.println("线程：" + Thread.currentThread() + "输出：" + requestId.get());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                requestId.set(2L);
                System.out.println("线程：" + Thread.currentThread() + "输出：" + requestId.get());
            }
        }.start();
    }
}
