package cn.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderTest {

    public static void main(String[] args) throws IOException {

        // 缓存机制的流 用法和InputStreamReader一样
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 每次读取一个字符数组的数据（缓存）
        char[] arr = new char[5];
        int len;
        while ((len = br.read(arr)) != -1) {
            // 必须要用这种组合方式
            System.out.print(new String(arr, 0, len));
        }

        br.close();

    }



}
