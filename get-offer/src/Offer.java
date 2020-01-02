
import model.LinkedNode;

import java.util.*;

public class Offer {

    /**
     * 面试题3 p39
     * @param numbers 输入数字数组
     * @return 是否有重复
     */
    public static boolean findOneDuplication(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        for (int number : numbers) {
            if (number < 0 || number > numbers.length - 1) {
                return false;
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            // 判断其是否在其位
            while (i != numbers[i]) {
                if (numbers[i] == numbers[numbers[i]]) {
                    // 发现最大值
                    System.out.println(numbers[i]);
                    return true;
                }
                int temp = numbers[i];
                numbers[i] = numbers[numbers[i]];
                numbers[temp] = temp;
            }
        }
        return false;
    }


    /**
     * p79 快排
     * @param data int数组
     */
    public static void quickSort(int[] data) {
        if (data == null) {
            throw new NullPointerException("data is null");
        }
        if (data.length == 0) {
            return;
        }
        quickSort(data, 0, data.length - 1);
    }
    /**
     * 快排重载
     * @param data int数组
     * @param start 起始位置
     * @param end 结束位置
     */
    private static void quickSort(int[] data, int start, int end) {
        if (start == end) {
            return;
        }
        int index = partition(data, start, end);
        if (start < index) {
            quickSort(data, start, index - 1);
        }
        if (index < end) {
            quickSort(data, index + 1, end);
        }
    }
    /**
     * 单次排序
     * @param data int数组
     * @param start 开始位置
     * @param end 结束为止
     * @return 中间值
     */
    private static int partition(int[] data, int start, int end) {
        // 双索引遍历
        int small = start;
        for (int i = start + 1; i <= end; i++) {
            if (data[i] < data[start]) {
                small++;
                if (small != i) {
                    // 交换奇偶
                    int temp = data[small];
                    data[small] = data[i];
                    data[i] = temp;
                }
            }
        }
        int temp = data[small];
        data[small] = data[start];
        data[start] = temp;
        return small;
    }


    /**
     * p97 面试题14 剪绳子
     * @param length 绳子长度
     * @return 最大乘积
     */
    public static int maxProductAfterCutting(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        // 动态规划
        int[] products = new int[length + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        int max = 0;
        for (int i = 4; i <= length; i++) {
            max = 0;
            // j长度最大值和i - j长度最大值和的最大值，其中j = 1、2、~、i/2
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                if (max < product) {
                    max = product;
                }
            }
            products[i] = max;
        }
        return products[length];
    }


    /**
     * p114 面试17 打印1到n位整数
     * @param n 位数
     */
    public static void print1ToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        // 防止位数太大，使用数字来保存数字
        char[] number = new char[n];
        for (int i = 0; i < n; i++) {
            number[i] = '0';
        }
        int col = 0;

        while (!increment(number)) {
            printNumber(number);
            col++;
            if (col == 30) {
                System.out.println();
                col = 0;
            }
        }
        System.out.println();

    }
    /**
     * 打印数字
     * @param number 数字char型数组
     */
    private static void printNumber(char[] number) {
        boolean start = false;
        for (char c : number) {
            if (start) {
                System.out.print(c);
            } else if (c != '0') {
                System.out.print(c);
                // 数字前面的0不输出
                start = true;
            } else {
                // 数字对齐
                System.out.print(" ");
            }
        }
        System.out.print(" ");
    //        System.out.println();
    }
    /**
     * 数字加一
     * @param number 数字char型数组
     * @return 是否已到最大位数
     */
    private static boolean increment(char[] number) {
        for (int i = number.length - 1; i >= 0; i--) {
            // 从右往左进位
            if (number[i] != '9') {
                number[i] = (char) (number[i] + 1);
                return false;
            } else {
                // 是否已到最大值
                if (i == 0) {
                    return true;
                } else {
                    number[i] = '0';
                }
            }
        }
        return false;
    }


