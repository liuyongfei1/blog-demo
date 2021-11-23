package producerconsumer;

/**
 * 使用四个线程来进行测试 线程之间的通信：
 * 且 A执行完 -》通知B线程，B线程执行完 -》通知C线程，C线程执行完 -》通知D线程，D线程执行完
 *
 * @author Liuyongfei
 * @date 2021/11/23 09:09
 */
public class Demo2 {

    public static void main(String[] args) {
        MyDataUseLock myData = new MyDataUseLock();

        // 这个线程会调用10次 加的方法
        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    myData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }," A").start();


        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    myData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    myData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    myData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
