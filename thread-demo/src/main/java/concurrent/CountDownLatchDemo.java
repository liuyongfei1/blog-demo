package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 一个线程启动了多个线程来执行任务，且该线程需要阻塞等待其它的线程执行完毕后才能继续向下走
 *
 * 这时就可以用CountDownLatch。
 * @author Liuyongfei
 * @date 2021/8/31 19:41
 */
public class CountDownLatchDemo {

    public static void main(String[] args){
        CountDownLatch cdt = new CountDownLatch(3);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                    System.out.println("线程一执行完毕");
                    cdt.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                    System.out.println("线程二执行完毕");
                    cdt.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                    System.out.println("线程三执行完毕");
                    cdt.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("main线程被阻塞");
                    cdt.await();
                    System.out.println("main线程被唤醒，可继续向下执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
