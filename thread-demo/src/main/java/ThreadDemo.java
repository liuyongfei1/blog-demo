/**
 * 多线程原理：相当于玩游戏机，只有一个游戏机(cpu)，可是有很多人要玩。于是，start是排队，等cpu选中你就是轮到你，你就run()
 * 当cpu的运行的时间片执行完，这个线程就继续排队，等待下一次的run()
 * 1、start()方法来启动线程，调用start方法后，线程会被放到等待队列进行排队。等待CPU调度，并不一定是马上开始执行，只是将这个线程置于可运行状态
 * 然后通过JVM，线程Thread会调用run()方法，执行本线程的线程体
 *
 * 2、run()方法当做普通方法的方式调用。线程还要顺序执行，要等待run()方法执行完毕后，才可以继续执行下面的代码。
 * 程序中只有主线程这一个线程，其程序执行路径还是只有一条，这样就没有达到写线程的目的。
 * 3、runnable 其实就相当于一个task，并不具有线程的概念，如果直接调用runnable的run方法，其实就是相当于直接在主线程中执行一个函数而已，并未开启线程去执行。
 * @author Liuyongfei
 * @date 2022/3/16 17:45
 */
public class ThreadDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread()).start();
        }
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("myThread run......" +  Thread.currentThread().getName());
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("myThread run......" +  Thread.currentThread().getName());
    }
}