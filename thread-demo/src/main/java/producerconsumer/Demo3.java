package producerconsumer;

/**
 * 使用Condition来对三个线程来进行测试 线程之间的通信：
 * 且 A执行完 -》通知B线程，B线程执行完 -》通知C线程，C线程执行完 -》通知A线程，A线程执行
 * 这样有序的循环执行
 *
 * @author Liuyongfei
 * @date 2021/11/23 09:09
 */
public class Demo3 {

    public static void main(String[] args) {
        MyDataUseLock2 myData = new MyDataUseLock2();

        // 这个线程会调用10次 加的方法
        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                myData.printA();
            }
        }," A").start();


        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                myData.printB();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                myData.printC();
            }
        },"C").start();

    }
}
