package com.fullstackboy.springdemo.ioc;

import com.fullstackboy.springdemo.ioc.bean.Employee;
import com.fullstackboy.springdemo.ioc.bean.Order;
import com.fullstackboy.springdemo.User1;
import com.fullstackboy.springdemo.ioc.bean.Course;
import com.fullstackboy.springdemo.ioc.service.DeptService;
import com.fullstackboy.springdemo.ioc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 测试 使用Spring IOC
 *
 * @author Liuyongfei
 * @date 2021/10/31 17:12
 */
public class Test1 {


    @Test
    public void testUser() {
        // 1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2.获取配置创建的对象
        User user = context.getBean("user", User.class);


        System.out.println(user);

        user.add();
    }

    /**
     * 测试使用set方法注入属性
     */
    @Test
    public void testBook() {
        // 1.加载Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2.获取配置创建的对象
        Book book = context.getBean("book", Book.class);

        System.out.println(book);

        book.printBook();
    }


    /**
     * 测试使用有参构造方式注入属性
     */
    @Test
    public void testOrders() {
        // 1.加载Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2.获取配置创建的对象
        Orders orders = context.getBean("orders",Orders.class);

        System.out.println(orders);

        orders.printOrders();

    }


    /**
     * 测试注入属性：外部bean（service调用dao对象）
     */
    @Test
    public void testBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");

        UserService userService = context.getBean("userService", UserService.class);

        System.out.println(userService);
        userService.add();
    }

    /**
     * ioc容器：bean管理（工厂bean）
     */
    @Test
    public void testFactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");

        Course course = context.getBean("myfactorybean", Course.class);

        System.out.println(course);
    }

    /**
     * 演示bean的生命周期的过程
     */
    @Test
    public void testBeanPeriod() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");

        Order order = context.getBean("order", Order.class);

        System.out.println("第六步：bean可以使用了，获取创建的bean对象。。。。。。");

        System.out.println(order);

        // 手动销毁bean实例
        context.close();
    }

    @Test
    public void testBean1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
        Employee employee = context.getBean("employee", Employee.class);

        System.out.println(employee);
    }

    @Test
    public void testBean2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml");
        DeptService deptService = context.getBean("deptService", DeptService.class);

        System.out.println(deptService);
    }

    /**
     * 测试使用函数风格创建对象，并交给Spring管理
     */
    @Test
    public void testGenericApplicationContext() {
        GenericApplicationContext context = new GenericApplicationContext();

        // 调用GenericApplicationContext的方法注册bean
        context.refresh();;
//        context.registerBean(User1.class, () -> new User1());
        context.registerBean("user1", User1.class, () -> new User1());

        // 获取在spring中注册的bean对象
//        User1 user1 = (User1) context.getBean("com.fullstackboy.springdemo.User1");
        User1 user1 = (User1) context.getBean("user1");
        System.out.println(user1);
    }
}
