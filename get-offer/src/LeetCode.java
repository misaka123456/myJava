import model.ListNode;
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
     * 76. 最小覆盖子串
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
     */
    public static String minWindow(String s, String t) {
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
     * 518. 零钱兑换 II
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     */
    public int change(int amount, int[] coins) {
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
     * 846. 一手顺子
     * 爱丽丝有一手（hand）由整数数组给定的牌。 
     * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
     * 如果她可以完成分组就返回 true，否则返回 false。
     */
    public static boolean isNStraightHand(int[] hand, int W) {
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
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     */
    public static int[] searchRange(int[] nums, int target) {
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


    /**
     * 1128. 等价多米诺骨牌对的数量
     */
    public int numEquivDominoPairs(int[][] dominoes) {
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


    /**
     * 388. 文件的最长绝对路径
     * @param input
     * @return
     */
    public static int lengthLongestPath(String input) {
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


    /**
     * 面试题 08.02. 迷路的机器人
     */
    public static List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {

        List<List<Integer>> route = new ArrayList<>();
        int h = obstacleGrid.length - 1;
        int w = obstacleGrid[0].length - 1;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[h][w] == 1) {
            return route;
        }
        boolean[][] dp = new boolean[h + 1][w + 1];
        dp[0][0] = true;
        dp[h][w] = true;
        int x = 0;
        int y = 0;
        while (x < h || y < w) {
            dp[x][y] = true;
            if (x < h && obstacleGrid[x + 1][y] == 0) {
                x++;
                continue;
            }
            if (y < w && obstacleGrid[x][y + 1] == 0) {
                y++;
                continue;
            }
            if (x == 0 && y == 0) {
                return route;
            }
            dp[x][y] = false;
            obstacleGrid[x][y] = 1;
            if (x > 0 && dp[x - 1][y]) {
                x--;
            } else {
                y--;
            }
        }
        x = 0;
        y = 0;
        while (x < h || y < w) {
            route.add(Arrays.asList(x, y));
            if (x < h && dp[x + 1][y]) {
                x++;
            } else {
                y++;
            }
        }
        route.add(Arrays.asList(x, y));
        return route;
    }


    /**
     * 47. 全排列 II
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {

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
     * 1079. 活字印刷
     */
    static int count = 0;
    public static int numTilePossibilities(String tiles) {
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


    /**
     * 1300. 转变数组后最接近目标值的数组和
     */
    public static int findBestValue(int[] arr, int target) {
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


    public static ListNode removeZeroSumSublists(ListNode head) {
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


    /**
     * 260. 只出现一次的数字 III
     */
    public static int[] singleNumber(int[] nums) {
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


    public static int numSquares(int n) {
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



    public static String smallestSubsequence(String text) {
        if (text.length() <= 1) {
            return text;
        }
        int[] countArr = new int[26];
        char[] chars = text.toCharArray();
        for (char c : chars) {
            countArr[c - 'a']++;
        }

        int[] preArr = new int[26];
        int pre = -1;
        for (int i = 0; i < 26; i++) {
            if (countArr[i] != 0) {
                preArr[i] = pre;
                pre = i;
            }
        }

        int[] nextArr = new int[26];
        int next = 26;
        for (int i = 25; i >= 0; i--) {
            if (countArr[i] != 0) {
                nextArr[i] = next;
                next = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            int n = c - 'a';
            if (countArr[n] == 1) {
                sb.append(c);
                countArr[n] = 0;
                if (nextArr[n] == 26 && preArr[n] == -1) {
                    break;
                }
                if (nextArr[n] == 26) {
                    nextArr[preArr[n]] = 26;
                } else if (preArr[n] == -1) {
                    preArr[nextArr[n]] = -1;
                } else {
                    nextArr[preArr[n]] = nextArr[n];
                    preArr[nextArr[n]] = preArr[n];
                }
            } else if (countArr[n] > 1) {
                if (preArr[n] == -1) {
                    sb.append(c);
                    if (nextArr[n] != 26) {
                        preArr[nextArr[n]] = -1;
                    }
                    countArr[n] = 0;
                } else {

                    countArr[n]--;
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args)  {
        System.out.println(smallestSubsequence("leetcode"));


    }



}
