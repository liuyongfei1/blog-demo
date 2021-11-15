package exception;

/**
 * 多线程发生异常，无法使用try ...catch捕获异常
 *
 * @author Liuyongfei
 * @date 2021/11/15 23:35
 */
public class NoCaughtThread implements Runnable{
    @Override
    public void run() {
        System.out.println( 3 / 2);
        System.out.println( 3 / 1);
        System.out.println( 3 / 0);
    }

    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new NoCaughtThread());
            thread.start();
        } catch (Exception e) {
            System.out.println("==Exception: " + e.getMessage());
        }
    }
}
