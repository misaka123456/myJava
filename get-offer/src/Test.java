import model.LinkedNode;
import model.TreeNode;
import myTools.MyArrayTools;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;


public class Test {


    public static void main(String[] args) throws Exception {


        int[] arr1 = {0,9,8,7,6,5,4,3,2,1};
        int[] arr2 = {0,9,8,7,6,5,4,3,2,1};

        MyArrayTools.sortByMerge(arr1);
        System.out.println(Arrays.toString(arr1));
        MyArrayTools.sortByQuick(arr2);
        System.out.println(MyArrayTools.compare(arr2, arr1));


        System.out.println(((Comparable<String>) "123").compareTo("133"));


    }



}







