
public class MyArrayTools {

    public static void print(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is null");
            return;
        }
        int maxLen = getMaxLenOfElem(arr);
        int[] blankArr = getBlankCount(arr, maxLen);
        int[] lenArr = getLenArr(arr);
        int d = deep(arr);
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
                    return;
                }
                printBlank(blankArr[index]);
                int c = maxLen - lenArr[index];
                if (index % 2 == 0) {
                    printBlank((c >> 1) + c % 2);
                    System.out.print(arr[index]);
                    printBlank(c >> 1);
                } else {
                    printBlank(c >> 1);
                    System.out.print(arr[index]);
                    printBlank((c >> 1) + c % 2);
                }
            }
            System.out.println();
        }
    }

    private static int father(int child) {
        if (child == 0) {
            return -1;
        }
        return (child - 1) >> 1;
    }

    private static int deep(int[] arr) {
        int d = 0;
        for (int i = arr.length - 1; i >= 0; i = father(i)) {
            d++;
        }
        return d;
    }

    private static int[] getLenArr(int[] arr) {
        int[] lenArr = new int[arr.length];
        for (int i = 0; i < lenArr.length; i++) {
            lenArr[i] = Integer.toString(arr[i]).length();
        }
        return lenArr;
    }

    private static int getMaxLenOfElem(int[] arr) {
        int maxLen = 1;
        for (int a : arr) {
            int curLen = Integer.toString(a).length();
            if (curLen > maxLen) {
                maxLen = curLen;
            }
        }
        return maxLen;
    }

    private static int[] getBlankCount(int[] arr, int maxLen) {
        int[] blankArr = new int[arr.length];
        int d = deep(arr);
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
                    if (j == 0) {
                        blankArr[index] = (1 << (d - i)) - 1 + ((maxLen - 1) >> 1) * ((1 << (d - i)) - 1);
                        if (maxLen % 2 == 0) {
                            blankArr[index] += ((1 << (d - i - 1)) - 1);
                        }
                    } else {
                        blankArr[index] = (1 << (d - i)) + ((1 << (d - i)) - 1) * maxLen;
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
