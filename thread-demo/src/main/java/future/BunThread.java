package future;

/**
 * 等包子
 *
 * @author Liuyongfei
 * @date 2021/8/11 16:38
 */
public class BunThread extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(1000 * 3);
            System.out.println("包子准备完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
