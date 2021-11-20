package function;

import java.util.function.Supplier;

/**
 * 函数式接口之
 *
 * Supplier：供给型接口 没有参数，只有返回值
 *
 * @author Liuyongfei
 * @date 2021/11/20 14:38
 */
public class Demo4 {
    public static void main(String[] args) {
//        Supplier<Integer> supplier = new Supplier<Integer>() {
//
//            /**
//             * Gets a result.
//             *
//             * @return a result
//             */
//            @Override
//            public Integer get() {
//                System.out.println("get()");
//                return 1024;
//            }
//        };

        // 使用lambda表达式简化
        Supplier<Integer> supplier = () -> {return 1024;};

        System.out.println(supplier.get());
    }
}
