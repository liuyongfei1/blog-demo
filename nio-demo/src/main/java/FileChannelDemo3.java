import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 从磁盘文件里读取数据到Buffer缓冲区
 *
 * @author Liuyongfei
 * @date 2021/9/12 11:26
 */
public class FileChannelDemo3 {

    public static void main(String[] args) throws Exception{
        FileInputStream stream = new FileInputStream("/Users/lyf/Downloads/nioTest/test1.txt");
        FileChannel channel = stream.getChannel();

        // 将数据写入buffer，所以完成后buffer的position = 16
        ByteBuffer buffer = ByteBuffer.allocateDirect(11);
        channel.read(buffer);
        // 将buffer的position复位，position = 0, limit = 16
        buffer.flip();

        // 将buffer中的数据写入到data中
        byte[] data = new byte[11];
        buffer.get(data);

        System.out.println(new String(data));

        channel.close();
        stream.close();

    }
}
