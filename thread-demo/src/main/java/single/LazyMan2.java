package single;

import java.lang.reflect.Constructor;

/**
 * 单例之 双重检测的锁模式的懒汉式单例 DCL单例模式 + volatile防止指令重排
 *
 * @author Liuyongfei
 * @date 2021/11/21 17:36
 */
public class LazyMan2 {

    private LazyMan2() {

        synchronized (LazyMan2.class) {
            if (lazyMan != null) {
                throw new RuntimeException("不要试图用反射破坏单例");
            }
        }
    }

    private volatile static LazyMan2 lazyMan;

    public static LazyMan2 getInstance() {

        // 双重检测的锁模式的懒汉式单例 DCL单例模式
        if (lazyMan == null) {
            synchronized (LazyMan2.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan2(); // 不是原子性操作。
                    // 底层会分为3步骤：
                    // 1.分配内存空间
                    // 2.执行构造方法，初始化对象
                    // 3.把这个对象指向这个空间
                    // 看上去的一行代码，底层其实要执行这三步操作。=》 因此，就有可能会发生指令重排造成问题。
                    // 比如，期望的执行步骤123
                    // 但是线程A可能执行的顺序是132，当线程A执行第3步后，
                    // 此时，线程B进来了，由于线程A已经执行了第3布，因此线程B进来时就会任务 lazyMan不等于null，则就会直接return了。
                    // 但此时这个lazyMan还没有完成构造，空间是一篇虚无的，就有可能会产生问题。

                    // 那么怎么去解决呢？ =》防止指令重排，给lazyMan修饰符加上 volatile即可解决。

                }
            }
        }
        return lazyMan;
    }


    /**
     * 测试在多线程并发时，是不是这个实例就被创建了一次
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                LazyMan2.getInstance();
//            }).start();
//        }

        LazyMan2 instance = LazyMan2.getInstance();

        // 利用反射来得到单例
        // 获取空参构造器
        Constructor<LazyMan2> declaredConstructor = LazyMan2.class.getDeclaredConstructor(null);

        declaredConstructor.setAccessible(true); // 这一步操作可以无视私有构造器

        // 利用反射来得到单例
        LazyMan2 instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }

}
