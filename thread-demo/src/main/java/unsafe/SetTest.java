package unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全：
 *
 * Set不安全
 *
 * @author Liuyongfei
 * @date 2021/11/24 23:42
 */
public class SetTest {
    public static void main(String[] args) {

//        Set<String> set = new HashSet<>();

        // 解决方案一：使用集合工具类，转为安全的
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());

        // 解决方案二：使用集合工具类，转为安全的
        Set<String> set = new CopyOnWriteArraySet<>();

        // 单线程情况下没问题
//        for (int i = 1; i < 20; i++) {
//            set.add(UUID.randomUUID().toString().substring(0,5));
//            System.out.println(set);
//        }

        // 多线程情况下，报：
        // java.util.ConcurrentModificationException
        for (int i = 1; i < 20; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            }).start();

        }

    }
}
