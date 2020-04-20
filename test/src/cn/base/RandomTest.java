package cn.base;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {

        Random random = new Random();
        System.out.println(random.nextInt(10));  // [0, 10) 左开右闭整数

        double random1 = Math.random();     // [0, 1) 左开右闭double数
        System.out.println((int) Math.floor(random1 * 10));   // [0, 10) 左开右闭整数


    }
}
