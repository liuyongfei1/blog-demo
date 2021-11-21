package single;

/**
 * 单例模式之：利用静态内部类来实现单例模式
 *
 * @author Liuyongfei
 * @date 2021/11/21 18:14
 */
public class Holder {

    private Holder() {

    }

    private Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass {
        private static final Holder HOLDER = new Holder();
    }
}
