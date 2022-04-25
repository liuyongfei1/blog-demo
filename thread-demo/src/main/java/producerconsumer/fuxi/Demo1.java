package producerconsumer.fuxi;

/**
 * 线程间的通信
 *     Object的 wait()、notifyAll()
 * 使用 Condition的 await()、signalAll()
 *
 * @author Liuyongfei
 * @date 2022/4/25 10:24
 */
public class Demo1 {

    public static void main(String[] args) {
        MyDataUseLock data = new MyDataUseLock();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"第一个线程").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"第二个线程").start();
    }
}
