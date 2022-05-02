package volatiledemo;

/**
 * volatile 可见性demo
 *
 * 在新建的线程里对变量进行加操作，然后 main线程里读取变量的值
 *
 *
 * 不加 volatile 关键字时，会一直在 while循环这卡死
 * 加上 volatile关键字后， main线程就可以读取到修改后的值
 * @author Liuyongfei
 * @date 2022/5/2 22:13
 */
public class KeJianXing {

    public static void main(String[] args) {
        MyData data = new MyData();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " come in");
                // sleep3秒钟，3秒后将num值改为60 =》
                // 备注：这行sleep的代码很关键，没有这行代码，有可能就达不到demo的效果
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.add1();
            System.out.println(Thread.currentThread().getName() + "读取到的num值为:" + data.num);
        },"线程A").start();

        // 在线程A sleep的这3秒钟的 这个过程中，main线程也读取了这个变量
        while (data.num == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "读取到的num值为：" + data.num);
    }
}
