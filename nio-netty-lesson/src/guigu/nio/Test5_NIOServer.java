package guigu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Test5_NIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        Selector selector = Selector.open();

        ssc.bind(new InetSocketAddress(7000));

        // 设置为非阻塞模式
        ssc.configureBlocking(false);

        // channel注册到Select中，并设置时间模式
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端链接
        while (true) {
            // 等待一秒
            if (selector.select(1000) == 0) {
                // 无事件发生
                System.out.println("无连接，不阻塞");
            } else {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    // 获取selectKey
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        // 如果是接收事件
                        SocketChannel accept = ssc.accept();
                        // 注册， 事件为read, 关联 buffer
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 获取channel关联的buffer
                        ByteBuffer bb = (ByteBuffer) key.attachment();
                        channel.read(bb);
                        System.out.println("接收：" + new String(bb.array()));
                    }
                    keyIterator.remove();
                }
            }
        }

    }

}
