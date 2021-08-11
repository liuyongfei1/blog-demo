package proxy;

/**
 * 接口实现类
 * 只关注处理业务逻辑
 * @author Liuyongfei
 * @date 2021/7/31 12:08
 */
public class IPersonImpl implements IPerson{
    @Override
    public void sleep() {
        System.out.println("睡觉中");
    }

    @Override
    public void eating() {
        System.out.println("吃饭中");
    }
}
