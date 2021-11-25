package callable;

import future.FutureTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用Callable
 *
 * @author Liuyongfei
 * @date 2021/11/17 22:40
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 之前使用Runnable的用法
//        new Thread(new MyThread()).start();

        // 如何使用Callable？
        MyThread thread  = new MyThread();
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        // 获取线程执行结果的返回值
        String result = (String) futureTask.get();
        System.out.println(result);
    }
}

//class MyThread implements Runnable {
//
//    @Override
//    public void run() {
//        System.out.printf("run()......");
//    }
//}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("call()......");
        return "123456";
    }
}