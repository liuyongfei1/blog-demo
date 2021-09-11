import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 基于FileChannel将数据写入文件
 *
 * @author Liuyongfei
 * @date 2021/9/11 23:03
 */
public class FileChannel1 {

    public static void main(String[] args) throws Exception{

        // 构造一个传统的文件输出流
        FileOutputStream outputStream = new FileOutputStream("/Users/lyf/Downloads/nioTest/test1.txt");

        // 获取该文件输出流的FileChannel，以NIO的方式来写文件
        FileChannel channel = outputStream.getChannel();

        // 创建一个缓冲区
        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());

        channel.write(buffer);

        channel.close();
        outputStream.close();
    }
}
