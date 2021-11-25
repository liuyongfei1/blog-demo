package unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 集合类不安全：
 *
 * Map不安全
 *
 * @author Liuyongfei
 * @date 2021/11/25 08:55
 */
public class MapTest {
    public static void main(String[] args) {

//        Map<String, String> map = new HashMap();

        // 解决办法一：
        Map<String, String> map = new ConcurrentHashMap<>();

//        for (int i = 1; i < 20; i++) {
//            map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
//
//            System.out.println(map);
//        }

        // 多线程并发的情况下，会报错
        // java.util.ConcurrentModificationException
        for (int i = 1; i < 20; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));

                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
