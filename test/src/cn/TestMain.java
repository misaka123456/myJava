package cn;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class TestMain {

    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        List<Integer> integers = Collections.synchronizedList(list);
        integers.add((1));




    }


}
