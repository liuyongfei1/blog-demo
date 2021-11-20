package stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User对象
 * 使用注解
 * 无参构造函数、有参构造函数、get\set\toString 方法
 * @author Liuyongfei
 * @date 2021/11/20 15:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;

    private String name;

    private int age;

}
