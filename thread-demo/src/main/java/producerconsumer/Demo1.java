package producerconsumer;

/**
 * 使用两个线程来进行测试 线程之间的通信
 *
 * @author Liuyongfei
 * @date 2021/11/23 09:09
 */
public class Demo1 {

    public static void main(String[] args) {
//        MyDataUseSynchronized myData = new MyDataUseSynchronized();
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
        }," T1").start();


        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    myData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T2").start();
    }
}
