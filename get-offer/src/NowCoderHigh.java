import model.TreeNode;

import java.util.*;
import java.util.function.BiConsumer;

public class NowCoderHigh {


    /**
     * 牛客网高级版第一课 获取数组内数字最大间隔
     * @param arr 数组
     */
    public static int getMaxGap(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int a : arr) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        if (min == max) {
            return 0;
        }

        int div = max - min;
        int arrL = arr.length + 1;
        int[][] minAndMaxArr = new int[arrL][];
        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] - min) * arrL / div;
            // 排除最大值被分到arrL+1组导致超出索引范围的情况
            index = index == arrL ? index - 1 : index;
            try {
                if (minAndMaxArr[index] == null) {
                    // 桶为空
                    minAndMaxArr[index] = new int[]{arr[i], arr[i]};
                } else {
                    minAndMaxArr[index][0] = Math.min(minAndMaxArr[index][0], arr[i]);
                    minAndMaxArr[index][1] = Math.max(minAndMaxArr[index][0], arr[i]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("-------------");
                System.out.println(index);
                System.out.println(i);
                System.out.println(arr[i]);
                System.out.println("-------------");
                e.printStackTrace();
            }
        }
        int maxGap = 0;
        int lastMax = minAndMaxArr[0][1];
        for (int i = 1; i < minAndMaxArr.length; i++) {
            if (minAndMaxArr[i] != null) {
                maxGap = Math.max(maxGap, minAndMaxArr[i][0] - lastMax);
                lastMax = minAndMaxArr[i][1];
            }
        }

        return maxGap;
    }


    /**
     * 牛客网高级版第一课 根据面值数组获取找零的所有情况总数
     * @param moneyType 普通面值数组
     * @param spMoneyType 纪念币面值数组（每种面值只能用一次）
     * @param money 钱
     * @return 总次数
     */
    public static int getMoneyCombineNum(int[] moneyType, int[] spMoneyType, int money) {
        if (money < 0) {
            return 0;
        }
        if((moneyType == null || moneyType.length == 0) && (spMoneyType == null || spMoneyType.length == 0)) {
            return money == 0 ? 1 : 0;
        }

        int[] numArr = getMoneyCombineNumArr(moneyType, money);
        int[] numOneArr = getMoneyCombineNumOneArr(spMoneyType, money);

        if (numArr == null) {
            return numOneArr[money];
        }
        if (numOneArr == null) {
            return numArr[money];
        }

        System.out.println(Arrays.toString(numArr));
        System.out.println(Arrays.toString(numOneArr));
        int numSum = 0;
        for (int i = 0; i <= money; i++) {
            numSum += numOneArr[i] * numArr[money - i];

        }
        return numSum;

    }
    /**
     * 根据面值数组获取找零的所有情况总数
     * @param type 面值数组
     * @param money 钱数
     * @return 情况数
     */
    public static int[] getMoneyCombineNumArr(int[] type, int money) {
        if (type == null || type.length == 0) {
            return null;
        }
        // 动态规划，i表示可以使用i号面值之前的所有面值，j表示money
        // 可以优化成2个数组而不是用二位数组
        int[] moneyNum = new int[money + 1];
        for (int i = 0; i <= money; i = i + type[0]) {
            moneyNum[i] = 1;
        }
        for (int i = 1; i < type.length; i++) {
//            int j = 0;
//            for (; j < type[i] && j <= money; j++) {
//                moneyNum[j] = moneyNum[j];
//            }
            for (int j = type[i]; j <= money; j++) {
                moneyNum[j] = moneyNum[j - type[i]] + moneyNum[j];
            }
        }
        return moneyNum;
    }
    /**
     * 根据纪念币面值数组（每种面值只能用一张）获取找零的所有情况总数
     * @param type 面值数组
     * @param money 钱数
     * @return 情况数
     */
    public static int[] getMoneyCombineNumOneArr(int[] type, int money) {
        if (type == null || type.length == 0) {
            return null;
        }
        // 动态规划，i表示可以使用i号面值之前的所有面值，j表示money
        // 可以优化成2个数组而不是用二位数组
        int[][] moneyNum = new int[type.length][money + 1];
        for (int i = 0; i <= money; i = i + type[0]) {
            moneyNum[0][i] = 1;
        }
        for (int i = 1; i < moneyNum.length; i++) {
            int j = 0;
            for (; j < type[i] && j <= money; j++) {
                moneyNum[i][j] = moneyNum[i - 1][j];
            }
            for (; j <= money; j++) {
                moneyNum[i][j] = moneyNum[i - 1][j - type[i]] + moneyNum[i - 1][j];
            }
        }
        return moneyNum[type.length - 1];
    }


    /**
     * 牛客网高级版第一课 根据数组数数依次淘汰应聘者,
     * 应聘者围成一个圈，每次循环那数组中的数，根据数字数到谁谁淘汰
     * 然后从下一个人继续数，输出最后剩下
     * @param pSum 总人数
     * @param nums 数数的数字
     * @return 录取号
     */
    public static int getOfferNum(int pSum, int[] nums) {

        int[] personNumTable = new int[pSum];
        for (int i = 0; i < pSum; i++) {
            personNumTable[i] = i;
        }

        int curSum = pSum;
        int curStartNum = 0;

        for (int i = 0; i < pSum - 1; i++) {
            int outNum = (nums[i % nums.length] - 1 + curStartNum) % curSum;
//            System.out.println(outNum);
//            System.out.println(Arrays.toString(personNumTable));
            curSum--;

            curStartNum = outNum;
            for (int j = outNum; j < curSum; j++) {
                personNumTable[j] = personNumTable[j + 1];
            }
        }
        return personNumTable[0];
    }
    /**
     * 牛客网高级版第一课 根据数依次淘汰应聘者,
     * 应聘者围成一个圈，根据数字数到谁谁淘汰
     * 然后从下一个人继续数，输出最后剩下
     * @param pSum 总人数
     * @param m 数数的数字
     * @return 录取号
     */
    public static int getPerfectOfferNum(int pSum, int m) {
        if (pSum == 1) {
            return 0;
        }
        return (getPerfectOfferNum(pSum - 1, m) + m) % pSum;
    }
    /**
     * 牛客网高级版第一课 根据数组数数依次淘汰应聘者,
     * 应聘者围成一个圈，每次循环那数组中的数，根据数字数到谁谁淘汰
     * 然后从下一个人继续数，输出最后剩下
     * @param pSum 总人数
     * @param nums 数数的数字
     * @return 录取号
     */
    public static int getPerfectOfferNum(int pSum, int[] nums) {
        return getPerfectOfferNum(pSum, nums, 0);
    }
    private static int getPerfectOfferNum(int pSum, int[] nums, int i) {
        if (pSum == 1) {
            return 0;
        }
        return (getPerfectOfferNum(pSum - 1, nums, (i + 1) % nums.length) + nums[i]) % pSum;
    }


    /**
     * 牛客网高级版第二课 2有序数组求第k位数
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
            return getMidfromTwoEqualLengthArr(shortArr, 0, k - 1, longArr, 0, k - 1);
        } else if (k > longArr.length + shortArr.length) {
            int shortStart = k - longArr.length - 1;
            int longStart = k- shortArr.length - 1;
            if (shortArr[shortArr.length - 1] <= longArr[longStart]) {
                return longArr[longStart];
            }
            if (shortArr[shortStart] >= longArr[longArr.length - 1]) {
                return shortArr[shortStart];
            }
            return getMidfromTwoEqualLengthArr(shortArr, shortStart + 1, shortArr.length, longArr, longStart + 1, longArr.length);
        } else {
            int longStart = k - shortArr.length - 1;
            if (longArr[longStart] >= shortArr[shortArr.length - 1]) {
                return longArr[longStart];
            } else {
                return getMidfromTwoEqualLengthArr(shortArr, 0, shortArr.length - 1, longArr, longStart + 1, k - 1);
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
    private static int getMidfromTwoEqualLengthArr(int[] arr1, int start1, int end1,
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
                return getMidfromTwoEqualLengthArr(arr1, start1, mid1, arr2, mid2 + 1, end2);
            } else {
                if (arr1[mid1 - 1] <= arr2[mid2]) {
                    return arr2[mid2];
                } else {
                    return getMidfromTwoEqualLengthArr(arr1, start1, mid1 - 1, arr2, mid2, end2);
                }
            }
        } else {
            if ((end1 - start1 + 1) % 2 == 0) {
                return getMidfromTwoEqualLengthArr(arr1, mid1 + 1, end1, arr2, start2, mid2);
            } else {
                if (arr1[mid1] <= arr2[mid2 - 1]) {
                    return arr1[mid1];
                } else {
                    return getMidfromTwoEqualLengthArr(arr1, mid1, end1, arr2, start2, mid2 - 1);
                }
            }
        }
    }


    /**
     * 获取和为k的最长子数组的长度
     * @param arr 值不为负数的数组
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
     */
    public static int getRouteMaxSumInTree(TreeNode root) {

        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return getSumInfoInTree(root)[0];
    }
    /**
     * 获取树根节点往下的最大路径和以及含根节点最大路径和
     * @param node 根节点
     * @return [最大路径和, 含根节点最大路径和]
     */
    private static int[] getSumInfoInTree(TreeNode node) {
        if (node == null) {
            return null;
        }

        int[] left = getSumInfoInTree(node.getLeft());
        int[] right = getSumInfoInTree(node.getRight());

        int maxSumHead = node.getValue();
        int maxSum = node.getValue();
        if (left != null) {
            if (right != null) {
                // 左右子节点都存在
                maxSumHead = Math.max(left[1], right[1]);
                maxSumHead = maxSumHead > 0 ? maxSumHead + node.getValue() : node.getValue();
                maxSum = Math.max(left[0], right[0]);
                maxSum = Math.max(maxSum, maxSumHead);
            } else {
                // 只存在左节点
                maxSumHead = Math.max(left[1] + node.getValue(), node.getValue());
                maxSum = Math.max(maxSumHead, left[0]);
            }
        } else if (right != null) {
            // 只存在右节点
            maxSumHead = Math.max(right[1] + node.getValue(), node.getValue());
            maxSum = Math.max(maxSumHead, right[0]);
        }
        return new int[]{maxSum, maxSumHead};
    }


    /**
     * 树中所有路径最长长度
     * @param root 根节点
     * @return 最长长度
     */
    public static int getRouteMaxLengthIntree(TreeNode root) {

        if (root == null) {
            return 0;
        }
        return getRouteLengthInfoInTree(root)[0];
    }
    /**
     * 树中最大长度信息
     * @param node 根节点
     * @return [最长长度, 深度]
     */
    private static int[] getRouteLengthInfoInTree(TreeNode node) {

        if (node == null) {
            return null;
        }
        int[] left = getRouteLengthInfoInTree(node.getLeft());
        int[] right = getRouteLengthInfoInTree(node.getRight());

        int maxLength = 1;
        int deep = 1;

        if (left != null) {
            if (right != null) {
                // 左右子节点都存在
                maxLength = Math.max(left[0], right[0]);
                maxLength = Math.max(maxLength, 1 + left[1] + right[1]);
                deep = Math.max(left[1], right[1]) + 1;
            } else {
                // 只存在左节点
                deep = left[1] + 1;
                maxLength = Math.max(left[0], deep);
            }
        } else if (right != null) {
            // 只存在右节点
            deep = right[1] + 1;
            maxLength = Math.max(right[0], deep);
        }
        return new int[]{maxLength, deep};

    }


    /**
     * 获取二位数组从左到右路径做大和，每次只能往右、右上、右下走一格
     * @param map 二维数组
     */
    public static int getMaxRouteLengthOfMap(int[][] map) {
        if (map == null || map.length == 0 || map[0] == null || map[0].length == 0) {
            return -1;
        }
        // 动态规划
        int[][][] valueMap = new int[map.length][map[0].length][2];
        for (int i = 0; i < map.length; i++) {
            // 保存每个已用技能和没用技能情况的值
            valueMap[i][map[0].length - 1][0] = map[i][map[0].length - 1];
            valueMap[i][map[0].length - 1][1] = -map[i][map[0].length - 1];
        }
        // 从右往左倒过来算
        for (int j = map[0].length - 2; j >= 0; j--) {
            for (int i = 0; i < map.length; i++) {
                int valueWithUsed = valueMap[i][j + 1][1];
                int valueWithoutUsed = valueMap[i][j + 1][0];
                if (i >= 1) {
                    valueWithoutUsed = Math.max(valueWithoutUsed, valueMap[i - 1][j + 1][0]);
                    valueWithUsed = Math.max(valueWithUsed, valueMap[i - 1][j + 1][1]);
                }
                if (i <= map.length - 2) {
                    valueWithoutUsed = Math.max(valueWithoutUsed, valueMap[i + 1][j + 1][0]);
                    valueWithUsed = Math.max(valueWithUsed, valueMap[i + 1][j + 1][1]);
                }
                valueMap[i][j][0] = valueWithoutUsed + map[i][j];
                valueMap[i][j][1] = Math.max(valueWithUsed + map[i][j], valueWithoutUsed - map[i][j]);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < valueMap.length; i++) {
            max = Math.max(max, Math.max(valueMap[i][0][0], valueMap[i][0][1]));
        }
        return max;
    }


    /**
     * 获取二位数组从左到右路径做大和，每次只能往右、右上、右下走一格，如果为负，则停止。
     * @param map 二维数组
     * @return 最大和，如果都为负，则输出-1；
     */
    public static int getMaxRouteLengthLimitOfMap(int[][] map) {

        if (map == null || map.length == 0) {
            return -1;
        }

        int maxL = Integer.MIN_VALUE;
        for (int i = 0; i < map.length; i++) {
            // 递归调用
            maxL = Math.max(maxL, getMaxRouteLengthLimitOfMap(map, i, 0, 0, false));
        }
        return maxL;
    }
    /**
     * 获取二位数组从左到右路径做大和，且必须经过(x, y)点。
     * @param map 二维数组
     * @return 最大和，如果都为负，则输出-1；
     */
    private static int getMaxRouteLengthLimitOfMap(int[][] map, int x, int y, int hSum, boolean isUsed) {
        // 计算经过（x， y）点，并且该点之前的路径和为hSum时的最大路径和
        if (y >= map[0].length) {
            return hSum;
        }
        if (hSum < 0) {
            return -1;
        }

        int value = map[x][y];
        // isUsed表示之前是否已使用技能
        if (isUsed) {
            int maxSum = getMaxRouteLengthLimitOfMap(map, x, y + 1, hSum + value, true);
            if (x >= 1) {
                maxSum = Math.max(maxSum, getMaxRouteLengthLimitOfMap(map, x - 1, y + 1, hSum + value, true));
            }
            if (x <= map.length - 2) {
                maxSum = Math.max(maxSum, getMaxRouteLengthLimitOfMap(map, x + 1, y + 1, hSum + value, true));
            }
            return maxSum < 0 ? -1 : maxSum;
        } else {
            int maxSumUsed = getMaxRouteLengthLimitOfMap(map, x, y + 1, hSum - map[x][y], true);
            int maxSumNoused = getMaxRouteLengthLimitOfMap(map, x, y + 1, hSum + map[x][y], false);
            if (x >= 1) {
                maxSumUsed = Math.max(maxSumUsed, getMaxRouteLengthLimitOfMap(map, x - 1, y + 1, hSum - value, true));
                maxSumUsed = Math.max(maxSumUsed, getMaxRouteLengthLimitOfMap(map, x - 1, y + 1, hSum + value, false));
            }
            if (x <= map.length - 2) {
                maxSumUsed = Math.max(maxSumUsed, getMaxRouteLengthLimitOfMap(map, x + 1, y + 1, hSum - value, true));
                maxSumUsed = Math.max(maxSumUsed, getMaxRouteLengthLimitOfMap(map, x + 1, y + 1, hSum + value, false));
            }
            return Math.max(Math.max(maxSumNoused, maxSumUsed), -1);
        }
    }


    /**
     * 根据运算表达式计算结果
     * @param exp 表达式
     * @return 结果
     */
    public static int getValueFromExpression(String exp) {
        return getValueFromExpression(exp.toCharArray(), 0)[0];

    }

    /**
     * 牛客网算法高级班第四课：根据运算表达式计算结果
     * @param expChar 表达式的char数组形式
     * @param start 起始位置
     * @return [计算结果，之后的起始位置]
     */
    private static int[] getValueFromExpression(char[] expChar, int start) {

        Stack<Character> exStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();

        int cur = 0;
        int[] result = null;
        while (start < expChar.length && expChar[start] != ')') {
            if (expChar[start] == ' ') {
                start++;
                continue;
            }
            if (expChar[start] >= '0' && expChar[start] <= '9') {
                // 数字
                cur = cur * 10 + expChar[start++] - '0';
            } else if (expChar[start] == '(') {
                // 递归计算括号内的
                result = getValueFromExpression(expChar, start + 1);
                cur = result[0];
                start = result[1];
            } else {
                // 运算符
                if (exStack.isEmpty() || exStack.peek() == '+' || exStack.peek() == '-') {
                    // 加减直接压栈
                    numStack.push(cur);
                    exStack.push(expChar[start++]);
                } else {
                    int pre = numStack.pop();
                    numStack.push(exStack.pop() == '*' ? pre * cur : pre / cur);
                    exStack.push(expChar[start++]);
                }
                cur = 0;
            }
        }
        int out = 0;

        // 运算符
        if (exStack.peek() == '*' || exStack.peek() == '/') {
            int pre = numStack.pop();
            cur = exStack.pop() == '*' ? pre * cur : pre / cur;
        }

        while (!exStack.isEmpty()) {
            out = exStack.pop() == '+' ? out + cur : out - cur;
            cur = numStack.pop();
        }
        return new int[]{out + cur, start + 1};

    }


    /**
     * 牛客网算法高级班第四课：两人一船，限制最大重量，求出最少需要几只船
     * @param weightArr 所有人的重量数组
     * @param limit 船的限重
     */
    public static int getMinShips(int[] weightArr, int limit) {

        if (weightArr == null || weightArr.length == 0) {
            return 0;
        }
        Arrays.sort(weightArr);
        if (weightArr[weightArr.length - 1] > limit) {
            return -1;
        }
        int midIndex = -1;
        int midValue = limit >> 1;

        for (int i = 0; i < weightArr.length; i++) {
            if (weightArr[i] <= midValue) {
                midIndex = i;
            }
        }
        if (midIndex == -1) {
            return weightArr.length;
        }
        int left = midIndex;
        int right = midIndex + 1;
        int solved = 0;
        while (left >= 0 && right < weightArr.length) {
            if (weightArr[left] + weightArr[right] <= limit) {
                solved++;
                left--;
                right++;
            } else {
                left--;
            }
        }
//        return solved + (midIndex - solved) / 2 + 1 + weightArr.length - midIndex - 1 - solved;
        return (midIndex - solved) / 2 + weightArr.length - midIndex;
    }
    public static int getMinShips2(int[] weightArr, int limit) {

        if (weightArr == null || weightArr.length == 0) {
            return 0;
        }
        Arrays.sort(weightArr);
        if (weightArr[weightArr.length - 1] > limit) {
            return -1;
        }

        int midValue = limit >> 1;

        int left = 0;
        int right = weightArr.length - 1;
        int num = 0;
        for (; right >= left; right--) {
            if (weightArr[right] <= midValue) {
                num = num + (right - left) / 2 + 1;
                // 保障不会出循环后又n++；
                right = left + 1;
                break;
            }
            if (weightArr[left] + weightArr[right] <= limit) {
                left++;
            }
            num++;
        }
        if (left == right) {
            num++;
        }
        return num;
    }


    /**
     * 牛客网算法高级班第四课：最长回文子序列
     */
    public static int getMaxLengthOfPalindrome(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }
        // 动态规划保存i到j字符串的最大回文字序列长度。
        int[][] matrix = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            matrix[i][i] = 1;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                matrix[i][i + 1] = 2;
            } else {
                matrix[i][i + 1] = 1;
            }
        }

        for (int j = 2; j < str.length(); j++) {
            for (int i = 0; i < str.length() - j; i++) {
                if (str.charAt(i) == str.charAt(i + j)) {
                    matrix[i][i + j] = matrix[i + 1][i + j - 1] + 2;
                } else {
                    matrix[i][i + j] = Math.max(matrix[i + 1][i + j], matrix[i][i + j - 1]);
                }
            }
        }
        return matrix[0][str.length() - 1];
    }
    /**
     * 最长回文子序列,使用最长公共子序列长度来求
     */
    public static int getMaxLengthOfPalindrome2(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }
        // 字符串与其逆字符串的最长公共子序列为最长呢回文长度
        char[] chars1 = new char[str.length()];
        char[] chars2 = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars1[i] = str.charAt(i);
            chars2[str.length() - i - 1] = str.charAt(i);
        }
        return MyCoder.maxLengthOfCommonStr(chars1, chars2);
    }


    /**
     * 牛客网算法高级班第四课：变成回文字所需插入的最少数
     */
    public static int getMinNumToBePalindrome(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 动态规划保存i到j字符串的变成回文字所需插入的最少数。
        int[][] matrix = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            matrix[i][i] = 0;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                matrix[i][i + 1] = 0;
            } else {
                matrix[i][i + 1] = 1;
            }
        }
        for (int up = 2; up < str.length(); up++) {
            for (int i = 0; i < str.length() - up; i++) {
                int j = i + up;
                if (str.charAt(i) == str.charAt(j)) {
                    matrix[i][j] = matrix[i + 1][j - 1];
                } else {
                    matrix[i][j] = 1 + Math.min(matrix[i + 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[0][str.length() - 1];
    }


    /**
     * 第五课：后一半和前一半交叉重排，空间复杂度o(n)
     * 目前有问题，不完善。存在闭环。
     */
    public static void acrossSort(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length % 2 != 0) {
            return;
        }

        int cur = 0;
        int next = 1;
        int curValue = arr[0];
        int temp = 0;
        int mid = arr.length >> 1;
        while (next != 0) {

            if (cur < mid) {
                next = 2 * cur + 1;
            } else {
                next = 2 * (cur - mid);
            }
            temp = arr[next];
            arr[next] = curValue;
            curValue = temp;
            cur = next;
        }

    }


    /**
     * 第六课：获取数组中每个数离他最近的比他小的左和右的
     * @return 数组中每个元素：[左最近, 右最近]
     */
    public static int[][] getNearestMin(int[] arr) {

        if (arr == null) {
            return null;
        }

        int[][] mins = new int[arr.length][];
        if (arr.length == 0) {
            return mins;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[stack.peek()]) {
                stack.push(i);
            } else {
                mins[stack.pop()] = new int[]{stack.isEmpty() ? -1 : arr[stack.peek()], arr[i]};
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            mins[stack.pop()] = new int[]{-1, stack.isEmpty() ? -1 : stack.peek()};
        }
        return mins;
    }


    /**
     * 第六课：环形山，相互之间能看见的山峰为一对，一共多少对
     * @param arr 山峰高度数组
     * @return 总对数
     */
    public static int countOfAbleSee(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int count = 0;
        Stack<int[]> stack = new Stack<>();

        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }

        stack.push(new int[]{maxIndex, 1});
        for (int i = maxIndex + 1; ; i++) {
            if (i == arr.length) {
                i = 0;
            }
            if (i == maxIndex) {
                break;
            }
            if (arr[i] > arr[stack.peek()[0]]) {
                int[] popElem = stack.pop();
                count = count + 2 * popElem[1] + popElem[1] * (popElem[1] - 1) / 2;
                if (arr[i] == arr[stack.peek()[0]]) {
                    stack.peek()[1] = stack.peek()[1] + 1;
                } else {
                    stack.push(new int[]{i, 1});
                }
            } else if (arr[i] == arr[stack.peek()[0]]) {
                stack.peek()[1] = stack.peek()[1] + 1;
            } else {
                stack.push(new int[]{i, 1});
            }
        }
        while (stack.size() >= 3) {
            int[] popElem = stack.pop();
            count = count + 2 * popElem[1] + popElem[1] * (popElem[1] - 1) / 2;
        }

        int[] popElem = stack.pop();
        if (stack.isEmpty()) {
            count = count + popElem[1] * (popElem[1] - 1) / 2;
        } else {
            if (stack.peek()[1] >= 2) {
                count = count + 2 * popElem[1] + stack.peek()[1] * (stack.peek()[1] - 1) / 2;
            } else {
                count = count + popElem[1];
            }
        }




        return count;


    }


    /**
     * 第六课：字符串匹配，？匹配单个，*匹配0或多个(自己占位，例如*匹配空、a、ab等)
     */
    public static boolean samplePattern(String str, String pattern) {
        if (str == null || str.length() == 0 || pattern == null || pattern.length() == 0) {
            return false;
        }
        return samplePattern(str.toCharArray(), pattern.toCharArray(), 0, 0);
    }
    private static boolean samplePattern(char[] str, char[] pattern, int sStart, int pStart) {

        if (sStart == str.length) {
            return pStart == pattern.length;
        } else if (pStart == pattern.length) {
            return false;
        }

        if (pattern[pStart] == '?' || str[sStart] == pattern[pStart]) {
            return samplePattern(str, pattern, sStart + 1, pStart + 1);
        }
        if (pattern[pStart] == '*') {
            boolean flag = false;
            for (int i = sStart; i <= str.length; i++) {
                flag = samplePattern(str, pattern, i, pStart + 1);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 第六课：字符串匹配动态规划版，？匹配单个，*匹配0或多个(自己占位，例如*匹配空、a、ab等)
     */
    public static boolean dpPattern(String str, String pattern) {

        if (str == null || pattern == null) {
            return false;
        }
        if (str.length() == 0 && pattern.length() == 0) {
            return true;
        }

        boolean[][] dp = new boolean[pattern.length() + 1][str.length() + 1];
        dp[0][0] = true;

        int j = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '*') {
                for (int k = j; k <= str.length(); k++) {
                    dp[i + 1][k] = dp[i][j];
                }
            } else {
                for (int k = j; k < str.length(); k++) {
                    dp[i + 1][k + 1] = dp[i][k] && (pattern.charAt(i) == str.charAt(k) || pattern.charAt(i) == '?');
                }
                j++;
            }
        }
//        for (boolean[] a : dp) {
//            System.out.println(Arrays.toString(a));
//        }
        return dp[pattern.length()][str.length()];
    }
    /**
     * 第六课：字符串匹配动态规划版，？匹配单个，*匹配0或多个(自己不占位，例如a*匹配空、a、aa等)
     */
    public static boolean newDpPattern(String str, String pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        if (str.length() == 0 && pattern.length() == 0) {
            return true;
        }
        boolean[][] dp = new boolean[pattern.length() + 1][str.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < pattern.length() + 1; i++) {
            for (int j = 1; j < str.length() + 1; j++) {
                if (pattern.charAt(i - 1) == str.charAt(j - 1) || pattern.charAt(i - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 2][j] || dp[i - 1][j];
                    if (pattern.charAt(i - 2) == str.charAt(j - 1) || pattern.charAt(i - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                    }

                }
            }
        }
//        for (boolean[] a : dp) {
//            System.out.println(Arrays.toString(a));
//        }
        return dp[pattern.length()][str.length()];
    }


    /**
     * 第七课：2个字符串是否互为旋变字符串
     */
    static boolean isRotateString(String str1, String str2) {

        if (!isSameTypeNum(str1, str2)) {
            return false;
        }

        int len = str1.length();
        char[] ca1 = str1.toCharArray();
        char[] ca2 = str2.toCharArray();

        // 动态规划
        boolean[][][] dp = new boolean[len][len][len + 1];

        for (int i1 = 0; i1 < len; i1++) {
            for (int i2 = 0; i2 < len; i2++) {
                dp[i1][i2][1] = ca1[i1] == ca2[i2];
            }
        }

        for (int size = 2; size <= len; size++) {
            for (int i1 = 0; i1 <= len - size; i1++) {
                for (int i2 = 0; i2 <= len - size; i2++) {
                    for (int s = 1; s < size; s++) {
                        if (dp[i1][i2][s] && dp[i1 + s][i2 + s][size - s] ||
                                (dp[i1 + s][i2][size - s] && dp[i1][i2 + s][s])) {
                            dp[i1][i2][size] = true;
                            break;
                        }
                    }
                }
            }
        }


        return dp[0][0][len];

    }

    /**
     * 两字符串是否长度相同且组成的字母种类和数量是否都相同
     */
    private static boolean isSameTypeNum(String str1, String str2) {

        if (str1 == null) {
            return str2 == null;
        } else if (str2 == null) {
            return false;
        }
        if (str1.length() == 0) {
            return str2.length() == 0;
        } else if (str2.length() == 0) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] map = new int[128];
        for (int i = 0; i < str1.length(); i++) {
            map[str1.charAt(i)] = map[str1.charAt(i)] + 1;
            map[str1.charAt(i)] = map[str1.charAt(i)] - 1;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 我自己想的方法
     * 字符串1中包含字符串2所有字符的最小字串长度
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最小长度
     */
    public static int minLengthOfStr1ContainStr2(String str1, String str2) {
        if (str2 == null || str2.length() == 0) {
            return 0;
        }
        if (str1 == null || str1.length() == 0) {
            return -1;
        }

        HashMap<Character, Integer> hm = new HashMap<>();
        int[] str2chars = new int[128];
        for (int i = 0; i < str2.length(); i++) {
            str2chars[str2.charAt(i)] += 1;
            if (hm.containsKey(str2.charAt(i))) {
                hm.replace(str2.charAt(i), hm.get(str2.charAt(i)) + 1);
            } else {
                hm.put(str2.charAt(i), 1);
            }
        }

        int pre = 0;
        int minL = str1.length();

        char[] ca = str1.toCharArray();
        int i = -1;
        while (true) {
            if (hm.isEmpty()) {
                minL = Math.min(minL, i - pre + 1);
                if (str2chars[ca[pre]] >= 0) {
                    hm.put(ca[pre], 1);
                }
                str2chars[ca[pre]] += 1;
                pre++;
            } else {
                i++;
                if (i >= str1.length()) {
                    break;
                }
                str2chars[ca[i]] -= 1;
                if (hm.containsKey(ca[i])) {
                    hm.replace(ca[i], hm.get(ca[i]) - 1);
                    if (hm.get(ca[i]) == 0) {
                        hm.remove(ca[i]);
                    }
                }
            }


        }
        return minL == str1.length() ? -1 : minL;
    }
    /**
     * 牛客网老师的方法
     * 字符串1中包含字符串2所有字符的最小字串长度
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最小长度
     */
    public static int minLengthOfStr1ContainStr2New(String str1, String str2) {
        if (str2 == null || str2.length() == 0) {
            return 0;
        }
        if (str1 == null || str1.length() == 0) {
            return -1;
        }

        // 保存次数
        int[] charNumArr = new int[128];
        for (int i = 0; i < str2.length(); i++) {
            charNumArr[str2.charAt(i)] += 1;
        }

        char[] ca = str1.toCharArray();
        int pre = 0;
        int cur = -1;
        int minLen = str1.length();
        // 保存总数
        int all = str2.length();

        while (true) {
            if (all == 0) {
                minLen = Math.min(minLen, cur - pre + 1);
                charNumArr[ca[pre]] += 1;
                if (charNumArr[ca[pre]] > 0) {
                    all++;
                }
                pre++;
            } else {
                cur++;
                if (cur >= str1.length()) {
                    break;
                }
                charNumArr[ca[cur]] -= 1;
                if (charNumArr[ca[cur]] >= 0) {
                    all--;
                }
            }
        }
        return minLen == str1.length() ? -1 : minLen;
    }
}


















