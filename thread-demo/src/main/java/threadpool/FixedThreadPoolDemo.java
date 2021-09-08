package threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * fixed 线程池
 *
 * @author Liuyongfei
 * @date 2021/9/8 08:18
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程执行异步任务......" + Thread.currentThread());
                }
            });
        }
    }
}
