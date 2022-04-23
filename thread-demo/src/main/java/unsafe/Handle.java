package unsafe;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/4/11 11:31
 */
public class Handle {

    private static int data = 0;

    public void increment() {
        System.out.println("hello");
        data++;
        System.out.println(Thread.currentThread().getName() + " => 执行加操作: " + data);
    }

    public void decrement() {
        data--;
        System.out.println(Thread.currentThread().getName() + " => 执行减操作: " + data);
    }
}
