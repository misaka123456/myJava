package guigu.nio;


import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Buffer数组
 * Scattering写入Buffer时，依次写入
 * Gathering读取Buffer时，依次读取
 */
public class Test4_ScatteringAndGathering {

    public static void main(String[] args) throws IOException {

        // 创建
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(7000);

        // 帮顶地址和端口，并启动
        ssc.bind(address);

        ByteBuffer[] bbs = new ByteBuffer[2];
        bbs[0] = ByteBuffer.allocate(5);
        bbs[1] = ByteBuffer.allocate(3);

        // 等待客户端链接
        SocketChannel accept = ssc.accept();

        int len;
        // 循环读取
        while (true) {
            len = 0;
            while (len < 8) {
                len += accept.read(bbs);
                System.out.println("读取到：" + len);
                Arrays.stream(bbs).map(bb -> "position = " + bb.position() + ", limit = " + bb.limit()).forEach(System.out::println);
            }

            // flip
            Arrays.asList(bbs).forEach(ByteBuffer::flip);

            // 显示
            long bw = 0;
            while (bw < 8) {
                bw += accept.write(bbs);
            }

            // Buffer clear
            Arrays.asList(bbs).forEach(ByteBuffer::flip);

            System.out.println("len = " + len + ", bw = " + bw);
        }

    }


}
