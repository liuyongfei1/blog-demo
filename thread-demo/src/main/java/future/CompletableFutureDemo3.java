package future;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

/**
 * CompletableFuture 可以异步执行多个任务
 *
 * 可见CompletableFuture的优点是：
 *
 *   1、异步任务结束时，会自动回调某个对象的方法；
 *   2、异步任务出错时，会自动回调某个对象的方法；
 *   3、主线程设置好回调后，不再关心异步任务的执行。
 *   4、如果只是实现了异步回调机制，我们还看不出CompletableFuture相比Future的优势。
 *      CompletableFuture更强大的功能是，多个CompletableFuture可以串行执行
 *
 * @author Liuyongfei
 * @date 2022/3/25 17:35
 */
public class CompletableFutureDemo3 {
    public static void main(String[] args) throws InterruptedException {
        // 创建第一个异步执行任务
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });

        // cf1 执行成功后继续执行下一个任务
        CompletableFuture<Double> cf2 = cf1.thenApplyAsync((code) -> {
            return fetchPrice(code);
        });


        // cf2成功后打印结果
        cf2.thenAccept((result) -> {
            System.out.println("price: " + result);
        });

        // 主线程不要立即介绍，否则CompletableFuture默认使用d的线程池h会立刻关闭
        Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static double fetchPrice(String code) {
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


