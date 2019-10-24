public class NowCoder {


    /**
     * 2有序数组求第k位数
     * @param arr1
     * @param arr2
     * @param k
     * @return
     * @throws Exception
     */
    public static int getMaxK(int[] arr1, int[] arr2, int k) throws Exception {
        if (arr1 == null || arr2 == null) {
            throw new Exception("arr is error");
        }
        if (k < 0 || k >= arr1.length + arr2.length) {
            throw new Exception("k is error");
        }
        int length = arr1.length + arr2.length;
        int[] longArr = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shortArr = arr1.length >= arr2.length ? arr2 : arr1;

        if (k < shortArr.length) {
            return getMidfromTwoEqualLenghtArr(shortArr, 0, k, longArr, 0, k);
        } else if (k >= longArr.length + shortArr.length) {
            int shortStart = length - k;
            int longStart = length - k;
            if (shortArr[shortArr.length - 1] <= longArr[longStart]) {
                return longArr[longStart];
            }
            if (shortArr[shortStart] >= longArr[longArr.length - 1]) {
                return shortArr[shortStart];
            }
            return getMidfromTwoEqualLenghtArr(shortArr, shortStart + 1, shortArr.length, longArr, longStart + 1, longArr.length);
        } else {
            int longStart = k - shortArr.length;
            if (longArr[longStart] >= shortArr[shortArr.length - 1]) {
                return longArr[longStart];
            } else {
                return getMidfromTwoEqualLenghtArr(shortArr, 0, shortArr.length - 1, longArr, longStart + 1, k);
            }
        }
    }
    /**
     * 2等长有序数组求中值
     * @param arr1
     * @param start1
     * @param end1
     * @param arr2
     * @param start2
     * @param end2
     * @return
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
            return getMidfromTwoEqualLenghtArr(arr1, start1, mid1, arr2, mid2 + 1, end2);
        } else {
            return getMidfromTwoEqualLenghtArr(arr1, mid1 + 1, end1, arr2, start2, mid2);
        }
    }


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
            } else if (curSum < sum) {
                end++;
            } else {
                start++;
            }
        }

        return maxL;
    }

    public static void main(String[] args) throws Exception {

//        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
//        int[] arr2 = {2 ,3, 5, 6, 8 ,9 ,10, 13, 23, 45};
//        System.out.println(getMaxK(arr1, arr2, 3));
//        System.out.println(getMaxK(arr1, arr2, 5));
//        System.out.println(getMaxK(arr1, arr2, 8));
//        System.out.println(getMaxK(arr1, arr2, 10));
//        System.out.println(getMaxK(arr1, arr2, 15));

        int[] arr = new int[] {1, 2, 1, 1, 1};
        System.out.println(getLengthOfPlusArrInSum(arr, 3));


    }



}
