package cn.base;

import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        Integer[] integers = list1.toArray(new Integer[0]);
        System.out.println(Arrays.toString(integers));
        List<Integer> integers1 = Arrays.asList(integers);
        System.out.println(integers1);

        System.out.println("-------------");
        TreeMap<Integer, Integer> treeMap = new TreeMap<>((a, b) -> b - a);






    }
}
