package concurrent;

/**
 * double check的单例模式
 *
 * @author Liuyongfei
 * @date 2021/8/18 22:35
 */
public class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {}

    public DoubleCheckSingleton getInstance() {
        if (instance == null) {
            // 多个线程会卡在这里
            synchronized (DoubleCheckSingleton.class) {
                // 第一个线程进来，发现instance为空，就创建一个实例
                // 这是第二个线程会进来，如果不加上这个if判断，就会再创建一遍实例。
                if (instance == null) {
                    DoubleCheckSingleton.instance = new DoubleCheckSingleton();
                }
            }
            // 第一个线程执行完毕，退出synchronized代码块，会是否synchronized锁
        }
        return instance;
    }
}
