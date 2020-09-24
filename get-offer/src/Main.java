import java.util.*;
public class Main{
    public static void main(String[] args){

       int[] arr = {9,9,9,7};
        System.out.println(Arrays.toString(add(arr, 4)));


    }

    private static int[] add(int[] numArr, int a) {

        numArr[numArr.length - 1] = numArr[numArr.length - 1] + a;
        int top = 0;
        int sum = 0;

        for (int i = numArr.length - 1; i >= 0; i--) {
            sum = top + numArr[i];
            if (sum < 10) {
                numArr[i] = sum;
                top = 0;
                break;
            } else {
                numArr[i] = sum - 10;
                top = 1;
            }
        }


        if (top == 0) {
            return numArr;
        } else {
            int[] outArr = new int[numArr.length + 1];
            outArr[0] = top;
            for (int i = 0; i < numArr.length; i++) {
                outArr[i + 1] = numArr[i];
            }
            return outArr;
        }
    }
}