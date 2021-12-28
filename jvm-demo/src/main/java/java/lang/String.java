package java.lang;

/**
 * 举例讲双亲委派机制
 *
 * 这个String跟java管理的是同包同名
 *
 * @author Liuyongfei
 * @date 2021/12/26 22:31
 */
public class String {

    public String toString() {
        return "Hello";
    }

    public static void main(String[] args) {
        String s = new String();
        System.out.println(s.toString());
    }
}
