import com.fullstackboy.springdemo.event.MyEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于Spring内部广播器发布事件
 *
 * @author Liuyongfei
 * @date 2022/3/13 17:06
 */
public class EventDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "event.xml");

        // 发布自定义事件
        context.publishEvent(new MyEvent("test myEvent"));
    }
}
