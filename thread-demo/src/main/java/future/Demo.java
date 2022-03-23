package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步回调：CompletableFuture
 *
 * 类似于 Ajax
 * @author Liuyongfei
 * @date 2021/11/20 19:58
 */
public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "runAsync()");
//        });

//        System.out.println("hello");

        // 获取阻塞执行结果
//        completableFuture.get();

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);

                int a = 100 / 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "supplyAsync()");
            return 666;
        });

        Integer data = completableFuture.whenComplete((t,u) -> {
            System.out.println("t->" + t);  // t是正常时的返回结果
            System.out.println("u->" + u); //  u是发生异常时的异常信息
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 111;  // 可以获取到错误时的返回结果
        }).get();

        System.out.println(data);

        // ***********test
        // ***********test

    }
}
