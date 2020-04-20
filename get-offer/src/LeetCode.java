import model.TreeNode;
import myTools.MyArrayTools;

import java.util.*;

public class LeetCode {

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 997. 找到小镇的法官
     */
    public static int findJudge(int N, int[][] trust) {
        if (trust.length == 0) {
            return N == 1 ? 1 : -1;
        }

        int[] trustCount = new int[N + 1];
        int[] trustedCount = new int[N + 1];
        for (int i = 0; i < trust.length; i++) {
            trustCount[trust[i][1]]++;
            trustedCount[trust[i][0]]++;
        }

        int judge = -1;
        for (int i = 1; i < N + 1; i++) {
            if (trustCount[i] == N - 1 && trustedCount[i] == 0) {
                if (judge == -1) {
                    judge = i;
                } else {
                    return -1;
                }
            }
        }
        return judge;
    }


    /**
     * 942. 增减字符串匹配
     */
    public static int[] diStringMatch(String abba, String S) {

        int[] arr = new int[S.length() + 1];

        int small = 0;
        int large = S.length();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'I') {
                arr[i] = small;
                small++;
            } else {
                arr[i] = large;
                large--;
            }
        }
        arr[S.length()] = small;
        return arr;
    }


    /**
     * 739. 每日温度
     */
    public static int[] dailyTemperatures(int[] T) {

        int[] outArr = new int[T.length];

        outArr[T.length - 1] = 0;

        for (int i = T.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < T.length; j += outArr[j]) {
                if (T[j] > T[i]) {
                    outArr[i] = j - i;
                    break;
                } else if (outArr[j] == 0) {
                    outArr[i] = 0;
                    break;
                }
            }

        }

        return outArr;

    }


    /**
     * 290. 单词规律
     */
    public static boolean wordPattern(String pattern, String str) {

        String[] strs = str.split(" ");
        HashMap<Character, String> map = new HashMap<>();
        if (pattern.length() != strs.length) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(strs[i])) {
                    return false;
                }
            } else if (map.containsValue(strs[i])) {
                return false;
            } else {
                map.put(c, strs[i]);
            }
        }
        return true;

    }


    /**
     * 448. 找到所有数组中消失的数字
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组
     * 数组中的元素一些出现了两次，另一些只出现一次。
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }

        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i] - 1 && nums[i] != nums[nums[i] - 1]) {
                MyArrayTools.swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) {
                list.add(i + 1);
            }
        }
        return list;
    }


    /**
     * 80. 删除排序数组中的重复项 II
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int delCount = 0;
        int curIndex = 0;
        int pre = 0;
        int preVal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == preVal) {
                if (i - pre > 1) {
                    delCount++;
                } else {
                    nums[++curIndex] = nums[i];
                }
            } else {
                pre = i;
                preVal = nums[i];
                nums[++curIndex] = nums[i];
            }
        }
        return nums.length - delCount;
    }


    /**
     * 1324. 竖直打印单词
     * 给你一个字符串 s。请你按照单词在 s 中的出现顺序将它们全部竖直返回。
     * 单词应该以字符串列表的形式返回，必要时用空格补位，但输出尾部的空格需要删除（不允许尾随空格）。
     * 每个单词只能放在一列上，每一列中也只能有一个单词。
     */
    public static List<String> printVertically(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.trim().length() == 0) {
            return result;
        }

        String[] arr = s.trim().split("\\s+");
        char[] chars = new char[arr.length];

        int len = 0;
        for (String a : arr) {
            len = Math.max(len, a.length());
        }

        int end = 0;
        for (int i = 0; i < len; i++) {
            end = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].length() <= i) {
                    chars[j] = ' ';
                } else {
                    chars[j] = arr[j].charAt(i);
                    end = j;
                }
            }

            result.add(new String(chars, 0, end + 1));
        }
        return result;
    }

    /**
     * 56. 合并区间
     * 给出一个区间的集合，请合并所有重叠的区间。
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[][] result = new int[intervals.length][];
        result[0] = new int[]{intervals[0][0], intervals[0][1]};
        int pre = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > result[pre][1]) {
                result[++pre] = new int[]{intervals[i][0], intervals[i][1]};
            } else {
                result[pre][1] = Math.max(result[pre][1], intervals[i][1]);
            }
        }
        return Arrays.copyOf(result, pre + 1);
    }


    /*
     * 190. 颠倒二进制位
     * 颠倒给定的 32 位无符号整数的二进制位。
     */
    public static int reverseBits(int n) {
        int newN = 0;
        for (int i = 0; i < 32; i++) {
            newN = (newN << 1) + (n & 1);
            n = n >> 1;
        }
        return newN;
    }


    /*
     * 面试题21. 调整数组顺序使奇数位于偶数前面
     */
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int pre = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                pre++;
                if (i != pre) {
                    swap(nums, i, pre);
                }
            }
        }
        return nums;
    }


    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeCore(nums, 0, nums.length - 1);
    }
    private static TreeNode constructMaximumBinaryTreeCore(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = maxIndex(nums, start, end);

        TreeNode node = new TreeNode(nums[mid]);
        node.left = constructMaximumBinaryTreeCore(nums, start, mid - 1);
        node.right = constructMaximumBinaryTreeCore(nums, mid + 1, end);
        return node;
    }
    private static int maxIndex(int[] nums, int start, int end) {
        int maxIndex = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    /*
     * 面试题 16.11. 跳水板
     * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
     * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     * 返回的长度需要从小到大排列。
     */
    public static int[] divingBoard(int shorter, int longer, int k) {
        if (k <= 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{k * shorter};
        }
        int[] result = new int[k + 1];
        result[0] = shorter * k;
        int gap = longer - shorter;
        for (int i = 1; i <= k; i++) {
            result[i] = result[i - 1] + gap;
        }
        return result;
    }


    /*
     * 面试题42. 连续子数组的最大和
     * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = nums[0];
        int pre = 0;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            sum = Math.max(curSum, sum);
            if (curSum < 0) {
                curSum = 0;
            }
        }
        return sum;
    }


    /*
     * 974. 和可被 K 整除的子数组
     * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
     */
    public static int subarraysDivByK(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] arr = new int[K];
        int sum = 0;
        int count = 0;
        for (int a : A) {
            sum = ((sum + a) % K + K) % K;
            count += arr[sum];
            if (sum == 0) {
                count++;
            }
            arr[sum]++;
        }
        return count;
    }


    /*
     * 337. 打家劫舍 III
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     */
    public static int rob(TreeNode root) {
        int[] re = robCore(root);
        return Math.max(re[0], re[1]);
    }
    private static int[] robCore(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = robCore(root.left);
        int[] right = robCore(root.right);

        return new int[]{Math.max(left[0], left[1]) + Math.max(right[0], right[1]),
                (int)root.val + left[0] + right[0]};
    }


    /*
     * 784. 字母大小写全排列
     * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
     */
    public static List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        if (S == null || S.length() == 0) {
            return list;
        }
        char[] arr = S.toCharArray();
        letterCasePermutationCore(arr, 0, list);
        return list;
    }
    private static void letterCasePermutationCore(char[] arr, int start, List<String> list) {
        int i = start;
        for (; i < arr.length; i++) {
            if (arr[i] >= 'a' && arr[i] <= 'z') {
                letterCasePermutationCore(arr, i + 1, list);
                arr[i] -= 32;
                letterCasePermutationCore(arr, i + 1, list);
                arr[i] += 32;
                break;
            } else if (arr[i] >= 'A' && arr[i] <= 'Z') {
                letterCasePermutationCore(arr, i + 1, list);
                arr[i] += 32;
                letterCasePermutationCore(arr, i + 1, list);
                arr[i] -= 32;
                break;
            }
        }
        if (i == arr.length) {
            list.add(new String(arr));
        }
    }

    public static void main(String[] args) {

        System.out.println(letterCasePermutation("a1b2"));

    }



}
