package synchronizeddemo;

/**
 * 演示：
 * 1、Runnable 可以实现多个相同的程序代码的线程去共享一个资源
 * 2、Thread 也可以实现多个相同的程序代码的线程去共享一个资源
 *
 * @author Liuyongfei
 * @date 2021/10/11 20:43
 */
public class Demo1 {
    public static void main(String[] args) {
        MyRunnable r1 = new MyRunnable();
        new Thread(r1, "线程1").start();
        new Thread(r1, "线程2").start();
//        MyThread thread = new MyThread();
//        new Thread(thread, "线程1").start();
//        new Thread(thread, "线程2").start();
    }

    public static class MyRunnable implements Runnable {

        private int total = 10;

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (this) {
                    if (total > 0) {
                        try {
                            Thread.sleep(100);
                            System.out.println(Thread.currentThread().getName() + "卖票：------>" + (this.total--));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class MyThread extends Thread {

        private int total = 10;

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (this) {
                    if (total > 0) {
                        try {
                            Thread.sleep(100);
                            System.out.println(Thread.currentThread().getName() + "卖票：------>" + (this.total--));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
