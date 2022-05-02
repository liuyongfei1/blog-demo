package producerconsumer.fuxi;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/4/29 15:29
 */
public class Test {

    public static void main(String[] args) {
        MyDataUseLock2 data = new MyDataUseLock2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();
    }
}
