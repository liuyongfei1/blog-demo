package lock;

/**
 * 可重入锁-synchronized
 *
 * @author Liuyongfei
 * @date 2021/11/22 22:27
 */
public class ReentrantDemo1 {

    public static void main(String[] args) {
        ReentrantDemo1 demo = new ReentrantDemo1();
        demo.test();
    };

    public void test() {
        new Thread(() -> {
            synchronized (this) {
                System.out.println("第一次获得这个锁，这个锁是" + this);

                int index = 1;

                while (true) {
                    synchronized (this) {
                        System.out.println("第" + (++index) + "次获得这个锁，这个锁是" + this);

                        if (index == 10) {
                            break;
                        }
                    }
                }
            }

        }).start();
    }
}
