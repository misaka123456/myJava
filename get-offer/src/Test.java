import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import model.LinkedNode;
import model.TreeNode;
import myTools.MyArrayTools;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;


public class Test {


    public static void main(String[] args) throws Exception {


        int[] arr1 = {0,9,8,7,2,3,4,7,5,6,33,45,12,35,43,67,1,23,12,23,6,5,4,3,2,1};
        int[] arr2 = {0,9,8,7,6,5,4,3,2,1};

        MyArrayTools.sortByQuick(arr1);
        System.out.println(Arrays.toString(arr1));

    }



}







