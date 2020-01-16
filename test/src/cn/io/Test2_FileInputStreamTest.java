package cn.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test2_FileInputStreamTest {

    public static void main(String[] args) throws IOException {

        // 字节流
        InputStream is = new FileInputStream("E:\\workspace\\myJava\\test\\src\\cn\\stream\\in.txt");
        // 读取单个字节
//        int s = is.read();
//        System.out.println(s);
//        int ch;
//        while ((ch = is.read()) != -1) {
////            System.out.println(ch);
//            // 中文一个字符对应2字节，再从字节转字符时会乱码
//            System.out.print((char) ch);
//        }

        // 字节流转字符流
        InputStreamReader isr = new InputStreamReader(is);
        char[] arr = new char[5];
        int len;
        while ((len = isr.read(arr)) != -1) {
            // 必须要用这种组合方式
            // 中文不会出问题
            System.out.print(new String(arr, 0, len));
        }

        is.close();
        isr.close();


    }

}
