package unsafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 集合类不安全：
 *
 * List 不安全
 * @author Liuyongfei
 * @date 2021/11/24 13:21
 */
public class ListTest {

    public static void main(String[] args) {
        // 1.单线程下是安全的，没有问题的
//        List<String> list = Arrays.asList("1","2","3");
//        list.forEach(System.out::println);

        // 2.单线程下是安全的，没有问题的
//        List<String> list = new ArrayList<>();
//
//        for (int i = 1; i < 10; i++) {
//            list.add(UUID.randomUUID().toString().substring(0,5));
//        }
//
//        System.out.println(list);

        // 3. 改为多线程情况下 则会有报错
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
