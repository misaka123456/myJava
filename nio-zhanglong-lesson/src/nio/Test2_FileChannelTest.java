package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test2_FileChannelTest {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("nio-zhanglong-lesson\\src\\nio\\Test2_FileChannelTest.txt");
        FileChannel fileChannel = fis.getChannel();

        ByteBuffer bb = ByteBuffer.allocate(5);

        // read

        fileChannel.read(bb);
        bb.flip();
        while (bb.remaining() > 0) {
            System.out.print((char) bb.get());
        }

        System.out.println();

        bb.flip();
        while (bb.remaining() > 0) {
            System.out.print((char) bb.get());
        }

        fileChannel.close();



    }

}
