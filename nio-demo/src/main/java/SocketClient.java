import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Socket Client
 *
 * @author Liuyongfei
 * @date 2021/9/15 09:31
 */
public class SocketClient {
    public static void main(String[] args) throws Exception{

        // 先通过DNS服务查找域名对应的IP地址
        // 接下来需要跟那个ip地址上的9000端口的服务器程序进行TCP三次握手
        // 这个时候会构造一个三次握手中的第一次握手的TCP包
        // 把这个TCP包封装在IP包里，IP包里是有对应的IP地址，再封装在以太网包里
        // 通过底层的硬件设备走以太网协议出去，路由器通过IP地址查找路由表，确定下一个路由器的位置
        // 查找下一个路由的mac地址，写入以太网包头，走下一个子网广播出去
        // 通过这种方式层层转发，一直到对应的服务器上去

        // 服务器收到三次握手中的第一次握手的TCP包
        // 就会回传第二次握手的TCP包给这个客户端的程序，客户端再次发送第一次握手的TCP包
        // 三次握手成功，TCP连接就建立起来了。
        Socket socket = new Socket("localhost",9000);

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        OutputStream out = socket.getOutputStream();

        // 发送数据流，底层会拆分为一个一个的tcp包
        out.write("你好".getBytes());

        char[] buf = new char[1024 * 1024];
        int len = in.read(buf);

        while (len != -1) {
            String response = new String(buf, 0, len);
            System.out.println("客户端收到了响应：" + response);
            len = in.read(buf);
        }

        in.close();
        out.close();
        socket.close();
    }
}
