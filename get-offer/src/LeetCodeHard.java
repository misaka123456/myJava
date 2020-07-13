import model.TreeNode;

import java.util.*;

/**
 * @author xiakai
 * @create 2020-06-27 10:59
 */
@SuppressWarnings("all")
public class LeetCodeHard {

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static int _0041_缺失的第一个正数(int[] nums) {
        int min = 1;
        for (int n : nums) {
            if (n > 0) {
                min = Math.min(min, n);
            }
        }
        if (min != 1) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] - 1 < nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int n : nums) {
            if (n != min) {
                return min;
            } else {
                min++;
            }
        }
        return min;
    }


    public int _0042_接雨水(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }
        int sum = 0;
        Stack<Integer> hStack = new Stack<>();
        hStack.push(0);
        for (int i = 1; i < height.length; i++) {
            if (hStack.isEmpty() || height[hStack.peek()] > height[i]) {
                hStack.push(i);
            } else {
                int base = height[hStack.pop()];
                while (!hStack.isEmpty()) {
                    int pre = hStack.peek();
                    sum += (i - pre - 1) * (Math.min(height[pre], height[i]) - base);
                    base = height[pre];
                    if (height[pre] <= height[i]) {
                        hStack.pop();
                    } else {
                        break;
                    }
                }

                hStack.push(i);
            }

        }
        return sum;
    }


    /**
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * 示例 1:
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     */
    public static int _0032_最长有效括号(String s) {
        if (s.length() <= 1) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int[] dp = new int[arr.length];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == ')') {
                if (arr[i - 1] == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && arr[i - dp[i - 1] - 1] == '(') {
                    dp[i] = (i - dp[i - 1] > 1 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }


    public static boolean _0044_通配符匹配(String s, String p) {
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        boolean[][] dp = new boolean[sArr.length + 1][pArr.length + 1];
        dp[0][0] = true;
        for (int j = 0; j < pArr.length; j++) {
            for (int i = -1; i < sArr.length; i++) {
                if (i == -1 || pArr[j] == '*') {
                    if (pArr[j] == '*') {
                        for (int k = 0; k <= i + 1; k++) {
                            if (dp[k][j]) {
                                dp[k][j + 1] = true;
                                for (k = k + 1; k <= sArr.length; k++) {
                                    dp[k][j + 1] = true;
                                }
                                break;
                            }
                        }
                    }
                } else if (sArr[i] == pArr[j] || pArr[j] == '?') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
            }
        }
        return dp[sArr.length][pArr.length];
    }


    /**
     * 给你一个由小写字母组成的字符串 s，和一个整数 k。
     * 请你按下面的要求分割字符串：
     * 首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
     * 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
     * 请返回以这种方式分割字符串所需修改的最少字符数。
     * 示例 1：
     * 输入：s = "abc", k = 2
     * 输出：1
     * 解释：你可以把字符串分割成 "ab" 和 "c"，并修改 "ab" 中的 1 个字符，将它变成回文串。
     * 示例 2：
     * 输入：s = "aabbc", k = 3
     * 输出：0
     * 解释：你可以把字符串分割成 "aa"、"bb" 和 "c"，它们都是回文串。
     * 示例 3：
     * 输入：s = "leetcode", k = 8
     * 输出：0
     */
    public static int _1278_分割回文串_III(String s, int k) {
        if (s.length() == k) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int[][] dp1 = new int[arr.length][arr.length];
        dp1[0][0] = 0;
        for (int j = 1; j < arr.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp1[i][j] = (arr[i] == arr[j] ? 0 : 1) + dp1[i + 1][j - 1];
            }
        }

        int[][] dp2 = new int[arr.length][k];
        for (int i = 0; i < arr.length; i++) {
            dp2[i][0] = dp1[0][i];
        }
        for (int j = 1; j < k; j++) {
            for (int i = j; i < arr.length; i++) {
                dp2[i][j] = arr.length;
                for (int q = j - 1; q < i; q++) {
                    dp2[i][j] = Math.min(dp2[i][j], dp2[q][j - 1] + dp1[q + 1][i]);
                }
            }
        }
        return dp2[arr.length - 1][k - 1];
    }


    /**
     * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
     * 如果没有和至少为 K 的非空子数组，返回 -1 。
     *  
     * 示例 1：
     * 输入：A = [1], K = 1
     * 输出：1
     * 示例 2：
     * 输入：A = [1,2], K = 4
     * 输出：-1
     * 示例 3：
     * 输入：A = [2,-1,2], K = 3
     * 输出：3
     */
    public static int _0862_和至少为_K_的最短子数组(int[] A, int K) {
        int[] sums = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            sums[i + 1] = sums[i] + A[i];
        }

        Deque<Integer> deque = new LinkedList<>();
        int minLen = sums.length;

        for (int i = 0; i <= A.length; i++) {
            while (!deque.isEmpty() && sums[deque.peekLast()] >= sums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (!deque.isEmpty() && sums[i] - sums[deque.peekFirst()] >= K) {
                minLen = Math.min(minLen, i - deque.pollFirst());
            }
        }
        return minLen == sums.length ? -1 : minLen;
    }


    /**
     * 不是最优
     */
    public static int _0887_鸡蛋掉落(int K, int N) {
        int[][] dp = new int[N + 1][K];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < K; j++) {
                dp[i][j] = N;
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[k][j - 1], dp[i - k - 1][j]));
                }
            }
        }
        return dp[N][K - 1];
    }


    /**
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * 示例 1：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     */
    public int _0072_编辑距离(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                dp[i + 1][j + 1] = Math.min(dp[i][j] + (word1.charAt(i) == word2.charAt(j) ? 0 : 1), Math.min(dp[i][j + 1], dp[i + 1][j]) + 1);
            }
        }
        return dp[word1.length()][word2.length()];
    }


    /**
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     * 示例：
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
     * 给定一个非空二叉树，返回其最大路径和。
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     * 示例 1:
     * 输入: [1,2,3]
     *        1
     *       / \
     *      2   3
     *
     * 输出: 6
     * 示例 2:
     *
     * 输入: [-10,9,20,null,null,15,7]
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 输出: 42
     */
    public int _0124_二叉树中的最大路径和(TreeNode root) {
        return maxPathSumCore(root)[0];
    }
    private int[] maxPathSumCore(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = maxPathSumCore(root.left);
        int[] right = maxPathSumCore(root.right);
        return new int[]{Math.max(root.val + left[1] + right[1],
                Math.max(root.left == null ? Integer.MIN_VALUE : left[0],
                        root.right == null ? Integer.MIN_VALUE : right[0])),
                Math.max(Math.max(left[1], right[1]) + root.val, 0)};
    }


    /**
     * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
     * 如果数组元素个数小于 2，则返回 0。
     *
     * 示例 1:
     * 输入: [3,6,9,1]
     * 输出: 3
     * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
     *
     * 示例 2:
     * 输入: [10]
     * 输出: 0
     * 解释: 数组元素个数小于 2，因此返回 0。
     */
    public static int _0164_最大间距(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }
        if (max == min) {
            return 0;
        }
        int len = nums.length + 1;
        int gap = max - min;
        int[][] gapArr = new int[len][];
        int index = 0;
        for (int n : nums) {
            if (n == max) {
                index = len - 1;
            } else {
                index = (int) ((long)(n - min) * (long)len / gap);
            }
            if (gapArr[index] == null) {
                gapArr[index] = new int[]{n, n};
            } else {
                gapArr[index][0] = Math.min(gapArr[index][0], n);
                gapArr[index][1] = Math.max(gapArr[index][1], n);
            }
        }
        int magGap = 0;
        int preMax = gapArr[0][1];
        for (int[] arr : gapArr) {
            if (arr != null) {
                magGap = Math.max(magGap, arr[0] - preMax);
                preMax = arr[1];
            }
        }
        return magGap;
    }


    /**
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
     *
     * 示例 1:
     * 输入: "1 + 1"
     * 输出: 2
     * 示例 2:
     * 输入: " 2-1 + 2 "
     * 输出: 3
     * 示例 3:
     * 输入: "(1+(4+5+2)-3)+(6+8)"
     * 输出: 23
     */
    public static int _0224_基本计算器(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Character> calStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        int num = 0;
        int i = 0;
        char signal = '+';
        char c = ' ';
        while (i < s.length()) {
            c = s.charAt(i++);
            if (c == ' ') {
                continue;
            }
            if (c == '-' || c == '+') {
                signal = c;
            } else if (c == ')') {
                num = 0;
                while ((c = calStack.pop()) != '(') {
                    num += (c == '+' ? 1 : -1) * numStack.pop();
                }
                numStack.push(num);
            } else {
                calStack.push(signal);
                signal = '+';
                if (c == '(') {
                    calStack.push('(');
                } else {
                    num = c - '0';
                    while (i < s.length()) {
                        c = s.charAt(i);
                        if (c == ' ') {
                            i++;
                            continue;
                        }
                        if (c >= '0' && c <= '9') {
                            num = num * 10 + c - '0';
                            i++;
                        } else {
                            break;
                        }
                    }
                    numStack.push(num);
                }
            }
        }
        num = 0;
        while (!calStack.isEmpty()) {
            num += (calStack.pop() == '+' ? 1 : -1) * numStack.pop();
        }
        return num;
    }



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



    public List<List<Integer>> _0218_天际线问题(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings.length == 0) {
            return res;
        }

        int[][] heights = new int[buildings.length << 1][2];
        int i = 0;
        for (int[] building : buildings) {
            heights[i][0] = building[0];
            heights[i][1] = building[2];
            i++;
            heights[i][0] = building[1];
            heights[i][1] = -building[2];
            i++;
        }

        Arrays.sort(heights, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int maxHeight = 0;
        for (int[] height : heights) {
            if (height[1] > 0) {
                map.put(height[1], map.getOrDefault(height[1], 0) + 1);
            } else {
                int c = map.get(-height[1]) - 1;
                if (c == 0) {
                    map.remove(-height[1]);
                } else {
                    map.put(-height[1], c);
                }
            }
            if ((map.isEmpty() && maxHeight != 0) || map.lastKey() != maxHeight) {
                maxHeight = map.isEmpty() ? 0 : map.lastKey();
                res.add(Arrays.asList(height[0], maxHeight));
            }
        }
        return res;
    }




    public int _0174_地下城游戏(int[][] dungeon) {

        int h = dungeon.length;
        int w = dungeon[0].length;
        int[][] dp = new int[h][w];

        dp[h - 1][w - 1] = Math.max(1, 1 - dungeon[h - 1][w - 1]);

        for (int i = h - 2; i >= 0; i--) {
            dp[i][w - 1] = Math.max(dp[i + 1][w - 1], 1) - dungeon[i][w - 1];
        }
        for (int j = w - 2; j >= 0; j--) {
            dp[h - 1][j] = Math.max(dp[h - 1][j + 1], 1) - dungeon[h - 1][j];
        }
        for (int i = h - 2; i >= 0; i--) {
            for (int j = w - 2; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1])) - dungeon[i][j];
            }
        }
        return Math.max(dp[0][0], 1);
    }

    public static void main(String[] args) {





    }

}
