package cn.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;

public class Test {


    public static void main(String[] args) throws IOException {

        ByteBuffer bb = ByteBuffer.allocate(3);

        bb.put((byte) 1);
        bb.put((byte) 2);
        bb.put((byte) 3);
        bb.put((byte) 4);

        System.out.println(bb.get(1));
        System.out.println(bb.get(2));




    }
}
