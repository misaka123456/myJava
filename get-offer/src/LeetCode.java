import model.ListNode;
import model.TreeNode;
import myTools.MyArrayTools;

import java.util.*;


@SuppressWarnings("all")
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


    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     */
    public static int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    setIsland(grid, i, j);
                }
            }
        }
        return count;
    }
    private static void setIsland(char[][] grid, int x, int y) {
        grid[x][y] = '0';
        if (x > 0 && grid[x - 1][y] == '1') {
            setIsland(grid, x - 1, y);
        }
        if (x < grid.length - 1 && grid[x + 1][y] == '1') {
            setIsland(grid, x + 1, y);
        }
        if (y > 0 && grid[x][y - 1] == '1') {
            setIsland(grid, x, y - 1);
        }
        if (y < grid[0].length - 1 && grid[x][y + 1] == '1') {
            setIsland(grid, x, y + 1);
        }
    }


    /**
     * 面试题32 - III. 从上到下打印二叉树 III
     * 请实现一个函数按照之字形顺序打印二叉树，
     * 即第一行按照从左到右的顺序打印，
     * 第二层按照从右到左的顺序打印，
     * 第三行再按照从左到右的顺序打印，
     * 其他行以此类推。
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        TreeNode node = root;
        leftStack.push(root);

        List<Integer> list;
        while (!leftStack.isEmpty()) {
            list = new ArrayList<>();
            while (!leftStack.isEmpty()) {
                node = leftStack.pop();
                list.add((int)node.val);
                if (node.left != null) {
                    rightStack.push(node.left);
                }
                if (node.right != null) {
                    rightStack.push(node.right);
                }
            }
            result.add(list);
            if (rightStack.isEmpty()) {
                break;
            }
            list = new ArrayList<>();
            while (!rightStack.isEmpty()) {
                node = rightStack.pop();
                list.add((int)node.val);
                if (node.right != null) {
                    leftStack.push(node.right);
                }
                if (node.left != null) {
                    leftStack.push(node.left);
                }
            }
            result.add(list);
        }
        return result;
    }


    /**
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     *
     * 示例：
     *
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     */
    public static String _0076_最小覆盖子串(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int count = 0;
        int start = 0;
        int end = s.length();
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int temp = map.get(c) - 1;
                map.put(c, temp);
                if (temp == 0) {
                    count++;
                    if (count == map.size()) {
                        while (pre < s.length()) {
                            if (i - pre < end - start) {
                                start = pre;
                                end = i;
                            }
                            c = s.charAt(pre);
                            if (map.containsKey(c)) {
                                temp = map.get(c) + 1;
                                map.put(c, temp);
                                if (temp == 1) {
                                    count--;
                                    pre++;
                                    break;
                                }
                            }
                            pre++;
                        }
                    }
                }
            }
        }
        if (end == s.length()) {
            return "";
        }
        return s.substring(start, end + 1);
    }


    /**
     * 1209. 删除字符串中的所有相邻重复项 II
     * 给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
     * 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
     * 在执行完所有删除操作后，返回最终得到的字符串
     */
    public static String removeDuplicates(String s, int k) {
        if (s == null || s.length() < k) {
            return s;
        }
        Stack<Character> chStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        char c;
        int count;
        for (int i = s.length() - 1; i >= 0; i--) {
            c = s.charAt(i);
            if (chStack.isEmpty() || chStack.peek() != c) {
                chStack.push(c);
                countStack.push(1);
            } else {
                count = countStack.pop() + 1;
                if (count == k) {
                    chStack.pop();
                } else {
                    countStack.push(count);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!chStack.isEmpty()) {
            count = countStack.pop();
            c = chStack.pop();
            while (count-- > 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     */
    public int _0518_零钱兑换_II(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins.length == 0) {
            return 0;
        }
        int[] money = new int[amount + 1];
        money[0] = 1;
        for (int c : coins) {
            for (int i = c; i <= amount; i++) {
                money[i] = money[i] + money[i - c];
            }
        }
        return money[amount];
    }


    /**
     * 爱丽丝有一手（hand）由整数数组给定的牌。 
     * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
     * 如果她可以完成分组就返回 true，否则返回 false。
     */
    public static boolean _0846_一手顺子(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        for (int h : hand) {
            int count = map.getOrDefault(h, 0);
            if (count != 0) {
                for (int i = 0; i < W; i++) {
                    count = map.getOrDefault(h + i, 0);
                    if (count == 0) {
                        return false;
                    } else if (count == 1){
                        map.remove(h + i);
                    } else {
                        map.put(h + i, count - 1);
                    }
                }
            }
        }
        return map.isEmpty();
    }


    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     */
    public static int[] _0034_在排序数组中查找元素的第一个和最后一个位置(int[] nums, int target) {
        int[] out = new int[] {-1, -1};
        int len = nums.length - 1;
        if (len == -1 || target < nums[0] || target > nums[len]) {
            return out;
        }
        int start = 0;
        int end = len;
        int mid;
        if (nums[0] == target) {
            out[0] = 0;
        } else {
            while (start < end) {
                mid = (start + end) >> 1;
                if (nums[mid + 1] == target) {
                    if (nums[mid] < target) {
                        out[0] = mid + 1;
                        break;
                    } else {
                        end = mid;
                    }
                } else if (nums[mid + 1] > target) {
                    end = mid;
                } else {
                    start = mid + 2;
                }
            }
            if (start == end) {
                if (nums[start] == target) {
                    out[0] = start;
                } else {
                    return out;
                }
            }
        }
        if (nums[len] == target) {
            out[1] = len;
        } else {
            end = len;
            while (start < end) {
                mid = (start + end) >> 1;
                if (nums[mid] == target) {
                    if (nums[mid + 1] > target) {
                        out[1] = mid;
                        break;
                    } else {
                        start = mid + 1;
                    }
                } else if (nums[mid] > target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if (start == end) {
                out[1] = start;
            }
        }
        return out;
    }


    public int _1128_等价多米诺骨牌对的数量(int[][] dominoes) {
        if (dominoes.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum;
        for (int[] d : dominoes) {
            if (d[0] <= d[1]) {
                sum = d[1] * 1000 + d[0];
            } else {
                sum = d[0] * 1000 + d[1];
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        int count = 0;
        for (int val : map.values()) {
            count += (val - 1) * val / 2;
        }
        return count;
    }


    public static int _0388_文件的最长绝对路径(String input) {
        if (input.length() == 0) {
            return 0;
        }
        int maxLen = 0;
        int curMaxLen = 0;
        List<Integer> list = new ArrayList<>();
        int start = 0;
        int len = 0;
        int curFloor = 0;
        int i = 0;
        boolean isFile = false;
        for (; i < input.length(); i++) {
            if (input.charAt(i) == '.') {
                isFile = true;
            }
            if (input.charAt(i) == '\n') {
                list.add(i);
                break;
            }
        }
        if (isFile) {
            maxLen = i;
        }
        while (i < input.length()) {
            curFloor = 0;
            i += 1;
            while (input.charAt(i) == '\t') {
                curFloor++;
                i += 1;
            }
            start = i;
            isFile = false;
            while (i != input.length() && input.charAt(i) != '\n') {
                if (input.charAt(i) == '.') {
                    isFile = true;
                }
                i++;
            }
            len = i - start;
            if (isFile) {
                curMaxLen = curFloor + len;
                while (--curFloor >= 0) {
                    curMaxLen += list.get(curFloor);
                }
                maxLen = Math.max(maxLen, curMaxLen);
            } else {
                if (list.size() == curFloor) {
                    list.add(len);
                } else {
                    list.set(curFloor, len);
                }
            }
        }
        return maxLen;
    }



    public static List<List<Integer>> _0047_全排列_II(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);
        permuteUnique(nums, new int[nums.length], new boolean[nums.length], 0, result);
        return result;



    }
    private static void permuteUnique(int[] nums, int[] route, boolean[] used, int len, List<List<Integer>> result) {
        if (len == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int r : route) {
                list.add(r);
            }
            result.add(list);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            route[len] = nums[i];
            permuteUnique(nums, route, used, len + 1, result);
            used[i] = false;
        }
    }


    /**
     *
     */
    static int count = 0;
    public static int _1079_活字印刷(String tiles) {
        if (tiles == null || tiles.length() == 0) {
            return 0;
        }
        char[] nums = tiles.toCharArray();
        Arrays.sort(nums);
        numTilePossibilities(nums, new boolean[nums.length], 0);
        return count - 1;
    }
    private static void numTilePossibilities(char[] nums, boolean[] used, int len) {
        count++;
        if (len == nums.length) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            numTilePossibilities(nums, used, len + 1);
            used[i] = false;
        }
    }


    public static int _1300_转变数组后最接近目标值的数组和(int[] arr, int target) {
        if (arr.length == 1) {
            return target;
        }
        int a = 0;
        int b = 0;
        Arrays.sort(arr);
        if (target <= arr.length * arr[0]) {
            a = target / arr.length ;
            b = target / arr.length + 1;
            if (target - a * arr.length <= b * arr.length - target) {
                return a;
            } else {
                return b;
            }
        }


        int[] curSumArr = new int[arr.length];
        curSumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curSumArr[i] = curSumArr[i - 1] + arr[i];
        }

        int curClosestSum = 0;

        int l = 0;
        int r = arr.length - 1;
        int mid = 0;

        while (r - l >= 2) {
            mid = (l + r) >> 1;
            curClosestSum = curSumArr[mid - 1] + arr[mid] * (arr.length - mid);
            if (curClosestSum == target) {
                return arr[mid];
            } else if (curClosestSum < target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        a = (target - curSumArr[l]) / (arr.length - r);
        b = (target - curSumArr[l]) / (arr.length - r) + 1;
        if (target - curSumArr[l] - (arr.length - r) * a <= (arr.length - r) * b - target + curSumArr[l]) {
            return a;
        } else {
            return b;
        }
    }


    public static ListNode _1171_从链表中删去总和值为零的连续节点(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode myHead = new ListNode(0);
        myHead.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        ListNode node = myHead.next;
        int curSum = 0;
        while (node != null) {
            curSum = node.val + (stack.isEmpty() ? 0 : stack.peek());
            if (curSum == 0) {
                stack.clear();
                map.clear();
                myHead = node;

            } else if (map.containsKey(curSum)) {
                map.get(curSum).next = node.next;

                while (stack.peek() != curSum) {
                    map.remove(stack.pop());
                }
            } else {
                map.put(curSum, node);
                stack.push(curSum);

            }
            node = node.next;
        }
        return myHead.next;
    }



    public static int[] _0260_只出现一次的数字_III(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum = sum ^ n;
        }
        int gap = sum & (-sum);
        int a = 0;
        int b = 0;
        for (int n : nums) {
            if ((n & gap) == gap) {
                a = a ^ n;
            } else {
                b = b ^ n;
            }
        }
        return new int[]{a, b};

    }


    public static int _0279_完全平方数(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = n;
        }
        List<Integer> list = new ArrayList<>();
        int k = 1;
        while (true) {
            int diff = n - k * k;
            if (diff < 0) {
                break;
            } else if (diff <= 1){
                return diff + 1;
            } else {
                list.add(n - diff);
            }
            k++;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i - list.get(j) >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - list.get(j)] + 1);
                }
            }
        }
        return dp[n];
    }


    public static List<List<String>> _1331_数组序号转换(String[] paths) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = null;
        for (String path : paths) {
            int index = path.indexOf(' ');
            String fatherPath = path.substring(0, index) + "/";
            String[] files = path.substring(index + 1).split(" ");
            for (String file : files) {
                int valueIndex = file.indexOf('(');
                String value = file.substring(valueIndex + 1, file.length() - 1);
                if (map.containsKey(value)) {
                    list = map.get(value);
                    if (list.size() == 1) {
                        result.add(list);
                    }
                } else {
                    list = new ArrayList<>();
                    map.put(value, list);
                }
                list.add(fatherPath + file.substring(0, valueIndex));
            }





        }
        return result;
    }


    /**
     * 给定一个由空格分割单词的句子 S。每个单词只包含大写或小写字母。
     *
     * 我们要将句子转换为 “Goat Latin”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。
     *
     * 山羊拉丁文的规则如下：
     *
     * 如果单词以元音开头（a, e, i, o, u），在单词后添加"ma"。
     * 例如，单词"apple"变为"applema"。
     *
     * 如果单词以辅音字母开头（即非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
     * 例如，单词"goat"变为"oatgma"。
     *
     * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从1开始。
     * 例如，在第一个单词后添加"a"，在第二个单词后添加"aa"，以此类推。
     * 返回将 S 转换为山羊拉丁文后的句子。
     */
    public static String _0824_山羊拉丁文(String S) {
        if (S.length() == 0) {
            return "";
        }
        String[] sArr = S.split(" ");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            char c = sArr[i].charAt(0);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                    c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                sb.append(sArr[i]);
            } else {
                sb.append(sArr[i].substring(1));
                sb.append(c);
            }
            sb.append("ma");
            for (int n = 0; n <= i; n++) {
                sb.append("a");
            }
            i++;
            if (i == sArr.length) {
                break;
            }
            sb.append(' ');
        }
        return sb.toString();
    }


    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 示例:
     *
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    public static List<List<Integer>> _0077_组合(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < k) {
            return result;
        }
        int[] arr = new int[k];
        combine(n, k, 0, 0, arr, result);
        return result;
    }
    private static void combine(int n, int k, int curCount, int preValue, int[] arr, List<List<Integer>> result) {
        if (curCount == k) {
            List<Integer> list = new ArrayList<>();
            for (int a : arr) {
                list.add(a);
            }
            result.add(list);
            return;
        }
        for (int i = preValue + 1; i <= n; i++) {
            arr[curCount] = i;
            combine(n, k, curCount + 1, i, arr, result);
        }
    }


    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public ListNode _0002_两数相加(ListNode l1, ListNode l2) {

        ListNode l1Child = l1;
        ListNode l2Child = l2;

        ListNode head = l1;
        int top = 0;
        while (l1Child != null && l2Child != null) {
            int sum = top + l1Child.val + l2Child.val;
            if (sum >= 10) {
                top = 1;
                sum -= 10;
                if (l1Child.next == null && l2Child.next == null) {
                    l1Child.val = sum;
                    l1Child.next = new ListNode(1);
                    return head;
                }
            } else {
                top = 0;
            }
            l1Child.val = sum;
            l2Child.val = sum;
            l1Child = l1Child.next;
            l2Child = l2Child.next;
        }

        if (l1Child == null) {
            head = l2;
            l1Child = l2Child;
        }
        while (l1Child != null) {
            if (top == 0 || l1Child.val != 9) {
                l1Child.val = top + l1Child.val;
                break;
            }
            if (l1Child.val == 9) {
                l1Child.val = 0;
                if (l1Child.next == null) {
                    l1Child.next = new ListNode(1);
                    break;
                }
                top = 1;
                l1Child = l1Child.next;
            }
        }
        return head;

    }


    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     */
    public static String _0005_最长回文子串(String s) {
        if (s.length() == 0) {
            return "";
        }
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[arr.length][arr.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }
        int maxL = 0;
        int maxR = 0;
        for (int j = 1; j < dp.length; j++) {
            if ((dp[j - 1][j] = arr[j - 1] == arr[j]) && maxR - maxL == 0) {
                maxL = j - 1;
                maxR = j;
            }
            for (int i = j - 2; i >= 0; i--) {
                if ((dp[i][j] = dp[i + 1][j - 1] && (arr[i] == arr[j])) && j - i > maxR - maxL) {
                    maxL = i;
                    maxR = j;
                }
            }
        }
        return s.substring(maxL, maxR + 1);
    }


    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 示例：
     *
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     */
    public static List<List<Integer>> _0015_三数之和(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums.length <= 2) {
            return result;
        }
        Arrays.sort(nums);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(-nums[i], i);
        }
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int lastIndex = map.getOrDefault(nums[i] + nums[j], -1);
                if (lastIndex > j) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[lastIndex]));
                }
            }
        }
        return result;
    }


    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     */
    public ListNode _0023_合并K个排序链表(ListNode[] lists) {
        ListNode head = new ListNode();
        ListNode node = head;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }
        while (!heap.isEmpty()) {
            ListNode temp = heap.poll();
            node.next = temp;
            node = temp;
            temp = temp.next;
            if (temp != null) {
                heap.offer(temp);
            }
        }
        return head.next;
    }



    public static int[][] reconstructQueue(int[][] people) {
        LinkedList<int[]> list = new LinkedList<>();
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[0][]);

    }


    /**
     * 79. 单词搜索
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * 示例:
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     */
    public static boolean _0079_单词搜索(char[][] board, String word) {
        if (word.length() == 0) {
            return true;
        }

        boolean[][] used = new boolean[board.length][board[0].length];

        char[] arr = word.toCharArray();
        char c = arr[0];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == c) {
                    if (existCore(board, i, j, arr, used, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static boolean existCore(char[][] board, int i, int j, char[] arr, boolean[][] used, int end) {
        if (end == arr.length - 1) {
            return true;
        }
        used[i][j] = true;
        end++;
        if ((i > 0 && !used[i - 1][j] && arr[end] == board[i - 1][j] && existCore(board, i - 1, j, arr, used, end)) ||
                (i < board.length - 1 && !used[i + 1][j] && arr[end] == board[i + 1][j] && existCore(board, i + 1, j, arr, used, end)) ||
                (j > 0 && !used[i][j - 1] && arr[end] == board[i][j - 1] && existCore(board, i, j - 1, arr, used, end)) ||
                (j < board[0].length - 1 && !used[i][j + 1] && arr[end] == board[i][j + 1] && existCore(board, i, j + 1, arr, used, end))) {
            return true;
        }
        used[i][j] = false;
        return false;
    }


    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * 示例:
     *
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     *
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     */
    public static int _0300_最长上升子序列(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
        }
        int maxLen = 1;
        for (int len : dp) {
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }


    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int[] arr = new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> map.get(a)));
        int count = 0;
        for (int key : map.keySet()) {
            if (count == k) {
                if (map.get(key) > map.get(minHeap.peek())) {
                    minHeap.poll();
                    minHeap.offer(key);
                }
            } else {
                minHeap.offer(key);
            }
        }
        for (int i = 0; i < k; i++) {
            arr[i] = minHeap.poll();
        }
        return arr;
    }


    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     *
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     * 示例 1:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     *
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     */
    public static String _0006_Z字形变换(String s, int numRows) {
        if (s.length() == 0 || numRows == 1) {
            return s;
        }
        StringBuilder[] arr = new StringBuilder[numRows];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new StringBuilder();
        }
        int twoN = (numRows - 1) << 1;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (j < numRows) {
                arr[j].append(s.charAt(i));
                j++;
            } else if (j < twoN) {
                arr[twoN - j].append(s.charAt(i));
                j++;
            } else {
                j = 0;
                i--;
            }
        }
        for (int i = 1; i < numRows; i++) {
            arr[0].append(arr[i]);
        }
        return arr[0].toString();
    }


    /**
     * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
     *
     * 示例 1：
     *
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     * 示例 2：
     *
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     * 解释：1 不能在 2 之前弹出。
     */
    public static boolean _0946_验证栈序列(int[] pushed, int[] popped) {
        int[] stack = new int[pushed.length];
        int top = -1;
        int i = 0;
        for (int pushValue : pushed) {
            stack[++top] = pushValue;
            while (top >= 0 && stack[top] == popped[i]) {
                i++;
                top--;
            }
        }
        return top == -1;
    }


    /**
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     *
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     *
     * 说明：
     *
     * 给定 n 的范围是 [1, 9]。
     * 给定 k 的范围是[1,  n!]。
     * 示例 1:
     *
     * 输入: n = 3, k = 3
     * 输出: "213"
     * 示例 2:
     *
     * 输入: n = 4, k = 9
     * 输出: "2314"
     */
    public static String _0060_第k个排列(int n, int k) {
        k--;
        int num = 1;
        for (int i = 1; i <= n; i++) {
            num *= i;
        }
        boolean[] used = new boolean[n];
        String str = "";
        for (int i = 0; i < n; i++) {
            num = num / (n - i);
            int a = k / num;
            k = k % num;
            for (int j = 0; j < n; j++) {
                if (!used[j]) {
                    if (a == 0) {
                        str = str + (j + 1);
                        used[j] = true;
                        break;
                    } else {
                        a--;
                    }
                }
            }
        }
        return str;
    }


    /**
     * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
     *
     * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
     *
     * 以 10^9 + 7 为模，返回这些数字之和。
     *
     * 示例：
     *
     * 输入：[1,0,1,0,1,0,1]
     * 输出：22
     * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     */
    public static int _1022_从根到叶的二进制数之和(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumRootToLeafCore(root, 0);
    }
    private static int sumRootToLeafCore(TreeNode root, int sum) {
        sum = (sum << 1) + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        int sum1 = 0;
        if (root.left != null) {
            sum1 += sumRootToLeafCore(root.left, sum);
        }
        if (root.right != null) {
            sum1 += sumRootToLeafCore(root.right, sum);
        }
        return sum1;
    }


    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     *
     * 参考以下这颗二叉搜索树：
     *
     *      5
     *     / \
     *    2   6
     *   / \
     *  1   3
     * 示例 1：
     *
     * 输入: [1,6,3,2,5]
     * 输出: false
     * 示例 2：
     *
     * 输入: [1,3,2,6,5]
     * 输出: true
     */
    public boolean _面试题33_二叉搜索树的后序遍历序列(int[] postorder) {
        return verifyPostorderCore(postorder, 0, postorder.length - 1);
    }
    private boolean verifyPostorderCore(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int i = start;
        while (i < end && postorder[i] < postorder[end]) {
            i++;
        }
        for (int j = i + 1; j < end; j++) {
            if (postorder[j] < postorder[end]) {
                return false;
            }
        }
        return verifyPostorderCore(postorder, start, i - 1) && verifyPostorderCore(postorder, i, end - 1);
    }


    /**
     * 比较两个版本号 version1 和 version2。
     * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
     *
     * 你可以假设版本字符串非空，并且只包含数字和 . 字符。
     *
     *  . 字符不代表小数点，而是用于分隔数字序列。
     *
     * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
     *
     * 你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
     *  
     * 示例 1:
     *
     * 输入: version1 = "0.1", version2 = "1.1"
     * 输出: -1
     * 示例 2:
     *
     * 输入: version1 = "1.0.1", version2 = "1"
     * 输出: 1
     * 示例 3:
     *
     * 输入: version1 = "7.5.2.4", version2 = "7.5.3"
     * 输出: -1
     * 示例 4：
     *
     * 输入：version1 = "1.01", version2 = "1.001"
     * 输出：0
     * 解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
     * 示例 5：
     *
     * 输入：version1 = "1.0", version2 = "1.0.0"
     * 输出：0
     * 解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
     */
    public int _0165_比较版本号(String version1, String version2) {
        if (version1.length() == 0 && version2.length() == 0) {
            return 0;
        }
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        int i1 = 0;
        int i2 = 0;
        while (i1 < arr1.length && i2 < arr2.length) {
            int n1 = Integer.parseInt(arr1[i1]);
            int n2 = Integer.parseInt(arr2[i2]);
            if (n1 != n2) {
                return n1 > n2 ? 1 : -1;
            }
            i1++;
            i2++;
        }
        if (i1 == arr1.length) {
            while (i2 < arr2.length) {
                if (Integer.parseInt(arr2[i2]) != 0) {
                    return -1;
                }
                i2++;
            }
        } else {
            while (i1 < arr1.length) {
                if (Integer.parseInt(arr1[i1]) != 0) {
                    return 1;
                }
                i1++;
            }
        }
        return 0;
    }


    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     *
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     *
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * 示例 1：
     *
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * 示例 2：
     *
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * 示例 3：
     *
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     * 示例 4：
     *
     * 输入：s = "abc3[cd]xyz"
     * 输出："abccdcdcdxyz"
     */
    public static String _0394_字符串解码(String s) {
        if (s.length() == 0) {
            return "";
        }

        Stack<String> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();

        StringBuilder sb = new StringBuilder();

        int count = 0;
        int pre = 0;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                count = count * 10 + c - '0';
                i++;
            } else if (c == '[') {
                numStack.push(count);
                strStack.push("");
                count = 0;
                i++;
            } else if (c == ']') {
                String code = strStack.pop();
                String ccooddee = "";
                count = numStack.pop();
                while (count > 0) {
                    ccooddee += code;
                    count--;
                }
                if (numStack.isEmpty()) {
                    sb.append(ccooddee);
                } else {
                    strStack.push(strStack.pop() + ccooddee);
                }
                i++;
            } else {
                pre = i;
                while (i < s.length() && s.charAt(i) != ']' && s.charAt(i) > '9') {
                    i++;
                }
                if (numStack.isEmpty()) {
                    sb.append(s.substring(pre, i));
                } else {
                    strStack.push(strStack.pop() + s.substring(pre, i));
                }
                count = 0;
            }
        }
        return sb.toString();
    }


    /**
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     *
     * 例如，给定三角形：
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     */
    public static int _0120_三角形最小路径和(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> list = triangle.get(i);
            dp[i] += dp[i - 1] + list.get(list.size() - 1);
            for (int j = list.size() - 2; j > 0; j--) {
                dp[j] = list.get(j) + Math.min(dp[j], dp[j - 1]);
            }
            dp[0] += list.get(0);
        }
        int min = dp[0];
        for (int d : dp) {
            min = Math.min(d, min);
        }
        return min;
    }





    /**
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     *
     * 示例：
     *
     * 输入：3
     * 输出：
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * 解释：
     * 以上的输出对应以下 5 种不同结构的二叉搜索树：
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     */
    public List<TreeNode> _0095_不同的二叉搜索树_II(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateTrees(start, i - 1);
            List<TreeNode> right = generateTrees(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
            }
        }
        return list;
    }


    /**
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     *
     * 示例:
     *
     * 输入: 3
     * 输出: 5
     * 解释:
     * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     */
    public static int _0096_不同的二叉搜索树(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }



    public static int maxScoreSightseeingPair(int[] A) {

        if (A.length == 2) {
            return A[0] + A[1] - 1;
        }
        int left = 0;
        int right = A.length - 1;
        int max = 0;
        while (right > left + 1) {
            max = Math.max(A[left] + A[right] + left - right, max);
            if (A[left + 1] == A[right - 1]) {
                if (A[left] < A[right]) {
                    left++;
                } else {
                    right--;
                }
            } else if (A[left] - A[left + 1] < A[right] - A[right - 1]) {
                left++;
            } else {
                right--;
            }
        }
        return Math.max(A[left] + A[right] + left - right, max);
    }


    /**
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     *
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * 进阶:
     *
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     */
    public static int[] _0338_比特位计数(int num) {
        int[] arr = new int[num + 1];
        arr[0] = 0;
        int i = 1;
        int pre = 0;
        int n = 1;
        while (i <= num) {
            arr[i] = arr[pre] + 1;
            i++;
            pre++;
            if (pre == n) {
                pre = 0;
                n = n << 1;
            }
        }
        return arr;
    }


    /**
     * 给定一个非空二叉树，返回其最大路径和。
     *
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * 输出: 6
     * 示例 2:
     *
     * 输入: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    public static int _0124_二叉树中的最大路径和(TreeNode root) {
        return maxPathSumCore(root)[0];
    }
    private static int[] maxPathSumCore(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = maxPathSumCore(root.left);
        int[] right = maxPathSumCore(root.right);
        return new int[]{Math.max(root.val + left[1] + right[1],
                                  Math.max(root.left == null ? Integer.MIN_VALUE : left[0],
                                           root.right == null ? Integer.MIN_VALUE : right[0])) ,
                         Math.max(Math.max(left[1], right[1]) + root.val, 0)};
    }


    /**
     * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
     *
     * 注意：请不要在超过该数组长度的位置写入元素。
     *
     * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
     *
     * 示例 1：
     *
     * 输入：[1,0,2,3,0,4,5,0]
     * 输出：null
     * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
     * 示例 2：
     *
     * 输入：[1,2,3]
     * 输出：null
     * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
     */
    public static void _1089_复写零(int[] arr) {
        int len = 0;
        int i = 0;
        for (; i + len < arr.length; i++) {
            if (arr[i] == 0) {
                len++;
            }
        }
        i--;
        int j = arr.length - 1;
        if (arr[i] == 0 && i + len == arr.length) {
            arr[j--] = 0;
            i--;
        }
        while (j >= 0) {
            if (arr[i] == 0) {
                arr[j--] = 0;
                arr[j--] = 0;
            } else {
                arr[j--] = arr[i];
            }
            i--;
        }
    }


    /**
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     *
     * 示例 1:
     * 输入:
     *
     * "bbbab"
     * 输出:
     *
     * 4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:
     *
     * "cbbd"
     * 输出:
     *
     * 2
     * 一个可能的最长回文子序列为 "bb"。
     */
    public static int _0516_最长回文子序列(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        int len = 0;
        for (int j = 1; j < dp.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
                len = Math.max(dp[i][j], len);
            }
        }
        return len;
    }


    /**
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     *
     * 注意：n 的值小于15000。
     *
     * 示例1:
     *
     * 输入: [1, 2, 3, 4]
     *
     * 输出: False
     *
     * 解释: 序列中不存在132模式的子序列。
     * 示例 2:
     *
     * 输入: [3, 1, 4, 2]
     *
     * 输出: True
     *
     * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
     * 示例 3:
     *
     * 输入: [-1, 3, 2, 0]
     *
     * 输出: True
     *
     * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
     */
    public boolean _0456_132模式(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int[] min = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                if (stack.peek() > min[i]) {
                    return true;
                } else {
                    stack.pop();
                }
            }
            stack.push(nums[i]);
        }
        return false;
    }


    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    public static List<List<Integer>> _0078_子集(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, new int[nums.length], -1, 0, result);
        return result;
    }
    private static void subsets(int[] nums, int[] arr, int end, int len, List<List<Integer>> result) {
        if (end == nums.length) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(arr[i]);
        }
        result.add(list);
        end++;
        for (int i = end; i < nums.length; i++) {
            arr[len] = nums[i];
            subsets(nums, arr, i, len + 1, result);
        }
    }



    /**
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     *
     * 示例 1:
     *
     * 输入:
     * "tree"
     *
     * 输出:
     * "eert"
     *
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     * 示例 2:
     *
     * 输入:
     * "cccaaa"
     *
     * 输出:
     * "cccaaa"
     *
     * 解释:
     * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
     * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
     * 示例 3:
     *
     * 输入:
     * "Aabb"
     *
     * 输出:
     * "bbAa"
     *
     * 解释:
     * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
     * 注意'A'和'a'被认为是两种不同的字符。
     */
    public static String _0451_根据字符出现频率排序(String s) {
        int[] counts = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (counts[s.charAt(i)]++ == 0) {
                count++;
            }
        }

        int i = 0;
        Integer[] addrs = new Integer[count];
        for (int j = 0; j < counts.length; j++) {
            if (counts[j] > 0) {
                addrs[i++] = j;
            }
        }
        Arrays.sort(addrs, Comparator.comparingInt(a -> -counts[a]));

        char[] arr = new char[s.length()];
        i = 0;
        for (Integer addr : addrs) {
            for (int j = 0; j < counts[addr]; j++) {
                arr[i++] = (char)(addr + 0);
            }
        }
        return new String(arr);
    }


    /**
     * 给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。
     *
     * 注意：要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。
     *
     * 如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。
     *
     * 示例 1：
     *
     * 输入：croakOfFrogs = "croakcroak"
     * 输出：1
     * 解释：一只青蛙 “呱呱” 两次
     * 示例 2：
     *
     * 输入：croakOfFrogs = "crcoakroak"
     * 输出：2
     * 解释：最少需要两只青蛙，“呱呱” 声用黑体标注
     * 第一只青蛙 "crcoakroak"
     * 第二只青蛙 "crcoakroak"
     * 示例 3：
     *
     * 输入：croakOfFrogs = "croakcrook"
     * 输出：-1
     * 解释：给出的字符串不是 "croak" 的有效组合。
     * 示例 4：
     *
     * 输入：croakOfFrogs = "croakcroa"
     * 输出：-1
     */
    public static int _1419_数青蛙(String croakOfFrogs) {
        int[] addr = new int[128];
        addr['c'] = 1;
        addr['r'] = 2;
        addr['o'] = 3;
        addr['a'] = 4;
        addr['k'] = 5;
        int[] counts = new int[5];
        int num = 0;
        int maxNum = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char c = croakOfFrogs.charAt(i);
            if (addr[c] == 0) {
                return -1;
            } else {
                if (c == 'c') {
                    num++;
                    maxNum = Math.max(maxNum, num);
                    counts[addr[c]]++;
                } else {
                    if (counts[addr[c] - 1]-- == 0) {
                        return -1;
                    }
                    if (c == 'k') {
                        num--;
                    } else {
                        counts[addr[c]]++;
                    }
                }
            }
        }
        for (int c : counts) {
            if (c != 0) {
                return -1;
            }
        }
        return maxNum;
    }


    /**
     * 我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
     *
     * 请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
     *
     * 示例 1：
     *
     * 输出：low = 100, high = 300
     * 输出：[123,234]
     * 示例 2：
     *
     * 输出：low = 1000, high = 13000
     * 输出：[1234,2345,3456,4567,5678,6789,12345]
     *  
     * 提示：
     * 10 <= low <= high <= 10^9
     */
    public static List<Integer> _1291_顺次数(int low, int high) {
        List<Integer> list = new ArrayList<>();
        int add = 11;
        int n = 2;
        int start = 12;
        int num = 12;
        while (num <= high) {
            if (num >= low) {
                list.add(num);
            }
            if (num % 10 == 9) {
                n++;
                start = start * 10 + n;
                num = start;
                add = add * 10 + 1;
            } else {
                num = num + add;
            }
        }
        return list;

    }


    /**
     * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
     *
     * 返回这两个区间列表的交集。
     *
     * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
     *
     * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
     * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     */
    public int[][] _0986_区间列表的交集(int[][] A, int[][] B) {
        int AIndex = 0;
        int BIndex = 0;
        List<int[]> list = new ArrayList<>();
        while (AIndex < A.length && BIndex < B.length) {
            int start = Math.max(A[AIndex][0], B[BIndex][0]);
            int end = Math.min(A[AIndex][1], B[BIndex][1]);
            if (start <= end) {
                list.add(new int[]{start, end});
            }
            if (A[AIndex][1] > B[BIndex][1]) {
                BIndex++;
            } else {
                AIndex++;
            }
        }
        return list.toArray(new int[0][]);
    }


    /**
     * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
     * 注意：两个节点之间的路径长度由它们之间的边数表示。
     *
     * 示例 1:
     * 输入:
     *
     *               5
     *              / \
     *             4   5
     *            / \   \
     *           1   1   5
     * 输出:
     * 2
     *
     * 示例 2:
     * 输入:
     *
     *               1
     *              / \
     *             4   5
     *            / \   \
     *           4   4   5
     * 输出:
     * 2
     */
    public int _0687_最长同值路径(TreeNode root) {
        return longestUnivaluePathCore(root)[0];
    }
    private int[] longestUnivaluePathCore(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = longestUnivaluePathCore(root.left);
        int[] right = longestUnivaluePathCore(root.right);
        int leftLen = (root.left != null && root.val == root.left.val) ? left[1] + 1 : 0;
        int rightLen = (root.right != null && root.val == root.right.val) ? right[1] + 1 : 0;
        return new int[] {Math.max(leftLen + rightLen, Math.max(left[0], right[0])), Math.max(leftLen, rightLen)};
    }


    /**
     * 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
     *
     * 选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
     *
     * 比如，有 A = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 A 为["bef", "vyz"]。
     *
     * 假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的元素是按 字典序（A[0] <= A[1] <= A[2] ... <= A[A.length - 1]）排列的，然后请你返回 D.length 的最小可能值。
     *
     * 示例 1：
     *
     * 输入：["ca","bb","ac"]
     * 输出：1
     * 解释：
     * 删除第一列后，A = ["a", "b", "c"]。
     * 现在 A 中元素是按字典排列的 (即，A[0] <= A[1] <= A[2])。
     * 我们至少需要进行 1 次删除，因为最初 A 不是按字典序排列的，所以答案是 1。
     * 示例 2：
     *
     * 输入：["xc","yb","za"]
     * 输出：0
     * 解释：
     * A 的列已经是按字典序排列了，所以我们不需要删除任何东西。
     * 注意 A 的行不需要按字典序排列。
     * 也就是说，A[0][0] <= A[0][1] <= ... 不一定成立。
     * 示例 3：
     *
     * 输入：["zyx","wvu","tsr"]
     * 输出：3
     * 解释：
     * 我们必须删掉每一列。
     */
    public int _0955_删列造序_II(String[] A) {
        int count = 0;
        boolean[] arr = new boolean[A[0].length()];
        int[] indexs = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            indexs[i] = 0;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < A.length; j++) {
                String s = A[j];
                String pre = A[j - 1];
                for (int k = indexs[j]; k <= i; k++) {
                    if (!arr[k]) {
                        if (s.charAt(k) > pre.charAt(k)) {
                            indexs[j] = k;
                            break;
                        } else if (s.charAt(k) < pre.charAt(k)) {
                            arr[i] = true;
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }


    /**
     * 给你一个整数数组 arr 和一个整数 k 。现需要从数组中恰好移除 k 个元素，请找出移除后数组中不同整数的最少数目。
     *
     * 示例 1：
     * 输入：arr = [5,5,4], k = 1
     * 输出：1
     * 解释：移除 1 个 4 ，数组中只剩下 5 一种整数。
     * 示例 2：
     *
     * 输入：arr = [4,3,1,1,3,3,2], k = 3
     * 输出：2
     * 解释：先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1 和 3 两种整数。
     */
    public static int _1481_不同整数的最少数目(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int[] counts = new int[map.size()];
        int i = 0;
        for (Integer count : map.values()) {
            counts[i++] = count;
        }
        Arrays.sort(counts);
        int count = counts.length;
        i = 0;
        for (; i < counts.length && counts[i] <= k; i++) {
            k -= counts[i];
            count--;
        }
        return count;
    }


    /**
     * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
     * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
     * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
     * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
     * 示例 1:
     * 输入:
     * 二叉树如下所示:
     *        4
     *      /   \
     *     2     6
     *    / \   /
     *   3   1 5
     *
     * v = 1
     * d = 2
     * 输出:
     *        4
     *       / \
     *      1   1
     *     /     \
     *    2       6
     *   / \     /
     *  3   1   5
     *
     * 示例 2:
     * 输入:
     * 二叉树如下所示:
     *       4
     *      /
     *     2
     *    / \
     *   3   1
     *
     * v = 1
     * d = 3
     * 输出:
     *       4
     *      /
     *     2
     *    / \
     *   1   1
     *  /     \
     * 3       1
     * 注意:
     * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
     * 输入的二叉树至少有一个节点。
     */
    public static TreeNode _0623_在二叉树中增加一行(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode node = null;
        queue.offer(root);
        int curCount = 1;
        int nextCount = 0;
        int deep = 1;
        while (curCount != 0) {
            if (++deep == d) {
                break;
            }
            while (curCount-- > 0) {
                node = queue.poll();
                if (node.left != null) {
                    nextCount++;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    nextCount++;
                    queue.offer(node.right);
                }
            }
            curCount = nextCount;
            nextCount = 0;
        }
        while (!queue.isEmpty()) {
            node = queue.poll();
            TreeNode left = new TreeNode(v);
            TreeNode right = new TreeNode(v);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
        }
        return root;
    }


    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     * 示例: 
     * 你可以将以下二叉树：
     *
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     * 序列化为 "[1,2,3,null,null,4,5]"
     */
    public static String _0297_二叉树的序列化与反序列化(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode node = null;
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (true) {
            node = queue.poll();
            if (node != null) {
                sb.append(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                sb.append("null");
                if (queue.isEmpty()) {
                    sb.append("]");
                    break;
                }
            }
            sb.append(", ");
        }
        return sb.toString();
    }
    public static TreeNode deserialize(String data) {
        if (data.equals("[]") || data.equals("[null]")) {
            return null;
        }
        String[] str = data.substring(1, data.length() - 1).split(", ");
        Integer[] arr = new Integer[str.length];
        for (int i = 0; i < arr.length; i++) {
            if (str[i].equals("null")) {
                arr[i] = null;
            } else {
                arr[i] = Integer.parseInt(str[i]);
            }
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);
        TreeNode node = null;
        int i = 1;
        while (i < arr.length) {
            node = queue.poll();
            if (arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                queue.offer(node.left);
            }
            i++;
            if (arr[i] != null) {
                node.right = new TreeNode(arr[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

    /**
     * 我们从二叉树的根节点 root 开始进行深度优先搜索。
     * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
     * （如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
     * 如果节点只有一个子节点，那么保证该子节点为左子节点。
     * 给出遍历输出 S，还原树并返回其根节点 root。
     */
    public TreeNode _1028_从先序遍历还原二叉树(String S) {
        if (S.length() == 0) {
            return null;
        }
        int i = 0;
        int num = 0;
        while (true) {
            char c = i == S.length() ? '-' : S.charAt(i);
            if (c != '-') {
                num = num * 10 + c - '0';
                i++;
            } else {
                break;
            }
        }
        TreeNode root = new TreeNode(num);
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = null;
        stack.push(root);
        int deep = 0;
        while (i < S.length()) {
            char c = S.charAt(i);
            if (c == '-') {
                deep++;
                i++;
            } else {
                num = 0;
                while (true) {
                    c = i == S.length() ? '-' : S.charAt(i);
                    if (c != '-') {
                        num = num * 10 + c - '0';
                        i++;
                    } else {
                        if (deep == stack.size()) {
                            node = stack.peek();
                            node.left = new TreeNode(num);
                            stack.push(node.left);
                        } else {
                            while (deep < stack.size()) {
                                stack.pop();
                            }
                            node = stack.peek();
                            node.right = new TreeNode(num);
                            stack.push(node.right);
                        }
                        deep = 0;
                        break;
                    }
                }
            }
        }
        return root;
    }


    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2:
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     */
    public List<List<Integer>> _0039_组合总和(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int[] arr = new int[target + 1];
        combinationSum(candidates, target, 0, arr, -1, 0, result);
        return result;
    }

    private void combinationSum(int[] candidates, int target, int start, int[] arr, int end, int sum, List<List<Integer>> result) {
        if (sum == target) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i <= end; i++) {
                list.add(arr[i]);
            }
            result.add(list);
            return;
        }
        if (start == candidates.length) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int newSum = sum;
            int newEnd = end;
            int j = 0;
            while (newSum <= target) {
                newEnd++;
                arr[newEnd] = candidates[i];
                newSum += candidates[i];
                combinationSum(candidates, target, i + 1, arr, newEnd, newSum, result);
            }
        }
    }


    /**
     * 给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。
     * 如果可以，请返回 True；否则，返回 False。
     * 示例 1：
     * 输入：nums = [1,2,3,3,4,4,5,6], k = 4
     * 输出：true
     * 解释：数组可以分成 [1,2,3,4] 和 [3,4,5,6]。
     * 示例 2：
     * 输入：nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
     * 输出：true
     * 解释：数组可以分成 [1,2,3] , [2,3,4] , [3,4,5] 和 [9,10,11]。
     * 示例 3：
     * 输入：nums = [3,3,2,2,1,1], k = 3
     * 输出：true
     * 示例 4：
     * 输入：nums = [1,2,3,4], k = 3
     * 输出：false
     * 解释：数组不能分成几个大小为 3 的子数组。
     */
    public static boolean _1296_划分数组为连续数字的集合(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int n : nums) {
            Queue<Integer> queue = map.get(n - 1);
            if (queue == null) {
                queue = map.get(n);
                if (queue == null) {
                    queue = new LinkedList<>();
                    map.put(n, queue);
                }
                queue.offer(1);
            } else {
                int c = queue.poll() + 1;
                if (queue.isEmpty()) {
                    map.remove(n - 1);
                }
                if (c != k) {
                    queue = map.get(n);
                    if (queue == null) {
                        queue = new LinkedList<>();
                    }
                    queue.offer(c);
                    map.put(n, queue);
                }
            }
        }
        return map.isEmpty();
    }


    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     * 示例 1:
     * 输入:
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出:
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2:
     *
     * 输入:
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出:
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     */
    public static void _0073_矩阵置零(int[][] matrix) {
        int h = matrix.length;
        int w = matrix[0].length;
        int X = -1;
        int Y = -1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] == 0) {
                    if (X == -1) {
                        X = i;
                        Y = j;
                        for (int k = 0; k < h; k++) {
                            if (matrix[k][Y] == 0) {
                                matrix[k][Y] = k;
                            } else {
                                matrix[k][Y] = -1;
                            }
                        }
                        for (int k = 0; k < w; k++) {
                            if (matrix[X][k] == 0) {
                                matrix[X][k] = k;
                            } else {
                                matrix[X][k] = -1;
                            }
                        }
                    } else {
                        matrix[i][Y] = i;
                        matrix[X][j] = j;
                    }
                }
            }
        }
        if (X == -1) {
            return;
        }
        for (int i = 0; i < h; i++) {
            if (i != X && matrix[i][Y] != -1) {
                for (int j = 0; j < w; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < w; j++) {
            if (j != Y && matrix[X][j] != -1) {
                for (int i = 0; i < h; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int k = 0; k < h; k++) {
            matrix[k][Y] = 0;
        }
        for (int k = 0; k < w; k++) {
            matrix[X][k] = 0;
        }
    }


    public static boolean _0010_正则表达式匹配(String s, String p) {
        return isMatch(s.toCharArray(), p.toCharArray(), 0, 0);
    }
    private static boolean isMatch(char[] sArr, char[] pArr, int sStart, int pStart) {
        if (pStart == pArr.length) {
            return sArr.length == sStart;
        }

        if (pStart + 1 < pArr.length && pArr[pStart + 1] == '*') {
            if (sStart < sArr.length && (sArr[sStart] == pArr[pStart] || pArr[pStart] == '.')) {
                return isMatch(sArr, pArr, sStart, pStart + 2) ||
                        isMatch(sArr, pArr, sStart + 1, pStart) ||
                        isMatch(sArr, pArr, sStart + 1, pStart + 2);
            }
            return isMatch(sArr, pArr, sStart, pStart + 2);
        }
        if (sStart < sArr.length && sArr[sStart] == pArr[pStart] || pArr[pStart] == '.') {
            return isMatch(sArr, pArr, sStart + 1, pStart + 1);
        }
        return false;
    }


    public static boolean _0010_正则表达式匹配_2(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        boolean[][] dp = new boolean[sArr.length + 1][pArr.length + 1];
        dp[0][0] = true;

        for (int j = 1; j <= pArr.length; j++) {
            if (pArr[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int j = 1; j <= pArr.length; j++) {
            for (int i = 1; i <= sArr.length; i++) {
                if (pArr[j - 1] == '*') {
                    if (j - 2 == 0) {
                        if (i - 1 == 0) {
                            dp[i][j] = dp[i][j - 1];
                        } else {
                            dp[i][j] = dp[i - 1][j] && (pArr[j - 2] == '.' || pArr[j - 2] == sArr[i - 1]);
                        }
                    } else {
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || (dp[i - 1][j] && (pArr[j - 2] == '.' || pArr[j - 2] == sArr[i - 1]));
                    }
                } else if (pArr[j - 1] == '.' || pArr[j - 1] == sArr[i - 1]){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }

            }
        }
        return dp[sArr.length][pArr.length];
    }


    /**
     * 给定一个数组 A，将其划分为两个不相交（没有公共元素）的连续子数组 left 和 right， 使得：
     * left 中的每个元素都小于或等于 right 中的每个元素。
     * left 和 right 都是非空的。
     * left 要尽可能小。
     * 在完成这样的分组后返回 left 的长度。可以保证存在这样的划分方法。
     *
     * 示例 1：
     * 输入：[5,0,3,8,6]
     * 输出：3
     * 解释：left = [5,0,3]，right = [8,6]
     * 示例 2：
     * 输入：[1,1,1,0,6,12]
     * 输出：4
     * 解释：left = [1,1,1,0]，right = [6,12]
     */
    public static int _0915_分割数组(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int pre = 0;
        int leftMax = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] < leftMax) {
                pre = i;
                leftMax = max;
            } else {
                max = Math.max(max, A[i]);
            }
        }
        return pre + 1;
    }





    public static void main(String[] args) {


    }



}
