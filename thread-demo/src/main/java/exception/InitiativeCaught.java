package exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主动捕获线程异常
 *
 * ① 在run()中设置对应的异常处理，主动方法来解决未检测异常
 *
 * @author Liuyongfei
 * @date 2021/11/16 08:15
 */
public class InitiativeCaught {

    public static void main(String[] args) {
        InitialtiveThread thread = new InitialtiveThread();

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(thread);
        exec.shutdown();
    }
}

class InitialtiveThread implements Runnable {

    @Override
    public void run() {
        Throwable throwable = null;
        try {
            System.out.println( 3 / 2);
            System.out.println( 3 / 1);
            System.out.println( 3 / 0);
        } catch (Exception e) {
            throwable = e;
        } finally {
            threadDeal(throwable);
        }
    }

    public void threadDeal(Throwable  t) {
        System.out.println("==Exception: " + t.getMessage());
    }
}
