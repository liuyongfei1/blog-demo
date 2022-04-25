import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟耗时任务异步执行
 * 耗时任务开启单独线程处理，任务线程处理完毕通知主线程
 * @author Liuyongfei
 * @date 2020/6/10 14:56
 */
public class Test1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new TestThread(new ResponseCallBack() {
            @Override
            public void printMsg(String msg) {
                System.out.println("print message: " + msg);
            }
        }));


        ReentrantLock lock = new ReentrantLock();
    }
}

