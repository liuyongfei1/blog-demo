package lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁现象，就是关于锁的8个问题- 第二个问题
 * sendSms方法延迟了4秒
 * synchronized，锁的对象是方法的调用者。
 * 但是B线程调用的是同一个方法，这时是先输出发短信，还是先输出hello呢？
 *
 *
 * 执行结果：
 * hello
 * 发短信
 * @author Liuyongfei
 * @date 2021/11/23 23:18
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        Phone3 phone = new Phone3();

        new Thread(() -> {
            phone.sendSms();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
//            phone.phone();
            phone.hello();
        }).start();

    }
}

class Phone3 {

    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void phone() {
        System.out.println("打电话");
    }

    public void hello() {
        System.out.println("hello");
    }
}
