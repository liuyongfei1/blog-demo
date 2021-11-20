package function;

import java.util.function.Predicate;

/**
 * 函数式接口之
 * Predicate： 断定型接口，传入一个参数，返回一个boolean值
 * @author Liuyongfei
 * @date 2021/11/20 12:58
 */
public class Demo2 {
    public static void main(String[] args) {
//        Predicate<String> predicate  = new Predicate<String>() {
//
//            /**
//             * 判断字符串是否为空
//             * @param s
//             * @return
//             */
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };

        // 用lambda表达式简化
        Predicate<String> predicate = (s) -> {return s.isEmpty();};
        System.out.println(predicate.test(""));
    }
}
