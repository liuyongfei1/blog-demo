package lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁现象，就是关于锁的8个问题- 第二个问题
 * sendSms方法延迟了4秒，该代码是 先执行 发短信，还是先执行 打电话呢？
 * synchronized，锁的对象是方法的调用者。因此两个方法锁的都是同一个对象。
 * 则谁先拿到锁谁先执行。
 *
 * 执行结果：
 * 发短信
 * 打电话
 * @author Liuyongfei
 * @date 2021/11/23 23:18
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone = new Phone2();

        new Thread(() -> {
            phone.sendSms();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone.phone();
        }).start();

    }
}

class Phone2 {

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
}
