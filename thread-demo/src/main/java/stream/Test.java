package stream;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 题目要求：一分钟内完成此题，只能用一行代码实现！
 * 现在有5个用户！筛选：
 * 1、ID 必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户！
 *
 * 使用lambda表达式、链式编程、函数式接口、stream流计算 来解决这个题目。
 *
 * @author Liuyongfei
 * @date 2021/11/20 15:11
 */
public class Test {
    public static void main(String[] args) {
        User user1  = new User(1, "a", 21);
        User user2  = new User(2, "b", 22);
        User user3  = new User(3, "c", 23);
        User user4  = new User(4, "d", 24);
        User user5  = new User(6, "e", 25);

        // 存入List中，集合就是存储
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

        // 计算交给Stream流
        users.stream()
                .filter(u -> { return  u.getId() % 2 == 0;})
                .filter(u -> { return  u.getAge() > 23;})
                .map(u -> { return  u.getName().toUpperCase();})
                .sorted((uu1,uu2) -> {return uu2.compareTo(uu1);})
                .limit(1)
                .forEach(System.out::println);
    }
}
