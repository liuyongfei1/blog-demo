package forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 测试
 * 1、普通方式
 * 2、ForkJoin方式
 * 3、Stream并行流
 * Stream并行流的效果比其他方式高很多
 * @author Liuyongfei
 * @date 2021/11/20 19:13
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1(); // sum=500000000500000000,耗费时间=5223
//          test2(); // sum=500000000500000000,耗费时间=7525
        test3(); // sum=500000000500000000,耗费时间=818
    }

    /**
     * 普通程序员的常规做法
     */
    public static void test1() {
        long start = System.currentTimeMillis();
        Long sum = 0L;

        for (int i = 1; i <= 10_0000_0000 ; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ",耗费时间=" + (end - start));
    }

    /**
     * 会使用ForkJoin的程序员
     */
    public static void  test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);

        ForkJoinTask<Long> submit = pool.submit(task);

        Long data = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum=" + data + ",耗费时间=" + (end - start));
    }

    public static void test3() {
        long start = System.currentTimeMillis();

        // Stream并行流
        long sum = LongStream.rangeClosed(0L, 10_0000_0000).parallel().reduce(0,Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ",耗费时间=" + (end - start));
    }
}
