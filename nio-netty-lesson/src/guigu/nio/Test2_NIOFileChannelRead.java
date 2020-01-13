package guigu.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test2_NIOFileChannelRead {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("nio-netty-lesson\\src\\guigu\\nio\\Test1_NIOFileChannel.txt");

        FileChannel channel = fis.getChannel();

        ByteBuffer bb = ByteBuffer.allocate(1024);

        channel.read(bb);

        bb.flip();
        while (bb.hasRemaining()) {
            System.out.print((char) bb.get());
        }
        System.out.println();
        bb.flip();
        System.out.println(new String(bb.array()));

        fis.close();


    }

}
