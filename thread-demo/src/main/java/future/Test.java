package future;

/**
 * 测试类
 * 1.未使用join()方法
 * 准备完毕时间：0
 * 凉菜准备完毕
 * 包子准备完毕
 *
 * 2.使用 join()方法
 *
 * 凉菜准备完毕
 * 包子准备完毕
 * 准备完毕时间：4009
 * @author Liuyongfei
 * @date 2021/8/11 16:41
 */
public class Test {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();

            // 等凉菜 -- 必须要等待返回的结果，所以要调用join方法
            Thread t1 = new ColdDishThread();
            t1.start();
            t1.join();

            // 等包子 -- 必须要等待返回的结果，所以要调用join方法
            Thread t2 = new BunThread();
            t2.start();
            t2.join();

            long end = System.currentTimeMillis();
            System.out.println("准备完毕时间：" + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
