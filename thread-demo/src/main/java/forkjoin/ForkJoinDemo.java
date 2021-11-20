package forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 使用ForkJoin求和计算的任务
 *
 * @author Liuyongfei
 * @date 2021/11/20 18:12
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 临界值
     */
    private Long tmp = 10000L;

    /**
     * 任务的计算逻辑
     *
     * @return 返回计算结果
     */
    @Override
    protected Long compute() {

        if ((end - start) < tmp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 使用ForkJoin 递归计算
            long middle = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);

            // 拆分任务，把任务压入线程队列
            task1.fork();

            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            // 拆分任务，把任务压入线程队列
            task2.fork();
            return  task1.join() + task2.join();
        }
    }
}
