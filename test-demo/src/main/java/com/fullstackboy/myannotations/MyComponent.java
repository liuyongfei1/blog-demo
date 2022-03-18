package com.fullstackboy.myannotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解
 * 模拟Spring的 @Component功能
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyComponent {
    String value() default "";
}
