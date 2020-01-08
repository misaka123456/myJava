package cn.stream;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Test1_InputStreamReaderTest {

    public static void main(String[] args) throws IOException {

        Reader isr = new InputStreamReader(System.in);



        // 每次读取一个字符
//        int ch;
//        while ((ch = isr.read()) != -1) {
//            System.out.println((char) ch);
//        }

        // 每次读取一个字符数组的数据（缓存）
        char[] arr = new char[5];
        int len;
        while ((len = isr.read(arr)) != -1) {
            // 必须要用这种组合方式
            System.out.print(new String(arr, 0, len));
        }

        isr.close();

        // 字节流
//        OutputStream fos = new FileOutputStream("E:\\workspace\\myJava\\test\\src\\cn\\stream\\out.txt");
//        fos.write(49);
//
//        OutputStreamWriter osw1 = new OutputStreamWriter(fos);
//        osw1.write("1212a奥德赛");
//        osw1.close();
//
//        fos.close();


    }


}
