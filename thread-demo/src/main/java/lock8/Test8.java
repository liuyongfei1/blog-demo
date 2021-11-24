package lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁现象，就是关于锁的8个问题- 第八个问题
 * sendSms方法延迟了4秒
 * synchronized，锁的对象是方法的调用者。
 * 一个静态的同步方法，一个普通的同步方法，两个对象调用,先输出发短信，还是打电话呢？
 *
 *
 * 执行结果：
 * 打电话
 * 发短信
 * @author Liuyongfei
 * @date 2021/11/23 23:18
 */
public class Test8 {
    public static void main(String[] args) throws InterruptedException {
        Phone8 phone1 = new Phone8();
        Phone8 phone2 = new Phone8();

        new Thread(() -> {
            phone1.sendSms();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone2.phone();
        }).start();

    }
}

class Phone8 {

    /**
     * 锁的是 这个 Class模板
     */
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    /**
     * 锁的是方法的调用者
     */
    public synchronized void phone() {
        System.out.println("打电话");
    }

}
