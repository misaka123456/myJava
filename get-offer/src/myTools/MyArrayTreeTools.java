package myTools;

public class MyArrayTreeTools {

    /**
     * 数组树接口打印
     */
    public static void print(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is null");
            return;
        }
        String[] strArr = new String[arr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.valueOf(arr[i]);
        }
        print(strArr);
    }
    /**
     * 数组树接口打印
     */
    public static void print(char[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is null");
            return;
        }
        String[] strArr = new String[arr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.valueOf(arr[i]);
        }
        print(strArr);
    }
    /**
     * 数组树接口打印
     */
    public static void print(Object[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is null");
            return;
        }
        String[] strArr = new String[arr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.valueOf(arr[i]);
        }
        print(strArr);
    }
    /**
     * 数组树接口打印
     */
    public static void print(String[] arr) {

        int maxLen = getMaxLenOfElem(arr);
        int[] blankArr = getBlankCount(arr, maxLen);
        int[] lenArr = getLenArr(arr);
        int d = deep(arr.length);
        printBlank(blankArr[0]);
        System.out.println(arr[0]);
        for (int i = 2; i <= d; i++) {
            int floorStart = (1 << (i - 1)) - 1;
            for (int j = 0; j < floorStart + 1; j++) {
                int index = floorStart + j;
                if (index < arr.length) {
                    int c = maxLen - 1;
                    if (index % 2 == 0) {
                        printBlank(blankArr[index] + (c >> 1));
                        System.out.print('\\');
                        printBlank((c >> 1) + c % 2);
                    } else {
                        printBlank(blankArr[index] + (c >> 1) + c % 2);
                        System.out.print('/');
                        printBlank((c >> 1));
                    }
                }
            }
            System.out.println();
            for (int j = 0; j < floorStart + 1; j++) {
                int index = floorStart + j;
                if (index >= arr.length) {
                    System.out.println();
                    return;
                }
                printBlank(blankArr[index]);
                int c = maxLen - lenArr[index];
                if (index % 2 == 1) {
                    printBlank(c >> 1);
                    System.out.print(arr[index]);
                    printBlank((c >> 1) + c % 2);
                } else {
                    printBlank((c >> 1) + c % 2);
                    System.out.print(arr[index]);
                    printBlank(c >> 1);
                }
            }
            System.out.println();
        }
    }

    /**
     * 数组树结构求父节点
     */
    private static int father(int child) {
        if (child == 0) {
            return -1;
        }
        return (child - 1) >> 1;
    }

    /**
     * 数组树结构求深度
     */
    private static int deep(int length) {
        int d = 0;
        for (int i = length - 1; i >= 0; i = father(i)) {
            d++;
        }
        return d;
    }

    /**
     * 数组每个元素的长度
     */
    private static int[] getLenArr(String[] arr) {
        int[] lenArr = new int[arr.length];
        for (int i = 0; i < lenArr.length; i++) {
            lenArr[i] = arr[i].length();
        }
        return lenArr;
    }

    /**
     * 数组中最大长度元素的长度
     */
    private static int getMaxLenOfElem(String[] arr) {
        int maxLen = 1;
        for (String s : arr) {
            int curLen = s.length();
            if (curLen > maxLen) {
                maxLen = curLen;
            }
        }
        return maxLen;
    }

    /**
     * 数组每个元素相同长度时需要不全的空格数
     */
    private static int[] getBlankCount(String[] arr, int maxLen) {
        int[] blankArr = new int[arr.length];
        int d = deep(arr.length);
        for (int i = 1; i <= d; i++) {
            int floorStart = (1 << (i - 1)) - 1;
            if (i == d) {
                blankArr[floorStart] = 0;
                for (int j = floorStart + 1; j < arr.length; j++) {
                    blankArr[j] = 1;
                }
                break;
            }
            for (int j = 0; j < floorStart + 1; j++) {
                int index = floorStart + j;
                if (index >= arr.length) {
                    break;
                } else {
                    if (j != 0) {
                        blankArr[index] = (1 << (d - i)) + ((1 << (d - i)) - 1) * maxLen;
                    } else {
                        blankArr[index] = (1 << (d - i)) - 1 + ((maxLen - 1) >> 1) * ((1 << (d - i)) - 1);
                        if (maxLen % 2 == 0) {
                            blankArr[index] += ((1 << (d - i - 1)) - 1);
                        }
                    }
                }
            }
        }
        return blankArr;
    }

    private static void printBlank(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

}