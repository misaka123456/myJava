package model;

import java.util.ArrayList;

public class MyMaxHeap {

    private ArrayList<Integer> heap;

    public MyMaxHeap() {
        heap = new ArrayList<>();
    }

    public MyMaxHeap(int[] elems) {
        heap = new ArrayList<>();
        for (int elem : elems) {
            insert(elem);
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public int deep() {
        int d = 0;
        for (int i = size() - 1; i >= 0; i = father(i)) {
            d++;
        }
        return d;
    }

    public int getMax() {
        return heap.get(0);
    }

    public void insert(int elem) {
        heap.add(elem);
        upElem(size() - 1);
    }

    public void insert(int[] elems) {
        for (int elem : elems) {
            insert(elem);
        }
    }

    public int popMax() {
        swap(0, size() - 1);
        int max = heap.remove(size() - 1);
        downElem(0);
        return max;
    }

    public int replace(int elem) {
        int max = getMax();
        heap.set(0, elem);
        downElem(0);
        return max;
    }

    private void downElem(int key) {
        while (key < size()) {
            int maxChild = leftChild(key);
            if (maxChild < size()) {
                int rc = rightChild(key);
                if (rc < size() && heap.get(rc) > heap.get(maxChild)) {
                    maxChild = rc;
                }
                if (heap.get(maxChild) < heap.get(key)) {
                    break;
                }
                swap(key, maxChild);
                key = maxChild;
            } else {
                break;
            }
        }
    }

    private void upElem(int key) {
        while (key > 0) {
            int fatherKey = father(key);
            if (heap.get(key) > heap.get(fatherKey)) {
                swap(key, fatherKey);
                key = fatherKey;
            } else {
                break;
            }
        }
    }

    private int father(int child) {
        if (child == 0) {
            return -1;
        }
        return (child - 1) >> 1;
    }

    private int leftChild(int father) {
        return (father << 1) + 1;
    }

    private int rightChild(int father) {
        return (father << 1) + 2;
    }

    private void swap(int a, int b) {
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public void print() {
        int maxLen = getMaxLenOfElem();
        int[] blankArr = getBlankCount(maxLen);
        int[] lenArr = getLenArr();
        int d = deep();
        printBlank(blankArr[0]);
        System.out.println(heap.get(0));
        for (int i = 2; i <= d; i++) {
            int floorStart = (1 << (i - 1)) - 1;
            for (int j = 0; j < floorStart + 1; j++) {
                int index = floorStart + j;
                if (index < size()) {
                    int c = maxLen - 1;
                    if (index % 2 == 1) {
                        printBlank(blankArr[index] + (c >> 1) + c % 2);
                        System.out.print('/');
                        printBlank((c >> 1));
                    } else {
                        printBlank(blankArr[index] + (c >> 1));
                        System.out.print('\\');
                        printBlank((c >> 1) + c % 2);
                    }
                }
            }
            System.out.println();
            for (int j = 0; j < floorStart + 1; j++) {
                int index = floorStart + j;
                if (index >= size()) {
                    System.out.println();
                    return;
                }
                printBlank(blankArr[index]);
                int c = maxLen - lenArr[index];
                if (index % 2 == 1) {
                    printBlank(c >> 1);
                    System.out.print(heap.get(index));
                    printBlank((c >> 1) + c % 2);
                } else {
                    printBlank((c >> 1) + c % 2);
                    System.out.print(heap.get(index));
                    printBlank(c >> 1);
                }
            }
            System.out.println();
        }
    }

    private int[] getLenArr() {
        int[] lenArr = new int[size()];
        for (int i = 0; i < lenArr.length; i++) {
            lenArr[i] = Integer.toString(heap.get(i)).length();
        }
        return lenArr;
    }

    private int getMaxLenOfElem() {
        int maxLen = 1;
        for (int i = 0; i < size(); i++) {
            int curLen = Integer.toString(heap.get(i)).length();
            if (curLen > maxLen) {
                maxLen = curLen;
            }
        }
        return maxLen;
    }

    private int[] getBlankCount(int maxLen) {
        int[] blankArr = new int[size()];
        int d = deep();
        for (int i = 1; i <= d; i++) {
            int floorStart = (1 << (i - 1)) - 1;
            if (i == d) {
                blankArr[floorStart] = 0;
                for (int j = floorStart + 1; j < size(); j++) {
                    blankArr[j] = 1;
                }
                break;
            }
            for (int j = 0; j < floorStart + 1; j++) {
                int index = floorStart + j;
                if (index >= size()) {
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

    private void printBlank(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

}
