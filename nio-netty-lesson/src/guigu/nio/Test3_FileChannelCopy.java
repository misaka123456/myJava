package guigu.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test3_FileChannelCopy {

    public static void main(String[] args) throws IOException {


        FileInputStream fis = new FileInputStream("nio-netty-lesson\\src\\guigu\\nio\\Test3_in.txt");
        FileChannel channel1 = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("nio-netty-lesson\\src\\guigu\\nio\\Test3_out.txt");
        FileChannel channel2 = fos.getChannel();

        ByteBuffer bb = ByteBuffer.allocate(5);

        // 该循环是因为buffer大小可能小于需要读取的数据，因此需要多次read
        while (true) {

            bb.clear();

            if (channel1.read(bb) == -1) {
                break;
            }

            bb.flip();
            channel2.write(bb);
        }

        fis.close();
        fos.close();


    }
}
