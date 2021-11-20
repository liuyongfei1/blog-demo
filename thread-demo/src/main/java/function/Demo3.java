package function;

import java.util.function.Consumer;

/**
 * 函数式接口之
 * Consumer：消费型接口，接收参数，没有返回值
 *
 * @author Liuyongfei
 * @date 2021/11/20 14:28
 */
public class Demo3 {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//
//            @Override
//            public void accept(String s) {
//                System.out.println("接收参数: " + s);
//            }
//        };
        // 使用lambda表达式简化
        Consumer<String> consumer = (s) -> {System.out.println("接收参数: " + s);};
        consumer.accept("hello");
    }
}
