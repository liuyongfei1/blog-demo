package function;

import java.util.function.Function;

/**
 * 函数型接口
 * 只要是函数型接口，就可以用lambda表达式简化
 * @author Liuyongfei
 * @date 2021/11/20 11:52
 */
public class Demo1 {
    public static void main(String[] args) {
        // 当不知道怎么写参数的时候，可以去看一下源码。
        // Function 函数型接口，传入一个参数，返回类型为R

//        Function<String,String> function = new Function<String, String>() {
//            /**
//             * Applies this function to the given argument.
//             *
//             * @param s the function argument
//             * @return the function result
//             */
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };

        // 只要是函数型接口，就可以用lambda表达式简化
        Function<String,String> function = (str) -> { return str; };

        System.out.println(function.apply("hello"));

    }
}
