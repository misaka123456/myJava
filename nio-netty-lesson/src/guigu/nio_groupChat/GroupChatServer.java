package guigu.nio_groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 监听
    public void listen() {
        try {
            while (true) {
                // 监听选择器是否有事件（channel）
                int count = selector.select(2000);
                if (count > 0) {
                    // 有事件要处理
                    // 遍历key
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        // accept
                        if (key.isAcceptable()) {
                            SocketChannel accept = listenChannel.accept();
                            accept.configureBlocking(false);
                            accept.register(selector, SelectionKey.OP_READ);
                            System.out.println(accept.getRemoteAddress() + "上线了！");
                        }
                        // read
                        if (key.isReadable()) {
                            // 通道可读
                            readData(key);
                        }
                        // 删除已处理key，防止重复处理
                        keyIterator.remove();
                    }

                } else {
                    System.out.println("等待中。。。");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    // 读取客户端消息
    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer bb = ByteBuffer.allocate(1024);
            int len = channel.read(bb);
            // 是否有数据
            if (len > 0) {
                String msg = new String(bb.array());
                System.out.println("接收到来自客户端 " + channel.getLocalAddress() + " 的消息：" + msg);
                // 向其他客户端转发
                sendDataToOther(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "离线了");
                // 取消注册
                key.cancel();
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    // 转发消息
    private void sendDataToOther(String msg, SocketChannel me) {
        System.out.println("服务器转发消息");
        selector.keys().forEach(key -> {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != me) {
                SocketChannel target = (SocketChannel) channel;
                ByteBuffer bb = ByteBuffer.wrap(msg.getBytes());
                try {
                    target.write(bb);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {

        // 启动服务器
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }

}
