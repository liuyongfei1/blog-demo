import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义一个注解
 * @interface 的作用是自定义一个新注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTarget {
}
