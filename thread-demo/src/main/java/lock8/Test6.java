package lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁现象，就是关于锁的8个问题- 第五个问题
 * sendSms方法延迟了4秒
 * synchronized，锁的对象是方法的调用者。
 * 增加两个静态的同步方法，只有一个对象,先输出发短信，还是打电话呢？
 *
 *
 * 执行结果：
 * 发短信
 * 打电话
 * @author Liuyongfei
 * @date 2021/11/23 23:18
 */
public class Test6 {
    public static void main(String[] args) throws InterruptedException {

        // 两个对象的Class模板只有一个
        Phone6 phone1 = new Phone6();
        Phone6 phone2 = new Phone6();

        new Thread(() -> {
            phone1.sendSms();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone2.phone();
        }).start();

    }
}

class Phone6 {

    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void phone() {
        System.out.println("打电话");
    }

    public void hello() {
        System.out.println("hello");
    }
}
