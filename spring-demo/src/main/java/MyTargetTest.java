import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 自定义注解测试
 *
 * @author Liuyongfei
 * @date 2021/7/29 15:54
 */
public class MyTargetTest {

    @MyTarget
    public void doSomething() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        try {

            HashMap<String, String> map = new HashMap<String, String>(3);
            map.put("张三","测试数据");

            Method method = MyTargetTest.class.getMethod("doSomething", null);
            // 如果doSomething方法上存在注解@MyTarget
            if (method.isAnnotationPresent(MyTarget.class)) {
                System.out.println(method.getAnnotation(MyTarget.class));
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
