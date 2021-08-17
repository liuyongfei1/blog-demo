package volatiledemo;

/**
 * Volatile demo
 *
 * @author Liuyongfei
 * @date 2021/8/17 22:28
 */
public class VolatileDemo {
    private volatile static int flag = 0;
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                int localFlag = flag;
                while (true) {
                    if (localFlag != flag) {
                        System.out.println("读取到了修改后的标志位：" + flag);
                        localFlag = flag;
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                int localFlag = flag;
                while (true) {
                    System.out.println("标志位被修改了：" + ++localFlag);
                    flag = localFlag;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
