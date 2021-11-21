package single;

/**
 * 单例之 懒汉式模式
 *
 * @author Liuyongfei
 * @date 2021/11/21 17:36
 */
public class LazyMan {

    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + " 创建实例ok");
    }

    private static LazyMan lazyMan;

    public static LazyMan getInstance() {
        if (lazyMan == null) {
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }


    /**
     * 测试在多线程并发时，是不是这个实例就被创建了一次
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan.getInstance();
            }).start();
        }
    }

}
