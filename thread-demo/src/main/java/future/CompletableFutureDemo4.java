package future;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 使用自定义线程池
 *
 * @author Liuyongfei
 * @date 2022/3/25 18:07
 */
public class CompletableFutureDemo4 {

    private static final int PERMITS = 30;
    private static final AtomicBoolean initializedRef = new AtomicBoolean(false);
    private static ThreadPoolExecutor THREAD_POOL_EXECUTOR = null;

    private static final Supplier<ThreadPoolExecutor> THREAD_POOL_EXECUTOR_SUPPLIER = () -> {
        if (initializedRef.compareAndSet(false, true)) {
            THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
                    PERMITS,
                    PERMITS * 2,
                    60,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(1000),
                    NamedDaemonThreadFactory.getInstance("consumePromotionMsg"),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );
        }
        return THREAD_POOL_EXECUTOR;
    };

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        System.out.println(THREAD_POOL_EXECUTOR_SUPPLIER);

        // 创建第一个异步执行任务
        // 使用自定义线程池
        CompletableFuture<AltResult> cf1 = CompletableFuture.supplyAsync(
                () -> queryCode("中国石油")
                , THREAD_POOL_EXECUTOR_SUPPLIER.get()
        );

        // 如果任务执行成功
        cf1.thenAccept((result) -> {
            System.out.println(result);
        });

        // 如果执行失败
        cf1.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });

        Thread.sleep(5000);

    }

    static AltResult queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            return new AltResult(e);
        }
//        return "601857:" + name;
        System.out.println("执行查询完成...");
        return new AltResult(null);
    }

    /**
     * completableFuture的返回结果，适用于无返回值的情况
     * ex字段为null表示任务执行成功
     * ex字段不为null表示任务执行失败，并把异常设置为ex字段
     */
    static class AltResult {

        final Throwable ex;

        public AltResult(Throwable ex) {
            this.ex = ex;
        }
    }
}
