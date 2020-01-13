package guigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Test5_NIOClient {

    public static void main(String[] args) throws IOException {

        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7000);
        if (!sc.connect(address)) {
            while (!sc.finishConnect()) {
                System.out.println("链接需要时间，客户端不会阻塞");
            }
        }
        System.out.println("链接成功");
        String s = "lkjasdjhfjhasdkjf";
        ByteBuffer bb = ByteBuffer.wrap(s.getBytes());
        sc.write(bb);
        System.out.println("发送完成");

    }

}
