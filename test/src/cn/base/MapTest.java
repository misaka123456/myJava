package cn.base;

import java.util.HashMap;

public class MapTest {

    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();
        System.out.println(map.put(11, "abc"));   // null
        System.out.println(map.remove(11));  // abc
    }
}
