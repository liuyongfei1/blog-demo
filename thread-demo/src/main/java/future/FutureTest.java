package future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Future模式测试
 * Future表示一个可能还没有完成的异步任务的结果，针对这个结果可以添加Callback以便在任务执行成功或失败后作出相应的操作。
 *
 * 输出结果：
 * 凉菜准备完毕
 * 包子完毕
 * 准备完毕时间：3007
 * @author Liuyongfei
 * @date 2021/8/11 17:36
 */
public class FutureTest {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();

            // 等凉菜
            Callable ca1 = new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(1000);
                    return "凉菜准备完毕";
                }
            };
            FutureTask<String> f1 = new FutureTask<String>(ca1);
            new Thread(f1).start();

            // 等包子
            Callable ca2 = new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(3 * 1000);
                    return "包子完毕";
                }
            };
            FutureTask<String> f2 = new FutureTask<String>(ca2);
            new Thread(f2).start();

            System.out.println(f1.get());
            System.out.println(f2.get());

            long end = System.currentTimeMillis();
            System.out.println("准备完毕时间：" + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
