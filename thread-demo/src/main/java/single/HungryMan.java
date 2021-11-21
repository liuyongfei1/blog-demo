package single;

/**
 * 单例模式：饿汉模式
 *
 * @author Liuyongfei
 * @date 2021/11/21 17:32
 */
public class HungryMan {

    private HungryMan() {

    }

    // 上来不管三七二十一，先创建一个实例
    private static final HungryMan HUNGRY_MAN = new HungryMan();


    public static HungryMan getInstance() {
        return  HUNGRY_MAN;
    }
}
