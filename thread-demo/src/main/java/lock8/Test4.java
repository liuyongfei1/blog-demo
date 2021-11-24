package lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁现象，就是关于锁的8个问题- 第四个问题
 * sendSms方法延迟了4秒
 * synchronized，锁的对象是方法的调用者。
 * 有两个对象，分别调用sendSms和phone,先输出发短信，还是打电话呢？
 *
 *
 * 执行结果：
 * 打电话
 * 发短信
 * @author Liuyongfei
 * @date 2021/11/23 23:18
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        new Thread(() -> {
            phone1.sendSms();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone2.phone();
        }).start();

    }
}

class Phone4 {

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