    /**
     * p124 面试题19：正则匹配
     */
    public static boolean match(String str, String pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return match(str.toCharArray(), pattern.toCharArray(), 0, 0);
    }
    private static boolean match(char[] str, char[] pattern, int sStart, int pStart) {
        if (sStart == str.length && pStart == pattern.length) {
            return true;
        }
        if (sStart < str.length && pStart == pattern.length) {
            return false;
        }

        if (pattern[pStart + 1] == '*') {
            if (str[sStart] == pattern[pStart] || (pattern[pStart] == '.' && sStart < str.length)) {
                return match(str, pattern, sStart + 1, pStart + 2) ||
                        match(str, pattern, sStart, pStart + 2) ||
                        match(str, pattern, sStart + 1, pStart);
            } else {
                return match(str, pattern, sStart, pStart + 2);
            }
        }
        if (str[sStart] == pattern[pStart] || (pattern[pStart] == '.' && sStart < str.length)) {
            return match(str, pattern, sStart + 1, pStart + 1);
        }
        return false;
    }


    /**
     * p129 面试题21 奇数在偶数前面
     * @param data 数字数组
     */
    public static void reorderOddEven(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        // 双指针，类似于快排的单次排序
        int odd = -1;
        for (int i = 0; i < data.length; i++) {
            if ((data[i] & 0x1) == 1) {
                odd++;
                if (odd != i) {
                    int temp = data[odd];
                    data[odd] = data[i];
                    data[i] = temp;
                }
            }
        }
    }
    /**
     * 奇数在偶数前，并且奇数与奇数、偶数与偶数相对位置不变
     * 方案一：空间换时间 时间复杂度O(3n)，空间复杂度O(n)
     * @param data 数字数组
     */
    public static void reorderOddEvenStrict1(int[] data) {
        int[] dataTemp = new int[data.length];
        System.arraycopy(data, 0, dataTemp, 0, data.length);
        int dataIndex = 0;
        // 先将奇数放入
        for (int d : dataTemp) {
            if ((d & 0x1) == 1) {
                data[dataIndex] = d;
                dataIndex++;
            }
        }
        // 再讲偶数放入
        for (int d : dataTemp) {
            if ((d & 0x1) == 0) {
                data[dataIndex] = d;
                dataIndex++;
            }
        }
    }
    /**
     * 奇数在偶数前，并且奇数与奇数、偶数与偶数相对位置不变
     * 方案二：冒泡 时间复杂度O(n2)，空间复杂度O(1)
     * @param data 数字数组
     */
    public static void reorderOddEvenStrict2(int[] data) {
        // 双指针，类似于快排的单次排序
        int oddEnd = -1;
        for (int i = 0; i < data.length; i++) {
            if ((data[i] & 0x1) == 1) {
                oddEnd++;
                if (oddEnd != i) {
                    // 奇数往前放的同时所有数字向后移一位，保证相对位置不变
                    int temp = data[i];
                    if (i - oddEnd >= 0) {
                        System.arraycopy(data, oddEnd, data, oddEnd + 1, i - oddEnd);
                    }
                    data[oddEnd] = temp;
                }
            }
        }
    }


    /**
     * p161 面试题29顺时针打印矩阵
     * @param matrix 二维矩阵
     * @return 顺时针数组
     * direction:
     *          0: →
     *      3: ↑     1: ↓
     *          2: ←
     */
    public static ArrayList<Integer> printMatrixClockWisely(int[][] matrix) {
        if (matrix == null) {
            return null;
        }

        ArrayList<Integer> printNumbers = new ArrayList<>();
        int startX = 0;
        int startY = 0;
        int endX = matrix.length - 1;
        int endY = matrix[0].length - 1;
        int direction = 0;

        while (startX <= endX && startY <= endY) {
            switch (direction) {
                case 0:
                    // →
                    for (int i = startY; i <= endY; i++) {
                        printNumbers.add(matrix[startX][i]);
//                            System.out.print(matrix[startX][i]);
//                            System.out.print(" ");
                    }
                    startX++;
                    break;
                case 1:
                    // ↓
                    for (int i = startX; i <= endX; i++) {
                        printNumbers.add(matrix[i][endY]);
//                            System.out.print(matrix[i][endY]);
//                            System.out.print(" ");
                    }
                    endY--;
                    break;
                case 2:
                    // ←
                    for (int i = endY; i >= startY; i--) {
                        printNumbers.add(matrix[endX][i]);
//                            System.out.print(matrix[endX][i]);
//                            System.out.print(" ");
                    }
                    endX--;
                    break;
                case 3:
                    // ↑
                    for (int i = endX; i >= startX; i--) {
                        printNumbers.add(matrix[i][startY]);
//                            System.out.print(matrix[i][startY]);
//                            System.out.print(" ");
                    }
                    startY++;
                    break;
            }
            direction = (direction + 1) % 4;
        }
        return printNumbers;
    }


