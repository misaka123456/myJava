import myTools.MyArrayTools;

public class NowCoderPrimary {

    /**
     * 数组最小和：每个数左边比它小的数的总数
     * @param arr 数组
     * @return 总数
     */
    public static int minNumCount(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        return minNumCount(arr, 0, arr.length - 1);
    }
    private static int minNumCount(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        int mid = (start + end) >> 1;
        int count = minNumCount(arr, start, mid) + minNumCount(arr, mid + 1, end);
        int[] newArr = new int[end - start + 1];
        int l = start;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= end) {
            if (arr[l] < arr[r]) {
                count++;
                newArr[i++] = arr[l++];
            } else {
                newArr[i++] = arr[r++];
            }
        }
        if (l <= mid) {
            for (int j = l; j <= mid; j++) {
                newArr[i++] = arr[l++];
            }
        }
        if (r <= end) {
            for (int j = r; j <= end; j++) {
                newArr[i++] = arr[r++];
            }
        }
        for (int j = 0; j < newArr.length; j++) {
            arr[start + j] = newArr[j];
        }
        return count;
    }


    /**
     * 数组最小和：每个数左边比它小的数的所有和
     * @param arr 数组
     * @return 最小和
     */
    public static int minNumSum(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        return minNumSum(arr, 0, arr.length - 1);
    }
    private static int minNumSum(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        int mid = (start + end) >> 1;
        int sum = minNumSum(arr, start, mid) + minNumSum(arr, mid + 1, end);
        int[] newArr = new int[end - start + 1];
        int l = start;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= end) {
            if (arr[l] < arr[r]) {
                sum = sum + arr[l] * (end - r + 1);
                newArr[i++] = arr[l++];
            } else {
                newArr[i++] = arr[r++];
            }
        }
        if (l <= mid) {
            for (int j = l; j <= mid; j++) {
                newArr[i++] = arr[l++];
            }
        }
        if (r <= end) {
            for (int j = r; j <= end; j++) {
                newArr[i++] = arr[r++];
            }
        }
        for (int j = 0; j < newArr.length; j++) {
            arr[start + j] = newArr[j];
        }
        return sum;
    }


    /**
     * 小的放左，大的放右
     * @param arr
     * @param n
     */
    public static void smallLeftAndLargeRight(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int pre = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= n) {
                pre++;
                if (pre != i) {
                    MyArrayTools.swap(arr, i, pre);
                }
            }
        }
    }
    /**
     * 小的放左，大的放右，相等放中间
     * @param arr
     * @param n
     */
    public static void smallLeftAndLargeRightAndEqualMiddle(int[] arr, int n) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int pre = -1;
        int equal = 0;
        for (int i = 0; i < arr.length; i++) {
           if (arr[i] == n) {
               pre++;
               equal++;
               if (pre != i) {
                   MyArrayTools.swap(arr, i, pre);
               }
           } else if (arr[i] < n){
               pre++;
               if (pre != i) {
                   MyArrayTools.swap(arr, i, pre);
                   if (equal != 0) {
                       MyArrayTools.swap(arr, pre, pre - equal);
                   }
               }
           }
        }
    }





}
