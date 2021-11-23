package producerconsumer;

/**
 * 这里写业务逻辑，
 *
 * 一个执行加操作的方法： 0 -》1
 * 一个执行减操作的方法： 1 -》0
 *
 * 判断是否需要等待
 *
 * 业务
 *
 * 通知
 * @author Liuyongfei
 * @date 2021/11/23 08:57
 */
public class MyData {

    private int number = 0;


    /**
     * 执行加操作
     */
    public synchronized void increment() throws InterruptedException {

        if (number != 0) {
            // 等待
            this.wait();
        }

        number++;

        System.out.println(Thread.currentThread().getName() + "=>" + number);

        // 通知其它线程，我+1完毕了
        this.notifyAll();
    }

    /**
     * 执行减操作
     */
    public synchronized void decrement() throws InterruptedException {

        if (number == 0) {
            // 等待
            this.wait();
        }

        number--;

        System.out.println(Thread.currentThread().getName() + "=>" + number);

        // 通知其它线程，我-1完毕了
        this.notifyAll();
    }
}
