import model.TreeNode;

import java.util.*;

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
     * @param arr 值不为负数的数组
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
                end++;
                curSum = curSum - arr[start] + arr[end];
                start++;
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


    /**
     * 获取和为k的最长子数组的长度
     * @param arr 值可为任意整数的数组
     * @param k 和
     * @return 最长长度
     */
    public static int sumEqualKMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(len, i - map.get(sum - k));
            } else {
                if (!map.containsKey(sum)) {
                    map.put(sum, i);
                }
            }
        }
        return len;
    }


    /**
     * 获取和小于等于k的最长子数组的长度
     * @param arr 数组
     * @param k 和
     * @return 最长长度
     */
    public static int sumLessKMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSums = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }
        int end = 0;
        int sum = 0;
        int res = 0;
        // i是窗口的最左的位置，end是窗口最右位置的下一个位置
        for (int i = 0; i < arr.length; i++) {
            // while循环结束之后：
            // 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
            // 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
            while (end < arr.length && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            res = Math.max(res, end - i);
            if (end > i) { // 窗口内还有数
                sum -= arr[i];
            } else { // 窗口内已经没有数了，说明从i开头的所有子数组累加和都不可能<=k
                end = i + 1;
            }
        }
        return res;
    }


    /**
     * 数组转化为A-Z的伪26进制字符串（没有0）
     * @param num 数组
     * @return 字符串
     */
    public static String intParseToAZStr(int num) {

        int len = 0;
        int cur = 1;
        while (cur <= num) {
            num = num - cur;
            cur = cur * 26;
            len++;
        }
        char[] chars = new char[len];

        int index = 0;
        int curN = 0;
        for (int i = 0; i < chars.length; i++) {
            cur = cur / 26;
            curN = num / cur;
            chars[index++] = (char) (curN + 65);
            num = num % cur;
        }

        return Arrays.toString(chars);
    }


    /**
     * 根据楼数据生成轮廓图
     * @param buildings 楼的数据组，每个数据三个参数（左边位置，右边位置，高度）
     * @return 轮廓队列，每个元素2个参数（位置和高度）
     */
    public static ArrayList<int[]> getBuilding(int[][] buildings) {

        if (buildings == null || buildings.length == 0) {
            return null;
        }
        // 每一个大楼轮廓数组，产生两个描述高度变化的对象
        int[][] btrix = new int[buildings.length << 1][2];
        for (int i = 0; i < buildings.length; i++) {
            btrix[i << 1][0] = buildings[i][0];
            btrix[i << 1][1] = buildings[i][2];

            btrix[(i << 1) + 1][0] = buildings[i][1];
            btrix[(i << 1) + 1][1] = buildings[i][2] * -1;
        }

        // 排序的比较策略
        // 1，第一个维度的x值从小到大。
        // 2，如果第一个维度的值相等，看第二个维度的值，“加入”排在前，“删除”排在后
        // 3，如果两个对象第一维度和第二个维度的值都相等，则认为两个对象相等，谁在前都行。
        // 把描述高度变化的对象数组，按照规定的排序策略排序
        Arrays.sort(btrix, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });
        // TreeMap就是java中的红黑树结构，直接当作有序表来使用
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        // 结果轮廓集
        ArrayList<int[]> heightArr = new ArrayList<>();
        int maxHeight = 0;
        for (int i = 0; i < btrix.length; i++) {
            int h = btrix[i][1];
            if (h > 0) {
                // 如果当前是添加操作
                if (!mapHeightTimes.containsKey(h)) {
                    // 没有出现的高度直接新加记录
                    mapHeightTimes.put(h, 1);
                } else {
                    // 之前出现的高度，次数加1即可
                    mapHeightTimes.put(h, 1 + mapHeightTimes.get(h));
                }
            } else {
                // 如果当前是删除操作
                h = -h;
                if (mapHeightTimes.get(h) == 1) {
                    // 如果当前的高度出现次数为1，直接删除记录
                    mapHeightTimes.remove(h);
                } else {
                    // 如果当前的高度出现次数大于1，次数减1即可
                    mapHeightTimes.put(h, mapHeightTimes.get(h) - 1);
                }
            }
            if (mapHeightTimes.isEmpty()) {
                // 如果高度集为空则输出0高度轮廓
                if (maxHeight != 0){
                    heightArr.add(new int[]{btrix[i][0], 0});
                    maxHeight = 0;
                }
            } else {
                if (mapHeightTimes.lastKey() != maxHeight) {
                    maxHeight = mapHeightTimes.lastKey();
                    heightArr.add(new int[]{btrix[i][0], maxHeight});
                }
            }

        }
        return heightArr;
    }


    /**
     * 获取树的所有路径的和的最大值
     * @param root 根节点
     * @return 最大值
     */
    public static int getRouteMaxSumInTree(TreeNode root) {

        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return getMaxInfoInTree(root)[0];
    }
    /**
     * 获取树根节点往下的最大路径和以及含根节点最大路径和
     * @param node 根节点
     * @return [最大路径和, 含根节点最大路径和]
     */
    private static int[] getMaxInfoInTree(TreeNode node) {
        if (node == null) {
            return null;
        }

        int[] left = getMaxInfoInTree(node.getLeft());
        int[] right = getMaxInfoInTree(node.getRight());

        int maxSumHead = node.getValue();
        int maxSum = node.getValue();
        if (left != null) {
            if (right != null) {
                maxSumHead = Math.max(left[1], right[1]);
                maxSumHead = maxSumHead > 0 ? maxSumHead + node.getValue() : node.getValue();
                maxSum = Math.max(left[0], right[0]);
                maxSum = Math.max(maxSum, maxSumHead);
            } else {
                maxSumHead = Math.max(left[1] + node.getValue(), node.getValue());
                maxSum = Math.max(maxSumHead, left[0]);
            }
        } else if (right != null) {
            maxSumHead = Math.max(right[1] + node.getValue(), node.getValue());
            maxSum = Math.max(maxSumHead, right[0]);
        }
        return new int[]{maxSum, maxSumHead};
    }


}


















