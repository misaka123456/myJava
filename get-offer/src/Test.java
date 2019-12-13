import model.MyListByArray;
import model.MyStackByArray;
import myTools.MyArrayTreeTools;

import java.util.*;

public class Test {

    public static void main(String[] args) {

//        int[] arr = {11, 11, 11, 11, 1, 111, 111, 1, 1, 1, 2, 3, 3};
//        MyArrayTreeTools.print(arr);
//
//       String[] arr1 = {"1213", "234df", "akdsjg", "ds", "s", "adsfh", "ajfdsgadg", "s", "s"};
//        MyArrayTreeTools.print(arr1);


//        MyStackByArray<Integer> arr = new MyStackByArray<>(10);
//        arr.push(1);
//        arr.push(2);
//        arr.push(3);
//        arr.push(4);
//        arr.push(5);
//        arr.push(6);
//        System.out.println(arr);
//        System.out.println(arr.pop());
//        System.out.println(arr);


//        MyListByArray<Integer> arr = new MyListByArray<>(10);
//        arr.push(1);
//        arr.push(2);
//        arr.push(3);
//        arr.push(4);
//        arr.push(5);
//        arr.push(6);
//        arr.push(7);
//        System.out.println(arr);
//        System.out.println(arr.pop());
//        System.out.println(arr.pop());
//        System.out.println(arr.pop());
//        System.out.println(arr.pop());
//        arr.push(8);
//        arr.push(9);
//        arr.push(11);
//        arr.push(12);
//        arr.push(12);
//        arr.push(13);
//        arr.push(15);
//        System.out.println(arr);


        TreeMap<Integer, Integer> tm = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        tm.put(1, 1);
        tm.put(2, 2);

        System.out.println(tm);


        TreeSet<Integer> ts = new TreeSet<>((o1, o2) -> 0);
        ts.add(1);
        ts.add(2);
        ts.add(3);
        System.out.println(ts);

        HashMap<Integer, Integer> hm = new HashMap<>();



    }

}
