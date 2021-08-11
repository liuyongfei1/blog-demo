package future;

/**
 * 等凉菜
 *
 * @author Liuyongfei
 * @date 2021/8/11 16:40
 */
public class ColdDishThread extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("凉菜准备完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
