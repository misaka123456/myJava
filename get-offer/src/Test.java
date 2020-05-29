import model.TreeNode;
import myTools.MyArrayTools;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    public static void main(String[] args) throws Exception {

      int a = 1 << 31;
        System.out.println(a);


    }


    public static int[] sortByBits(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int[] countArr = new int[arr.length];
        for (int i = 0; i <arr.length; i++) {
            countArr[i] = getNumOfOne(arr[i]);
        }
        sortByBits(arr, countArr, 0, arr.length - 1);
        return arr;
    }
    private  static void sortByBits(int[] arr, int[] countArr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = position(arr, countArr, start, end);
        sortByBits(arr, countArr, start, mid);
        sortByBits(arr, countArr, mid + 1, end);
    }
    private static int position(int[] arr, int[] countArr, int start, int end) {
        int pre = start;
        int countTemp = countArr[start];
        int temp = arr[start];
        for (int i = start + 1; i <= end; i++) {
            if (countArr[i] < countTemp || (countArr[i] == countTemp && arr[i] < temp)) {
                pre++;
                if (pre != i) {
                    swap(arr, countArr, pre, i);
                }
            }
        }
        swap(arr, countArr, start, pre);
        return pre;
    }
    private static void swap(int[] arr, int[] countArr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        temp = countArr[a];
        countArr[a] = countArr[b];
        countArr[b] = temp;
    }
    private static int getNumOfOne(int a) {
        int num = 0;
        while (a != 0) {
            num++;
            a = a - (a & -a);
        }
        return num;
    }



}







