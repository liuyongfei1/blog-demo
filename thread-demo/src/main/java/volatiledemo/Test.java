package volatiledemo;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/4/21 15:33
 */
public class Test {

    private volatile static int flag = 0;

    public static void main(String[] args) {

        // 线程1读数据
        new Thread(() -> {
            int localFlag = flag;
            while (localFlag != flag) {
                System.out.println("读取到了修改后的标志位：" + flag);
                localFlag = flag;
            }
        },"线程1").start();

        // 线程2读数据
        new Thread(() -> {
            int localFlag = flag;
            while (true) {
                System.out.println("标志位被修改了：" + ++flag);
                localFlag = flag;
            }
        },"线程2").start();
    }
}
