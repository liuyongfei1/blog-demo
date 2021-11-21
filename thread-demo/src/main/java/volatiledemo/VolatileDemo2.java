package volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * 本demo有两个线程：
 * main线程和自己新建的一个线程
 * 1.num没有加volatile，在main线程中修改num的值，另外一个线程是感知不到num发生变化的，因此一直在死循环，JVM进程也不退出
 * 2.num加volatile，在main线程中修改num的值，另外一个线程是感知不到num发生变化的，因此，while条件不成立了，所以JVM进程也退出了。
 * @author Liuyongfei
 * @date 2021/11/21 15:06
 */
public class VolatileDemo2 {

//    private static int num  = 0;
    private static volatile int num  = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
