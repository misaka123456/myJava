package guigu.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test1_NIOFileChannelWrite {

    public static void main(String[] args) throws IOException {

        String s = "asdlkgfjlkdf森岛帆高第三方as";
        File file = new File("nio-netty-lesson\\src\\guigu\\nio\\Test1_NIOFileChannel.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);

        FileChannel channel = fos.getChannel();

        ByteBuffer bb = ByteBuffer.allocate(1024);

        bb.put(s.getBytes());

        bb.flip();
        channel.write(bb);

        fos.close();




    }

}
