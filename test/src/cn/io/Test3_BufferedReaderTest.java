package cn.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test3_BufferedReaderTest {

    public static void main(String[] args) throws IOException {

        /*
        BufferedReader和BufferedWriter类各拥有8192个字符的缓冲区。
        当BufferedReader在读取文本文件时，会先尽量从文件中读入字符数据并放满缓冲区，
        而之后若使用read()方法，会先从缓冲区中进行读取。
        如果缓冲区数据不足，才会再从文件中读取，使用BufferedWriter时，
        写入的数据并不会先输出到目的地，而是先存储至缓冲区中。
        如果缓冲区中的数据满了，才会一次对目的地进行写出。
         */

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
