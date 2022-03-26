package future;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义一个线程池工厂
 *
 * @author Liuyongfei
 * @date 2022/3/25 18:10
 */
public class NamedDaemonThreadFactory implements ThreadFactory {

    private final String name;

    private final AtomicInteger counter = new AtomicInteger(0);

    public NamedDaemonThreadFactory(String name) {
        this.name = name;
    }

    public static NamedDaemonThreadFactory getInstance(String name) {
        Objects.requireNonNull(name, "必须要传一个线程名字的前缀");
        return new NamedDaemonThreadFactory(name);
    }

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
