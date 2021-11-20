package pool;

import java.util.concurrent.*;

/**
 * 线程池demo
 *
 * 使用线程池来创建线程
 *
 * 讲解线程池的7大参数，用去银行办理业务的例子来学习
 *
 * 比如银行有2个窗口可以办理业务，有3个窗口暂时不提供服务，然后候客区最多可以容纳3人。
 *
 * 1、这时有1个人来办理业务，正常
 * 2、有5个人来办理业务时，则有2个人可以去2个窗口办理业务，剩下3个人在候客区等待，对应到这里就是会开启2个线程来处理业务，剩下3个人在阻塞队列排队等待
 * 3、有6个人来办理业务时，则开启窗口：原来的2个窗口 + 原来暂时不提供服务的3个窗口会恢复1个窗口提供服务 =》 就会触发3个线程来执行了；
 * 3、有7个人来办理业务时，则开启窗口：原来的2个窗口 + 原来暂时不提供服务的3个窗口会恢复2个窗口提供服务 =》 就会触发4个线程来执行了；
 * 3、有8个人来办理业务时，则开启窗口：原来的2个窗口 + 原来暂时不提供服务的3个窗口会恢复3个窗口提供服务，注意，这时所有的窗口已经全部提供服务了 =》 就会触发5个线程来执行了；
 *
 * 最大承载：等于阻塞队列长度 + maximumPoolSize = 3 + 5 = 8
 *
 * 超出最大承载（8个），在这种策略情况下，会拒绝，并抛出异常
 * 比如，有第9个人来办理业务
 * 手动创建线程池
 * @author Liuyongfei
 * @date 2021/11/19 08:51
 */
public class Demo1 {

    public static void main(String[] args) {
//        ExecutorService threadPool =  Executors.newSingleThreadExecutor();

//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//
//        ExecutorService threadPool = Executors.newCachedThreadPool();

        // 手动创建一个线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略(比如银行的窗口人满了，候客区也满了，这时再有人想办理业务就会被拒绝，不处理这个人的，抛出异常)
//                new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里，（main线程调用的，则就丢给main线程来执行）
//                new ThreadPoolExecutor.DiscardPolicy() // 队列满了，尝试和最早的去竞争，也不会抛出异常。
        );

        try {
            for (int i = 1; i <= 8 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
