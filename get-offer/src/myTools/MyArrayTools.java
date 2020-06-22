package myTools;


import java.util.Comparator;

public class MyArrayTools {

    public static <E> void swap(E[] arr, int a, int b) {
        E temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void swap(int[] arr, int a, int b) {
        if (a == b) {
            return;
        }
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

    /**
     * int类型数组转Integer封装类数组
     */
    public static Integer[] intToInteger(int[] arr) {

        Integer[] newArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /**
     * Integer封装类数组转int类型数组
     */
    public static int[] integerToInt(Integer[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /**
     * int类型数组比较，无比较器
     */
    public static int compare(int[] arr1, int[] arr2) {
        return compare(intToInteger(arr1), intToInteger(arr2));
    }

    /**
     * int类型数组比较，有比较器
     */
    public static int compare(int[] arr1, int[] arr2, Comparator<? super Integer> c) {
        return compare(intToInteger(arr1), intToInteger(arr2), c);
    }

    /**
     * 任意对象类型数组比较，无比较器
     */
    @SuppressWarnings("unchecked")
    public static <E> int compare(E[] arr1, E[] arr2) {
        return compare(arr1, arr2, (o1, o2) -> ((Comparable<? super E>) o1).compareTo(o2));
    }

    /**
     * 任意对象类型数组比较，有比较器
     */
    public static <E> int compare(E[] arr1, E[] arr2, Comparator<? super E> c) {
        for (int i = 0; i < arr1.length && i < arr2.length; i++) {
            int r = c.compare(arr1[i], arr2[i]);
            if (r != 0) {
                return r;
            }
        }
        return arr1.length - arr2.length;
    }

    /**
     * 快排 int类型 无比较器
     */
    public static void sortByQuick(int[] arr) {
        sortByQuick(arr, Comparator.comparingInt(o -> o));
    }

    /**
     * 快排 int类型 有比较器
     */
    public static void sortByQuick(int[] arr, Comparator<? super Integer> c) {
        Integer[] newArr = intToInteger(arr);
        sortByQuick(newArr, c);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = newArr[i];
        }
    }

    /**
     * 快排 任意对象 无比较器
     */
    @SuppressWarnings("unchecked")
    public static <E> void sortByQuick(E[] arr) {
        sortByQuick(arr, (o1, o2) -> ((Comparable<? super E>) o1).compareTo(o2));
    }

    /**
     * 快排 任意对象 有比较器
     */
    public static <E> void sortByQuick(E[] arr, Comparator<? super E> c) {
        if (arr == null || arr.length == 0) {
            return;
        }
        sortByQuick(arr, c, 0, arr.length - 1);
    }

    /**
     * 快排 任意对象 有比较器
     */
    private static <E> void sortByQuick(E[] arr, Comparator<? super E> c, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = partition(arr, c, start, end);
        sortByQuick(arr, c, start, mid - 1);
        sortByQuick(arr, c, mid + 1, end);
    }

    /**
     * 快排单次排序
     */
    private static <E> int partition(E[] arr, Comparator<? super E> c, int start, int end) {
        int mid = (int) (Math.random() * (end - start + 1)) + start;
        swap(arr, start, mid);
        int pre = start;
        E midVal = arr[start];
        for (int i = start + 1; i <= end; i++) {
            if (c.compare(arr[i], midVal) < 0) {
                pre++;
                if (pre < i) {
                    swap(arr, i, pre);
                }
            }
        }
        swap(arr, start, pre);
        return pre;
    }

    /**
     * 归并排序 int类型 无比较器
     */
    public static void sortByMerge(int[] arr) {
        sortByMerge(arr, Comparator.comparing(o -> o));
    }

    /**
     * 归并排序 int类型 有比较器
     */
    public static void sortByMerge(int[] arr, Comparator<? super Integer> c) {
        Integer[] newArr = intToInteger(arr);
        sortByMerge(newArr, c);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = newArr[i];
        }
    }

    /**
     * 归并排序 任意对象类型 无比较器
     */
    @SuppressWarnings("unchecked")
    public static <E> void sortByMerge(E[] arr) {
        sortByMerge(arr, (o1, o2) -> ((Comparable<? super E>) o1).compareTo(o2));
    }

    /**
     * 归并排序 任意对象类型 有比较器
     */
    public static <E> void sortByMerge(E[] arr, Comparator<? super E> c) {
        if (arr == null || arr.length == 0) {
            return;
        }
        sortByMerge(arr, c, 0, arr.length - 1);
    }

    /**
     * 归并排序，单次归并
     */
    private static <E> void sortByMerge(E[] arr, Comparator<? super E> c, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        sortByMerge(arr, c, start, mid);
        sortByMerge(arr, c, mid + 1, end);
        Object[] tempArr = new Object[end - start + 1];
        int l = start;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= end) {
            if (c.compare(arr[l], arr[r]) < 0) {
                tempArr[i++] = arr[l++];
            } else {
                tempArr[i++] = arr[r++];
            }
        }
        while (l <= mid) {
            tempArr[i++] = arr[l++];
        }
        while (r <= end) {
            tempArr[i++] = arr[r++];
        }
        @SuppressWarnings("unchecked") E[] eArr = (E[]) tempArr;
        System.arraycopy(eArr, 0, arr, start, tempArr.length);
    }

    /**
     * 堆排序
     */
    public static void sortByHeap(int[] arr) {
        Integer[] newArr = intToInteger(arr);
        sortByHeap(newArr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = newArr[i];
        }
    }

    /**
     * 堆排序
     */
    public static void sortByHeap(int[] arr, Comparator<? super Integer> c) {
        Integer[] newArr = intToInteger(arr);
        sortByHeap(newArr, c);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = newArr[i];
        }
    }

    /**
     * 堆排序
     */
    @SuppressWarnings("unchecked")
    public static <E> void sortByHeap(E[] arr) {
        sortByHeap(arr, ((o1, o2) -> ((Comparable<? super E>) o1).compareTo(o2)));
    }

    /**
     * 堆排序
     */
    public static <E> void sortByHeap(E[] arr, Comparator<? super E> c) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j != 0) {
                int father = (j - 1) >> 1;
                if (c.compare(arr[j], arr[father]) > 0) {
                    swap(arr, j, father);
                    j = father;
                } else {
                    break;
                }
            }
        }
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            int j = 0;
            while (j < i) {
                int child = (j << 1) + 1;
                if (child < i) {
                    if (child + 1 < i && c.compare(arr[child + 1], arr[child]) > 0) {
                        child = child + 1;
                    }
                    if (c.compare(arr[child], arr[j]) > 0) {
                        swap(arr, j, child);
                        j = child;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }
}
