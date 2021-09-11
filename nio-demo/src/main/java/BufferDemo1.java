import java.nio.ByteBuffer;

/**
 * 熟悉Buffer的几个核心概念
 *
 * @author Liuyongfei
 * @date 2021/9/11 22:35
 */
public class BufferDemo1 {
    public static void main(String[] args) {

        // 分配一个Direct缓冲区，效率更高
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);

        System.out.println("position=" + buffer.position()); // 0
        System.out.println("capacity=" + buffer.capacity()); // 100
        System.out.println("limit=" + buffer.limit()); // 100

        byte[] src = new byte[] {1,2,3,4,5};
        buffer.put(src);

        System.out.println("position=" + buffer.position()); // 5

        // 此时0~4都填充了数据，position=5,如果直接读取数据是读取不到的。
        // 需要手动操作一下position，调整到position=0的地方，开始读数据
        buffer.position(0);

        byte[] dest = new byte[5];
        buffer.get(dest);
        System.out.println("position=" + buffer.position());

        System.out.print("dst=[");

        for (int i = 0; i < dest.length; i++) {
            System.out.println(i);

            if (i < dest.length - 1) {
                System.out.println(",");
            }
        }
        System.out.print("]");
    }
}
