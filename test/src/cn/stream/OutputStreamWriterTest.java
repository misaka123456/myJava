package cn.stream;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterTest {


    public static void main(String[] args) throws IOException {


        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        // 单个字符输出
        osw.write(50);
        osw.write('\n');

        osw.write("123森岛帆高");



        osw.close();
    }
}
