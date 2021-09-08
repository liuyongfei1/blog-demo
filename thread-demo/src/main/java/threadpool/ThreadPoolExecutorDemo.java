package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor demo
 *
 * @author Liuyongfei
 * @date 2021/9/8 21:49
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        // 核心线程数
        int coolPoolSize = 3;

        // 最大线程数
        int maxmumPoolSize = 6;

        // 超过核心线程数的线程的最大空闲时间
        long keepAliveTime = 2;

        // 创建工作队列，用于存放提交的等待执行任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);

        // 以秒为时间单位
        TimeUnit unit = TimeUnit.SECONDS;

        ThreadPoolExecutor threadPoolExecutor = null;

        try {
            // 创建线程池
            threadPoolExecutor =  new ThreadPoolExecutor(coolPoolSize,
                    maxmumPoolSize,
                    keepAliveTime,
                    unit,
                    workQueue,
                    new ThreadPoolExecutor.AbortPolicy()
            );

            for (int i = 0; i < 8; i++) {
                final int index = i + 1;
                threadPoolExecutor.submit(() -> {
                    // 记录当前线程数的索引
                    System.out.println("线程：" + index);

                    try {
                        // 模拟线程执行任务,10s
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                // 每个线程提交任务之后，休眠500ms后再提交下一个任务，用于保证提交顺序
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
