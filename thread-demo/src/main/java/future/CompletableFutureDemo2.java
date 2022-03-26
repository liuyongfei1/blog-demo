package future;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.*;

/**
 * CompletableFuture 可以异步执行多个任务
 *
 * 可见CompletableFuture的优点是：
 *
 *   1、异步任务结束时，会自动回调某个对象的方法；
 *   2、异步任务出错时，会自动回调某个对象的方法；
 *   3、主线程设置好回调后，不再关心异步任务的执行。
 *
 * @author Liuyongfei
 * @date 2022/3/25 17:35
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // 创建异步执行任务
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureDemo2::fetchPrice);

        // 如果执行成功
        cf.thenAccept((result) -> {
            System.out.println("price:" + result);
        });
        // 如果执行失败
        cf.exceptionally((e) -> {
           e.printStackTrace();
           return null;
        });

        // 主线程不要立即介绍，否则CompletableFuture默认使用d的线程池h会立刻关闭
        sleep(200);
    }

    static double fetchPrice() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed");
        }
        return 5 + Math.random() * 20;
    }
}


