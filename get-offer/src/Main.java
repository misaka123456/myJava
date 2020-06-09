import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {


        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }

        int left = 0;
        int right = len - 1;
        int count = 0;
        while (left < len) {
            if (arr[left] == 1) {
                break;
            }
            left++;
        }
        if (left == len) {
            System.out.println((len + 1) / 2);
            return;
        }
        count = left / 2;
        while (right >= 0) {
            if (arr[right] == 1) {
                break;
            }
            right--;
        }
        count += (len - right - 1) / 2;
        int pre = left + 1;
        for (int i = left + 1; i < right; i++) {

            if (arr[i] == 1) {

                count += (i - pre) / 2;
                pre = i + 1;
            } else {
                if (arr[pre] == 1) {
                    pre = i;
                }
            }
        }


        System.out.println(count);
    }

}