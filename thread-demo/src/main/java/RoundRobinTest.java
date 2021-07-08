import java.util.concurrent.atomic.AtomicInteger;

/**
 * round rabin轮询算法
 * 轮询调度算法的原理是每一次把来自用户的请求轮流分配给内部中的服务器，从1开始，直到N（内部服务器的个数）。然后重新开始循环
 *
 * 优点：简洁，无须记录当前所有连接的状态，所以它是一种无状态调度。
 * 缺点：轮询调度算法假设所有服务器的处理性能都相同，不关心每台服务器的当前连接数和响应速度。容易导致服务器间的负载不平衡
 *
 * 所以此种均衡算法适合于服务器组中的所有服务器都有相同的软硬件配置，并且平均服务请求相对均衡的情况。
 * @author Liuyongfei
 * @date 2021/7/8 10:53
 */
public class RoundRobinTest {
    private static final AtomicInteger next = new AtomicInteger(0);

    private int select(int S[]) throws Exception{
        if (S == null || S.length == 0) {
            throw new Exception("exception");
        } else {
            return S[next.getAndIncrement() % S.length];
        }
    }

    public static void main(String[] args) throws Exception{
        int S[] = {0,1,2,3,4};
        RoundRobinTest roundRobinTest = new RoundRobinTest();
        for (int i = 0; i< 10; i++) {
            System.out.println(roundRobinTest.select(S));
        }
    }

}
