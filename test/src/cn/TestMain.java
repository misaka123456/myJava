package cn;

import cn.base.SingletonTest;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestMain {

    public static void main(String[] args) throws IOException {

        String str = "https://search.bilibili.com:100/1111bangumi/sadf/asdg?sadffa#sadfasfd";
        System.out.println(str);
        Pattern compile = Pattern.compile("(.+)://(.+?):?(\\d{0,5})/(.+)\\??(.*)");
        Matcher matcher = compile.matcher(str);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
            System.out.println(matcher.group(5));

        }
    }


}
