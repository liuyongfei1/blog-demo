package volatiledemo;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/5/2 22:16
 */
public class MyData {

//    int num = 0;
    volatile int num = 0;

    public void add1() {
        this.num = 60;
    }
}
