import sun.tools.jconsole.Worker;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket Server端改造为 多线程模式，可以应对大量客户端连接
 *
 * @author Liuyongfei
 * @date 2021/9/15 09:22
 */
public class SocketServer1 {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9000);

        while (true) {
            Socket socket = serverSocket.accept();
            new Worker(serverSocket, socket).start();
        }
    }

    static class Worker extends Thread {

        Socket socket;
        ServerSocket serverSocket;

        public Worker(ServerSocket serverSocket, Socket socket) {
            this.serverSocket = serverSocket;
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                OutputStream out = socket.getOutputStream();

                // Socket的输入流，相当于就是不停的读取人家通过TCP协议发送过来的一个一个的TCP包
                // 把TCP包的数据通过IC输入流的方式提供给你
                // 你就可以通过IO输入流读取的方式把TCP包的数据读出来放入JVM内存的一个缓冲数组中
                char[] buf = new char[1024 * 1024];
                int len = in.read(buf);

                while (len != -1) {
                    String request = new String(buf, 0, len);

                    System.out.println("服务端收到了请求：" + request);
                    out.write("收到，收到".getBytes());

                    // read会有一个阻塞的效果，他会阻塞在这里尝试读取数据出来
                    len = in.read(buf);
                }

                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
//                try {
////                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }


}
