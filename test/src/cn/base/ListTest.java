package cn.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        System.out.println("------------set的作用范围，会报指针超出异常-------------------");
        List<Integer> arr1 = new LinkedList<>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(3);
        System.out.println(arr1);
//        System.out.println(arr1.set(4, 4));  // IndexOutOfBoundsException
//        System.out.println(arr1);
        System.out.println(arr1.remove(1));
        System.out.println(arr1);

        List<Integer> arr2 = new ArrayList<>();
        arr2.add(1);
        arr2.add(2);
        arr2.add(3);
        System.out.println(arr2);
//        System.out.println(arr2.set(4, 4));  // IndexOutOfBoundsException
//        System.out.println(arr2);
        System.out.println(arr2.remove(1));
        System.out.println(arr2);


        System.out.println("----------------null的输出----------------");
        ArrayList<Integer> arr3 = new ArrayList<>();
        arr3.add(null);
        System.out.println(arr3);


    }
}