    /**
     * p197 面试题38 字符串排列
     * @param str 需要排列的字符串
     */
    public static ArrayList<String> permutation(String str) {
        ArrayList<String> strList = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return strList;
        }

        // 字符串转化为数组
        char[] arr = new char[str.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = str.charAt(i);
        }

        permutation(arr, 0, strList);
        return strList;
    }
    private static void permutation(char[] arr, int start, ArrayList<String> strList) {
        if (start == arr.length) {
            String oneInStrList = String.valueOf(arr);
            // 输入是否允许出现重复字符 如aa 只能输出aa，不能输出aa aa。
            // 允许 则需要该if来去除重复情况
            // 不允许 则可去掉
            if (!strList.contains(oneInStrList)) {
                strList.add(oneInStrList);
//                    System.out.println(oneOfStr);
            }
        }
        for (int i = start; i < arr.length; i++) {
            char temp = arr[i];
            // 所有数字后移一位，保证按照排列习惯的顺序
            System.arraycopy(arr, start, arr, start + 1, i - start);
            arr[start] = temp;
            permutation(arr, start + 1, strList);
            // 复位
            System.arraycopy(arr, start + 1, arr, start, i - start);
            arr[i] = temp;
        }
    }


    /**
     * p199 字符串全排
     * @param str 需要排列的字符串
     */
    public static ArrayList<String> permutationAll(String str) {
        ArrayList<String> strList = new ArrayList<>();
        if (str == null) {
            return strList;
        }

        char[] arr = new char[str.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = str.charAt(i);
        }
        permutationAll(arr, 0, new char[arr.length], -1, strList);
        return strList;
    }
    private static void permutationAll(char[] arr, int start, char[] as, int end, ArrayList<String> strList) {
        if (start == arr.length) {
            return;
        }
        permutationAll(arr, start + 1, as, end, strList);
        end++;
        as[end] = arr[start];

        String oneInStrList = String.valueOf(as, 0, end + 1);
        if (!strList.contains(oneInStrList)) {
            strList.add(oneInStrList);
        }
//        System.out.println(oneInStrList);

        permutationAll(arr, start + 1, as, end, strList);
    }


    /**
     * p218 面试题42 连续数组最大和
     * @param data int型数组
     * @return 最大和
     */
    public static int findGreatestSumOfSubArray(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }

        // 类似于快排单次排序
        int greatestSum = 0x80000000;  // 最小值
        int curSum = 0;
        for (int d : data) {
            if (curSum <= 0) {
                // 小于0，则重新计数
                curSum = d;
            } else {
                curSum += d;
            }
            if (curSum > greatestSum) {
                // 更新最大值
                greatestSum = curSum;
            }
        }
        return greatestSum;
    }


    /**
     * p231 面试题46 数字转换成字符串
     * @param number 非负数
     * @return 所有可能情况数
     */
    public static int getTranslationCount(int number) {
        if (number < 0) {
            return 0;
        }

        String nStr = String.valueOf(number);
        // 动态规划，保存之前的结果
        int[] counts = new int[nStr.length()];
        counts[nStr.length() - 1] = 1;
        // 从右往左计算
        for (int i = nStr.length() - 2; i >= 0; i--) {
            counts[i] = counts[i + 1];
            int converted = (nStr.charAt(i) - '0') * 10 + nStr.charAt(i + 1) - '0';
            if (converted >= 10 && converted <= 25) {
                if (i < nStr.length() - 2) {
                    // o oo...o 和 oo o...o ，counts[i + 1] + counts[i + 2]种情况
                    counts[i] += counts[i + 2];
                } else {
                    // oo 和 o o ，2种情况
                    counts[i] += 1;
                }
            }
        }
        return counts[0];
    }


    /**
     * p233 面试题47 礼物最大价值
     * @param valueMatrix 礼物矩阵
     * @return 最大值
     */
    public static int getMaxValueSolution(int[][] valueMatrix) {
        // 走到该坐标的最大价值，动态规划
        int[][] maxValues = new int[valueMatrix.length][valueMatrix[0].length];

        for (int i = 0; i < maxValues.length; i++) {
            for (int j = 0; j < maxValues[0].length; j++) {
                int left = 0;
                int up = 0;
                if (i > 0) {
                    up = maxValues[i - 1][j];
                }
                if (j > 0){
                    left = maxValues[i][j - 1];
                }
                // 计算最大值
                maxValues[i][j] = Math.max(up, left) + valueMatrix[i][j];
            }
        }
        return maxValues[maxValues.length - 1][maxValues[0].length - 1];
    }


    /**
     * p236 面试题48 最长不重复字符串
     * @param str 字符串
     * @return 最长长度
     */
    public static int longestSubstringWithoutDuplication(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        // 双索引，类似于快排单次排序
        int curLen = 1;
        int maxLen = 1;
        for (int i = 1; i < str.length(); i++) {
            int j = i - curLen;
            for (; j < i; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    // 更新当前长度和起始位置
                    curLen = i - j;
                    break;
                }
            }
            if (i == j) {
                curLen++;
                // 更新最大长度
                maxLen = Math.max(curLen, maxLen);
            }
        }
        return maxLen;
    }


    /**
     * p240 面试题49 丑数
     * @param index 丑数索引
     * @return 第index个丑数
     */
    public static int getUglyNumber(int index) {
        if (index <= 0) {
            return 0;
        }

        // 动态规划，保存之前的丑数
        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        for (int i = 1; i < index; i++) {
            uglyNumbers[i] = min(uglyNumbers[index2] * 2, uglyNumbers[index3] * 3, uglyNumbers[index5] * 5);
            while (uglyNumbers[index2] * 2 <= uglyNumbers[i]) {
                index2++;
            }
            while (uglyNumbers[index3] * 3 <= uglyNumbers[i]) {
                index3++;
            }
            while (uglyNumbers[index5] * 5 <= uglyNumbers[i]) {
                index5++;
            }
        }
        return uglyNumbers[uglyNumbers.length - 1];
    }
    /**
     * 计算三个数的最小值
     * @param a a
     * @param b b
     * @param c c
     * @return 最小值
     */
    private static int min(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }


    /**
     * p243 面试题50#1 字符串中第一个只出现一次的字符
     * @param str 字符串
     * @return 该字符串的索引，没有返回-1
     */
    public static int findNotRepeatingChar(String str) {

        int[] charNumbers = new int[256];
        for (int i = 0; i < str.length(); i++) {
            charNumbers[str.charAt(i)]++;
        }

        for (int i = 0; i < str.length(); i++) {
            if (charNumbers[str.charAt(i)] == 1) {
                return i;
            }

        }
        return -1;
    }


    /**
     * p253 面试题52 两个链表的第一个公共节点
     * @param linkedNode1 链表1
     * @param linkedNode2 链表2
     * @return 公共节点
     */
    public static LinkedNode findFirstCommonNode(LinkedNode linkedNode1, LinkedNode linkedNode2) {
        if (linkedNode1 == null || linkedNode2 == null) {
            return null;
        }

        // 得到2个链表的长度
        int list1Length = getListNodeLength(linkedNode1);
        int list2Length = getListNodeLength(linkedNode2);
        int listLengthDif = list1Length - list2Length;

        LinkedNode nodeLong;
        LinkedNode nodeShort;
        if (list1Length > 0) {
            nodeLong = linkedNode1;
            nodeShort = linkedNode2;
        } else {
            nodeLong = linkedNode2;
            nodeShort = linkedNode1;
        }
        // 长的先走几步，保持同步
        for (int i = 0; i < listLengthDif; i++) {
            nodeLong = nodeLong.getNext();
        }
        // 遍历得到第一个相同节点
        while (nodeLong != nodeShort) {
            nodeLong = nodeLong.getNext();
            nodeShort = nodeShort.getNext();

        }
        return nodeLong;
    }
    /**
     * 获取链表长度
     * @param linkedNode 链表
     * @return 长度
     */
    private static int getListNodeLength(LinkedNode linkedNode) {
        int length = 0;
        while (linkedNode != null) {
            length++;
            linkedNode = linkedNode.getNext();
        }
        return length;
    }


    /**
     * p134 面试题22 链表中倒数第k个节点
     * @param head 链表头结点
     * @param k k
     * @return 倒数第k个节点
     */
    public static LinkedNode findKToTail(LinkedNode head, int k) {

        if (head == null || k <= 0) {
            return null;
        }

        LinkedNode node = head;
        LinkedNode nodeK = null;
        // 先遍历k次
        while (node != null) {
            if (k-- == 0) {
                break;
            }
            node = node.getNext();
        }
        if (k > 0) {
            return null;
        }
        // 前节点走到尾部，后节点为第k个节点
        nodeK = head;
        while (node != null) {
            node = node.getNext();
            nodeK = nodeK.getNext();
        }
        return nodeK;
    }


    /**
     * p139 面试题23 链表中环的入口节点
     * @param head 链表头结点
     * @return 入口节点
     */
    public static LinkedNode entryNodeOfLoop(LinkedNode head) {

        // 获取环中某个节点
        LinkedNode meetingNode = getOneMeetingNode(head);
        if (meetingNode == null) {
            return null;
        }

        // 计算环的长度
        int length = 1;
        LinkedNode node = meetingNode.getNext();
        while (node != meetingNode) {
            length++;
            node = node.getNext();
        }
        // 先遍历length次
        node = head;
        while (length > 0) {
            length--;
            node = node.getNext();
        }
        LinkedNode entryNode = head;
        while (entryNode != node) {
            entryNode = entryNode.getNext();
            node = node.getNext();
        }
        return entryNode;
    }
    /**
     * 获取环中的某个节点，没有环则返回null
     * @param head 链表头结点
     * @return 环中节点
     */
    private static LinkedNode getOneMeetingNode(LinkedNode head) {
        if (head == null) {
            return null;
        }

        LinkedNode nodeFast = head;
        LinkedNode nodeSlow = head;

        while (nodeFast != null) {
            nodeFast = nodeFast.getNext();
            if (nodeFast == null) {
                return null;
            } else {
                nodeFast = nodeFast.getNext();
            }
            nodeSlow = nodeSlow.getNext();
            if (nodeFast == nodeSlow) {
                return nodeFast;
            }
        }
        return null;
    }


    /**
     * p263 面试题53#1 数字在排序数组中出现的次数
     * @param data 数字数组
     * @param k k
     * @return k的次数
     */
    public static int getNumberOfK(int[] data, int k){
        if (data == null || data.length == 0) {
            return 0;
        }
        int number = 0;
        int first = getFirstKIndex(data, k, 0, data.length - 1);
        int last = getLastKIndex(data, k, 0, data.length - 1);
        if (first != -1 && last != -1) {
            number = last - first + 1;
        }
        return number;
    }
    /**
     * 获取第一个k的索引
     * @param data 数字数组
     * @param k k
     * @param start 开始索引
     * @param end 结束索引
     * @return 第一个索引
     */
    public static int getFirstKIndex(int[] data, int k, int start, int end) {

        if (start > end) {
            return -1;
        }

        // 二分法查找
        int mIndex = (start + end) >> 1;
        int mValue = data[mIndex];
        if (mValue == k) {
            if (mIndex == 0 || data[mIndex - 1] != k) {
                return mIndex;
            } else {
                end = mIndex - 1;
            }
        } else if (mValue > k) {
            end = mIndex - 1;
        } else {
            start = mIndex + 1;
        }
        return getFirstKIndex(data, k, start, end);
    }
    /**
     * 获取最后一个k的索引
     * @param data 数字数组
     * @param k k
     * @param start 开始索引
     * @param end 结束索引
     * @return 最后一个索引
     */
    private static int getLastKIndex(int[] data, int k, int start, int end) {

        if (start > end) {
            return -1;
        }

        // 二分法查找
        int mIndex = (start + end) >> 1;
        int mValue = data[mIndex];
        if (mValue == k) {
            if (mIndex == data.length - 1 || data[mIndex + 1] != k) {
                return mIndex;
            } else {
                start = mIndex + 1;
            }
        } else if (mValue > k) {
            end = mIndex - 1;
        } else {
            start = mIndex + 1;
        }
        return getLastKIndex(data, k, start, end);
    }


    /**
     * p266 面试题53#2 0~n-1中缺失的数字
     * @param numbers 缺失数组
     * @return 缺失数字
     */
    public static int getMissingNumber(int[] numbers) {

        if (numbers == null || numbers.length == 0) {
            return -1;
        }

        int left = 0;
        int right = numbers.length - 1;
        // 二分法查找
        while (left <= right) {
            int middle = (right + left) >> 1;
            if (middle == numbers[middle] - 1) {
                if (middle == 0 || numbers[middle - 1] == middle - 1) {
                    return middle;
                } else {
                    right = middle - 1;
                }

            } else {
                left = middle + 1;
            }
        }
        // 最右侧情况
        if (left == numbers.length && right == numbers[right]) {
            return left;
        } else {
            return -1;
        }
    }


    /**
     * p266 面试题53#3 数组中数值和下标相等的元素
     * @param numbers 数组
     * @return 指定元素的值/索引
     */
    public static int getNumberSameAsIndex(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }

        int left = 0;
        int right = numbers.length - 1;
        int middle = 0;
        while (left <= right) {
            middle = (right + left) >> 1;
            if (numbers[middle] == middle) {
                return middle;
            } else if (numbers[middle] < middle) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }


    /**
     * p280 面试题57#1 和为s的两个数字
     * @param data 数字数组
     * @param sum 和
     * @return 和为s的两个数字
     */
    public static int[] findNumbersWithSum(int[] data, int sum) {

        int[] numbers = new int[2];
        if (data == null || data.length == 0) {
            return numbers;
        }

        int small = 0;
        int large = data.length - 1;

        while (small < large) {
            int curSum = data[small] + data[large];
            if (curSum == sum) {
                numbers[0] = data[small];
                numbers[1] = data[large];
                break;
            } else if (curSum < sum) {
                small++;
            } else {
                large--;
            }
        }
        return numbers;


    }


    /**
     * p282 面试题57#2 和为S的连续正数序列
     * @param sum 和
     * @return 结果集
     */
    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {

        ArrayList<ArrayList<Integer>> numbersList = new ArrayList<>();
        if (sum <= 0){
            return numbersList;
        }

        int small = 1;
        int large = 2;
        int curSum = 3;
        while ((small + small) < sum) {
            if (curSum == sum) {
                numbersList.add(getSequenceNumbers(small, large));
                large++;
                curSum += large;
            } else if (curSum > sum) {
                curSum -= small;
                small++;
            } else {
                large++;
                curSum += large;
            }
        }
        return numbersList;
    }
    /**
     *  获取small到large的所有数字
     * @param small 最小
     * @param large 最大
     * @return 顺序数组
     */
    private static ArrayList<Integer> getSequenceNumbers(int small, int large) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= large - small; i++) {
            numbers.add(small + i);
        }
        return numbers;
    }


    /**
     * p284 面试题58#1 翻转单次顺序
     * @param str 字符串
     * @return 翻转后字符串
     */
    public static String reverseSentence(String str) {

        if (str == null || str.length() == 0) {
            return "";
        }
        // 整体翻转
        char[] chars = new char[str.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[chars.length -i -1] = str.charAt(i);
        }
        //找到起始点
        int charBegin = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                charBegin = i;
                break;
            }
        }
        // 复位单词
        for (int i = charBegin + 1; i < chars.length; i++) {
            // 排出多个空格问题
            if (chars[i] == ' ' && chars[i - 1] != ' ') {
                reverseArray(chars, charBegin, i - 1);
            } else if (chars[i - 1] == ' '){
                charBegin = i;
            }
        }
        if (chars[chars.length - 1] != ' ') {
            reverseArray(chars, charBegin, chars.length - 1);
        }
        return String.valueOf(chars);
    }
    /**
     * 翻转数组
     * @param chars 字符数组
     * @param start 开始
     * @param end 结束
     */
    private static void reverseArray(char[] chars, int start, int end) {
        if (chars == null || chars.length <= 1) {
            return;
        }
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * p288 面试题59#1 滑块窗口的最大值
     * @param numbers 数组
     * @param size 窗口大小
     * @return 最大值数组
     */
    public static ArrayList<Integer> maxInWindows(int[] numbers, int size) {

        ArrayList<Integer> maxList = new ArrayList<>();
        if (numbers == null || size <= 0 || numbers.length < size) {
            return maxList;
        }

        // 输赢Deque作为最大值队列
        Deque<Integer> maxIndexDeque = new ArrayDeque<>();
        for (int i = 0; i < size - 1; i++) {
            while (!maxIndexDeque.isEmpty() && numbers[i] >= numbers[maxIndexDeque.getLast()]) {
                maxIndexDeque.pollLast();
            }
            maxIndexDeque.addLast(i);
        }
        for (int i = size - 1; i < numbers.length; i++) {
            // 超过滑块，队头弹出
            if (!maxIndexDeque.isEmpty() && maxIndexDeque.getFirst() <= i - size) {
                maxIndexDeque.removeFirst();
            }
            // 有更大的值，队尾弹出
            while (!maxIndexDeque.isEmpty() && numbers[i] >= numbers[maxIndexDeque.getLast()]) {
                maxIndexDeque.pollLast();
            }
            maxIndexDeque.addLast(i);
            maxList.add(numbers[maxIndexDeque.getFirst()]);
        }
        return maxList;
    }


    /**
     * p304 面试题63 股票的最大利润
     * @param numbers 股票价格
     * @return 最大利润
     */
    public static int maxDiff(int[] numbers) {

        if (numbers == null || numbers.length < 2) {
            return 0;
        }

        int minIn = numbers[0];
        int maxDiff = numbers[1] - minIn;
        int curDiff = maxDiff;
        for (int i = 2; i < numbers.length; i++) {
            if (numbers[i - 1] < minIn) {
                minIn = numbers[i - 1];
            }
            curDiff = numbers[i] - minIn;
            if (curDiff > maxDiff) {
                maxDiff = curDiff;
            }
        }
        return maxDiff;
    }


//    public static void getProbability(int n) {
//
//        ArrayList<Integer> valueList = new ArrayList<>();
//        if (n <= 0) {
//            return;
//        }
//
//        int[][] probabilities = new int[2][n * 6 + 1];
//        int flag = 0;
//        for (int i = 1; i <= 6; i++) {
//            probabilities[flag][i] = 1;
//        }
//        for (int i = 2; i <= n; i++) {
//            for (int j = i; j <= 6 * i; j++) {
//                probabilities[1 - flag][j] = 0;
//                for (int k = 1; k <= 6 && k < j; k++) {
//                    probabilities[1 - flag][j] += probabilities[flag][j - k];
//                }
//            }
//            flag = 1 - flag;
//        }
//        for (int i = n; i <= n * 6; i++) {
//            System.out.print(i);
//            System.out.print(":");
//            System.out.println(probabilities[flag][i]);
//        }
//
//    }

//    public static TreeNode getKthNode(TreeNode root, int k) {
//
//        if (root == null || k <= 0) {
//            return null;
//        }
//
//        return getKthNodeCore(root, new int[]{k});
//
//    }
//
//    private static TreeNode getKthNodeCore(TreeNode node, int[] k) {
//
//        TreeNode target = null;
//
//        if (node.getLeft() != null) {
//            target = getKthNode(node.getLeft(), k);
//        }
//        if (target == null) {
//            if (k == 1) {
//                return node;
//            } else {
//                k--;
//            }
//        }
//        if (target == null && node.getRight() != null) {
//            target = getKthNode(node, k);
//        }
//        return target;
//    }


}
