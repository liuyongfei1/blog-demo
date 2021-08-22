package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger demo
 *
 * @author Liuyongfei
 * @date 2021/8/22 16:45
 */
public class AtomicIntegerDemo {

    private static long i = 0;

    private static AtomicInteger j = new AtomicInteger(0);

    public static void main(String[] args) {
//        synchronizedAdd();
        atomicIntegerAdd();
    }

    public static void synchronizedAdd() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    synchronized (AtomicIntegerDemo.class) {
                        System.out.println(AtomicIntegerDemo.i++);
                    }
                }
            }.start();
        }
    }

    public static void atomicIntegerAdd() {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(AtomicIntegerDemo.j.incrementAndGet());
                }
            }.start();
        }
    }
}
