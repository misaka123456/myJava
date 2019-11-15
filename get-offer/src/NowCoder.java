public class NowCoder {


    /**
     * 2有序数组求第k位数
     * @param arr1 数组1
     * @param arr2 数组2
     * @param k 第k小数
     * @return 第k小的数
     * @throws Exception 输入异常
     */
    public static int getKthMinFromTwoArr(int[] arr1, int[] arr2, int k) throws Exception {
        if (arr1 == null || arr2 == null) {
            throw new Exception("arr is error");
        }
        int length = arr1.length + arr2.length;
        if (k <= 0 || k > length) {
            throw new Exception("k is error");
        }
        int[] longArr = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shortArr = arr1.length >= arr2.length ? arr2 : arr1;

        if (k <= shortArr.length) {
            return getMidfromTwoEqualLenghtArr(shortArr, 0, k - 1, longArr, 0, k - 1);
        } else if (k > longArr.length + shortArr.length) {
            int shortStart = k - longArr.length - 1;
            int longStart = k- shortArr.length - 1;
            if (shortArr[shortArr.length - 1] <= longArr[longStart]) {
                return longArr[longStart];
            }
            if (shortArr[shortStart] >= longArr[longArr.length - 1]) {
                return shortArr[shortStart];
            }
            return getMidfromTwoEqualLenghtArr(shortArr, shortStart + 1, shortArr.length, longArr, longStart + 1, longArr.length);
        } else {
            int longStart = k - shortArr.length - 1;
            if (longArr[longStart] >= shortArr[shortArr.length - 1]) {
                return longArr[longStart];
            } else {
                return getMidfromTwoEqualLenghtArr(shortArr, 0, shortArr.length - 1, longArr, longStart + 1, k - 1);
            }
        }
    }
    /**
     * 2等长有序数组求中值
     * @param arr1 数组1
     * @param start1 数组1起始点
     * @param end1 数组1结束点
     * @param arr2 数组2
     * @param start2 数组2起始点
     * @param end2 数组2结束点
     * @return 上中值
     */
    private static int getMidfromTwoEqualLenghtArr(int[] arr1, int start1, int end1,
                                         int[] arr2, int start2, int end2) {
        if (start1 == end1) {
            return arr1[start1];
        }
        int mid1 = (start1 + end1) >> 1;
        int mid2 = (start2 + end2) >> 1;
        if (arr1[mid1] == arr2[mid2]) {
            return arr1[mid1];
        } else if (arr1[mid1] > arr2[mid2]) {
            if ((end1 - start1 + 1) % 2 == 0) {
                return getMidfromTwoEqualLenghtArr(arr1, start1, mid1, arr2, mid2 + 1, end2);
            } else {
                if (arr1[mid1 - 1] <= arr2[mid2]) {
                    return arr2[mid2];
                } else {
                    return getMidfromTwoEqualLenghtArr(arr1, start1, mid1 - 1, arr2, mid2, end2);
                }
            }
        } else {
            if ((end1 - start1 + 1) % 2 == 0) {
                return getMidfromTwoEqualLenghtArr(arr1, mid1 + 1, end1, arr2, start2, mid2);
            } else {
                if (arr1[mid1] <= arr2[mid2 - 1]) {
                    return arr1[mid1];
                } else {
                    return getMidfromTwoEqualLenghtArr(arr1, mid1, end1, arr2, start2, mid2 - 1);
                }
            }
        }
    }


    /**
     * 获取和为k的最长子数组的长度
     * @param arr 数组
     * @param sum 和
     * @return 最长长度
     */
    public static int getLengthOfPlusArrInSum(int[] arr, int sum) {
        int maxL = 0;
        int curSum = 0;
        int start = 0;
        int end = 0;
        while (end < arr.length) {
            if (curSum == sum) {
                if (end - start + 1 >= maxL) {
                    maxL = end - start + 1;
                }
                start++;
                end++;
                curSum = arr[end];
            } else if (curSum < sum) {
                end++;
                if (end >= arr.length) {
                    break;
                }
                curSum = curSum + arr[end];
            } else {
                curSum = curSum - arr[start];
                start++;
            }
        }

        return maxL;
    }





}
