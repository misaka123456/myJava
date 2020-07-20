import model.ListNode;
import model.TreeNode;
import myTools.MyArrayTools;
import myTools.MyArrayTreeTools;

import java.util.*;

/**
 * @author xiakai
 * @create 2020-06-29 11:50
 */
@SuppressWarnings("all")
public class LeetCodeMedium {


    /**
     * 给你一个数组 favoriteCompanies ，其中 favoriteCompanies[i] 是第 i 名用户收藏的公司清单（下标从 0 开始）。
     * 请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标。下标需要按升序排列。
     *
     * 示例 1：
     * 输入：favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
     * 输出：[0,1,4]
     * 解释：
     * favoriteCompanies[2]=["google","facebook"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集。
     * favoriteCompanies[3]=["google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 和 favoriteCompanies[1]=["google","microsoft"] 的子集。
     * 其余的收藏清单均不是其他任何人收藏的公司清单的子集，因此，答案为 [0,1,4] 。
     *
     * 示例 2：
     * 输入：favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
     * 输出：[0,1]
     * 解释：favoriteCompanies[2]=["facebook","google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集，因此，答案为 [0,1] 。
     *
     * 示例 3：
     * 输入：favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
     * 输出：[0,1,2,3]
     */
    public List<Integer> _1452_收藏清单(List<List<String>> favoriteCompanies) {
        List<Set<String>> setList = new ArrayList<>();
        for (List<String> favoriteCompany : favoriteCompanies) {
            Set<String> set = new HashSet<>(favoriteCompany);
            setList.add(set);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            boolean flag = true;
            for (int j = 0; j < setList.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (setList.get(j).containsAll(favoriteCompanies.get(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.add(i);
            }
        }
        return res;
    }


    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     *
     * 示例1:
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     *  
     * 示例2:
     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False
     */
    public static boolean _0567_字符串的排列(String s1, String s2) {

        int[] counts = new int[26];
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if ((counts[s1.charAt(i) - 'a']++) == 0) {
                count++;
            }
        }
        int pre = 0;
        int i = 0;
        int[] dp = new int[26];
        while (i < s2.length()) {
            int c = s2.charAt(i) - 'a';
            dp[c]++;
            if (counts[c] >= dp[c]) {
                if (dp[c] == counts[c]) {
                    count--;
                    if (count == 0) {
                        return true;
                    }
                }
            } else {
                while (pre <= i) {
                    c = s2.charAt(pre++) - 'a';
                    if (dp[c] == counts[c]) {
                        count++;
                    }
                    dp[c]--;
                    if (dp[c] == counts[c]) {
                        break;
                    }
                }
            }
            i++;
        }
        return false;

    }

    /**
     * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
     * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
     *
     * 例子:
     * 输入: N = 1, K = 1
     * 输出: 0
     *
     * 输入: N = 2, K = 1
     * 输出: 0
     *
     * 输入: N = 2, K = 2
     * 输出: 1
     *
     * 输入: N = 4, K = 5
     * 输出: 1
     *
     * 解释:
     * 第一行: 0
     * 第二行: 01
     * 第三行: 0110
     * 第四行: 01101001
     */
    public static int _0779_第K个语法符号(int N, int K) {
        // 自己想出来的，不是抄的
        int n = 1;
        int k = K;
        while (k != 0) {
            n = k & (-k);
            k = k ^ n;
        }
        int num = 0;
        while (K != 1) {
            if (n >= K) {
                n = n >> 1;
            } else {
                K = K - n;
                num++;
            }
        }
        return num % 2;
    }


    /**
     * 我们有两个长度相等且不为空的整型数组 A 和 B 。
     * 我们可以交换 A[i] 和 B[i] 的元素。注意这两个元素在各自的序列中应该处于相同的位置。
     * 在交换过一些元素之后，数组 A 和 B 都应该是严格递增的（数组严格递增的条件仅为A[0] < A[1] < A[2] < ... < A[A.length - 1]）。
     * 给定数组 A 和 B ，请返回使得两个数组均保持严格递增状态的最小交换次数。假设给定的输入总是有效的。
     *
     * 示例:
     * 输入: A = [1,3,5,4], B = [1,2,3,7]
     * 输出: 1
     * 解释:
     * 交换 A[3] 和 B[3] 后，两个数组如下:
     * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
     * 两个数组均为严格递增的。
     */
    public static int _0801_使序列递增的最小交换次数(int[] A, int[] B) {
        int a = 0;
        int b = 1;
        int a1 = 0;
        int b1 = 0;
        int a2 = 0;
        int b2 = 0;
        for (int i = 1; i < A.length; i++) {
            a1 = A.length;
            a2 = A.length;
            b1 = B.length;
            b2 = B.length;
            if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                a1 = a;
                b1 = b + 1;
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                a2 = b;
                b2 = a + 1;
            }
            a = Math.min(a1, a2);
            b = Math.min(b1, b2);
        }
        return Math.min(a, b);
    }


    public static int _0008_字符串转换整数 (String str) {
        long num = 0l;
        int signal = 1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c != '-' && (c < '0' || c > '9')) {
                return 0;
            }
            if (c == '-') {
                signal = -1;
                i++;
            } else if (c == '+') {
                i++;
            }
            while (i < str.length()) {
                c = str.charAt(i);
                if (c >= '0' && c <= '9') {
                    num = num * 10 + c - '0';
                    if (signal * num < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    }
                    if (signal * num > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                    i++;
                } else {
                    break;
                }
            }
            return (int) (signal * num);
        }
        return 0;
    }


    public int _1438_绝对差不超过限制的最长连续子数组(int[] nums, int limit) {
        if (nums.length == 0) {
            return 0;
        }

        Deque<Integer> maxQue = new LinkedList<>();
        Deque<Integer> minQue = new LinkedList<>();
        maxQue.offerLast(nums[0]);
        minQue.offerLast(nums[0]);

        int maxLen = 1;
        int pre = 0;
        for (int i = 1; i < nums.length; i++) {
            while (!maxQue.isEmpty() && (Math.abs(nums[i] - maxQue.peekFirst()) > limit || Math.abs(nums[i] - minQue.peekFirst()) > limit)) {
                if (nums[pre] == maxQue.peekFirst()) {
                    maxQue.pollFirst();
                }
                if (nums[pre] == minQue.peekFirst()) {
                    minQue.pollFirst();
                }
                pre++;
            }
            maxLen = Math.max(i - pre + 1, maxLen);
            while (!maxQue.isEmpty() && maxQue.peekLast() < nums[i]) {
                maxQue.pollLast();
            }
            maxQue.offerLast(nums[i]);
            while (!minQue.isEmpty() && minQue.peekLast() > nums[i]) {
                minQue.pollLast();
            }
            minQue.offerLast(nums[i]);
        }
        return maxLen;
    }


    /**
     * 我们正在玩一个猜数游戏，游戏规则如下：
     * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
     * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
     * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
     *
     * 示例:
     * n = 10, 我选择了8.
     * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
     * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
     * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
     * 游戏结束。8 就是我选的数字。
     * 你最终要支付 5 + 7 + 9 = 21 块钱。
     *
     * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
     */
    public int _0375_猜数字大小_II(int n) {
        if (n == 1) {
            return 0;
        }
        int[][] dp = new int[n + 2][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i >= 1; i--) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
            }
        }
        return dp[1][n];
    }


    /**
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     *
     * 示例 1:
     * 输入:
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * 输出: 3
     * 解释:
     * 长度最长的公共子数组是 [3, 2, 1]。
     */
    public int _0718_最长重复子数组(int[] A, int[] B) {
        int[][] dp = new int[A.length][B.length];

        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[0]) {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 0; j < B.length; j++) {
            if (A[0] == B[j]) {
                dp[0][j] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }


    public static List<Integer> _0054_螺旋矩阵(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) {
            return list;
        }
        int h = matrix.length - 1;
        int w = matrix[0].length - 1;
        int cir = 0;
        while ((cir << 1) < h && (cir << 1) < w) {
            for (int j = cir; j < w - cir; j++) {
                list.add(matrix[cir][j]);
            }
            for (int i = cir; i < h - cir; i++) {
                list.add(matrix[i][w - cir]);
            }
            for (int j = w - cir; j > cir; j--) {
                list.add(matrix[h - cir][j]);
            }
            for (int i = h - cir; i > cir; i--) {
                list.add(matrix[i][cir]);
            }
            cir++;
        }
        if ((cir << 1) == h) {
            for (int j = cir; j <= w - cir; j++) {
                list.add(matrix[cir][j]);
            }
        } else if ((cir << 1) == w) {
            for (int i = cir; i <= h - cir; i++) {
                list.add(matrix[i][cir]);
            }
        }
        return list;
    }



    public static int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length <= 1) {
            return asteroids;
        }
        int end = 0;
        for (int i = 1; i < asteroids.length; i++) {
            end++;
            asteroids[end] = asteroids[i];
            while (end > 0 && asteroids[end] < 0 && asteroids[end - 1] > 0) {
                if (-asteroids[end] == asteroids[end - 1]) {
                    end--;
                } else if (-asteroids[end] > asteroids[end - 1]) {
                    asteroids[end - 1] = asteroids[end];
                }
                end--;
            }
        }
        int[] res = new int[end + 1];
        for (int i = 0; i <= end; i++) {
            res[i] = asteroids[i];
        }
        return res;
    }


    /**
     * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
     * 示例 1:
     * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
     * 输出: 16
     * 解释: 这两个单词为 "abcw", "xtfn"。
     * 示例 2:
     * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
     * 输出: 4
     * 解释: 这两个单词为 "ab", "cd"。
     * 示例 3:
     * 输入: ["a","aa","aaa","aaaa"]
     * 输出: 0
     * 解释: 不存在这样的两个单词。
     */
    public int _0318_最大单词长度乘积(String[] words) {
        int[] dp = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                dp[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        int len = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if ((dp[i] & dp[j]) == 0) {
                    len = Math.max(len, words[i].length() * words[j].length());
                }
            }
        }
        return len;
    }


    /**
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * 示例:
     * 输入: 3
     * 输出:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     * @return
     */
    public static int[][] _0059_螺旋矩阵_II(int n) {
        if (n == 0) {
            return new int[0][0];
        }

        int[][] map = new int[n][n];
        int k = 1;
        int len = n - 1;
        int cir = 0;
        while ((cir << 1) < len) {
            for (int j = cir; j < len - cir; j++) {
                map[cir][j] = k++;
            }
            for (int i = cir; i < len - cir; i++) {
                map[i][len - cir] = k++;
            }
            for (int j = len - cir; j > cir; j--) {
                map[len - cir][j] = k++;
            }
            for (int i = len - cir; i > cir; i--) {
                map[i][cir] = k++;
            }
            cir++;
        }
        if ((cir << 1) == len) {
            map[cir][cir] = k;
        }
        return map;
    }



    public static boolean _0234_回文链表(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode left = head;
        ListNode right = head;
        while (true) {
            left = left.next;
            if (right.next == null) {
                break;
            }
            right = right.next;
            if (right.next == null) {
                break;
            }
            right = right.next;
        }
        right = left;
        ListNode next = left.next;
        while (next != null) {
            ListNode temp = next.next;
            next.next = left;
            left = next;
            next = temp;
        }
        right.next = null;
        while (left != null) {
            if (left.val != head.val) {
                return false;
            }
            left = left.next;
            head = head.next;
        }
        return true;
    }


    public static List<TreeNode> _1110_删点成林(TreeNode root, int[] to_delete) {
        List<TreeNode> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int d : to_delete) {
            set.add(d);
        }
        delNodesCore(root, true, set, list);
        return list;
    }

    private static TreeNode delNodesCore(TreeNode root, boolean father, Set<Integer> set, List<TreeNode> list) {
        if (root == null) {
            return null;
        }
        if (set.contains(root.val)) {
            delNodesCore(root.left, true, set, list);
            delNodesCore(root.right, true, set, list);
            return null;
        } else {
            if (father) {
                list.add(root);
            }
            root.left = delNodesCore(root.left, false, set, list);
            root.right = delNodesCore(root.right, false, set, list);
            return root;
        }
    }



    public static int _1143_最长公共子序列(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                while (i < text1.length()) {
                    dp[i++][0] = 1;
                }
                break;
            }
        }
        for (int j = 0; j < text2.length(); j++) {
            if (text1.charAt(0) == text2.charAt(j)) {
                while (j < text2.length()) {
                    dp[0][j++] = 1;
                }
                break;
            }
        }
        for (int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                dp[i][j] = text1.charAt(i) == text2.charAt(j) ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }


    /**
     * 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
     * 序号代表了一个元素有多大。序号编号的规则如下：
     * 序号从 1 开始编号。
     * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
     * 每个数字的序号都应该尽可能地小。
     *  
     * 示例 1：
     * 输入：arr = [40,10,20,30]
     * 输出：[4,1,2,3]
     * 解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。
     * 示例 2：
     * 输入：arr = [100,100,100]
     * 输出：[1,1,1]
     * 解释：所有元素有相同的序号。
     * 示例 3：
     * 输入：arr = [37,12,28,9,100,56,80,5,12]
     * 输出：[5,3,4,2,8,6,7,1,3]
     * @return
     */
    public static int[] _1331_数组序号转换(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(o -> arr[o]));
        Map<Integer, Integer> map = new HashMap<>();
        boolean[] dp = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
                heap.offer(i);
            } else {
                dp[i] = true;
            }
        }
        int i = 1;
        while (!heap.isEmpty()) {
            int o = heap.poll();
            map.put(arr[o], i);
            arr[o] = i;
            i++;
        }
        for (int j = 0; j < arr.length; j++) {
            if (dp[j]) {
                arr[j] = map.get(arr[j]);
            }
        }
        return arr;

    }


    /**
     * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
     * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
     * 如果不存在这样的子数组，请返回 0 。
     */
    public int _1493_删掉一个元素以后全为_1_的最长子数组(int[] nums) {
        int zero = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        if (zero == -1) {
            return nums.length - 1;
        }
        int pre = 0;
        int max = 0;
        for (int i = zero + 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                max = Math.max(max, i - pre - 1);
                pre = zero + 1;
                zero = i;
            }
        }
        return Math.max(max, nums.length - pre - 1);
    }



    public static String[] 面试题_17_07_婴儿名字(String[] names, String[] synonyms) {
        Map<String, String> map = new HashMap<>();
        for (String synonym : synonyms) {
            int mid = synonym.indexOf(',');
            String a = synonym.substring(1, mid);
            String b = synonym.substring(mid + 1, synonym.length() - 1);
            while (map.containsKey(a)) {
                a = map.get(a);
            }
            while (map.containsKey(b)) {
                b = map.get(b);
            }
            if (a.compareTo(b) == 0) {
                continue;
            }
            if (a.compareTo(b) < 0) {
                map.put(b, a);
            } else {
                map.put(a, b);
            }
        }
        Map<String, Integer> count = new LinkedHashMap<>();
        for (String name : names) {
            int mid = name.indexOf('(');
            String a = name.substring(0, mid);
            int c = Integer.parseInt(name.substring(mid + 1, name.length() - 1));
            while (map.containsKey(a)) {
                a = map.get(a);
            }
            count.put(a, count.getOrDefault(a, 0) + c);
        }
        String[] res = new String[count.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            res[i++] = entry.getKey() + "(" + entry.getValue() + ")";
        }
        return res;
    }


    /**
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     *
     * 示例 1:
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     */
    public static int[] _0503_下一个更大元素_II(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[max]) {
                max = i;
            }
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[max]);
        nums[max] = -1;
        for (int i = (max - 1 + nums.length) % nums.length; i != max; i = (i - 1 + nums.length) % nums.length) {
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            int temp = nums[i];
            nums[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(temp);
        }
        return nums;
    }



    public static int[] _0967_连续差相同的数字(int N, int K) {
        List<Integer> list = new ArrayList<>();
        int i = N == 1 ? 0 : 1;
        for ( ; i < 10; i++) {
            numsSameConsecDiff(N - 1, K, i, i, list);
        }
        int[] arr = new int[list.size()];
        for (i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    private static void numsSameConsecDiff(int N, int K, int num, int pre, List<Integer> list) {
        if (0 == N) {
            list.add(num);
            return;
        }
        if (pre - K >= 0) {
            numsSameConsecDiff(N - 1, K, num * 10 + pre - K, pre - K, list);
        }
        if (pre + K < 10 && K != 0) {
            numsSameConsecDiff(N - 1, K, num * 10 + pre + K, pre + K, list);
        }
    }


    /**
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
     */
    public static List<String> _0093_复原IP地址(String s) {
        List<String> res = new ArrayList<>();
        int[] dp = new int[4];
        int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = s.charAt(i) - '0';
        }
        restoreIpAddresses(nums, 0, dp, 0, res);
        return res;
    }
    private static void restoreIpAddresses(int[] nums, int start, int[] dp, int end, List<String> res) {
        if (end == 4) {
            if (start == nums.length) {
                res.add(dp[0] + "." + dp[1] + '.' + dp[2] + "." + dp[3]);
            }
            return;
        }
        if (start == nums.length) {
            return;
        }
        dp[end] = nums[start++];
        restoreIpAddresses(nums, start, dp, end + 1, res);
        if (start == nums.length) {
            return;
        }
        if (nums[start - 1] == 0) {
            return;
        }
        dp[end] = dp[end] * 10 + nums[start++];
        restoreIpAddresses(nums, start, dp, end + 1, res);
        if (start == nums.length) {
            return;
        }
        dp[end] = dp[end] * 10 + nums[start++];
        if (dp[end] <= 255) {
            restoreIpAddresses(nums, start, dp, end + 1, res);
        }
    }


    /**
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     *
     * 示例 1：
     * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * 输出: ["i", "love"]
     * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     *     注意，按字母顺序 "i" 在 "love" 之前。
     */
    public List<String> _692_前K个高频单词(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> maxHeap = new PriorityQueue<>((s1, s2) -> {
            int div = map.get(s1) - map.get(s2);
            if (div == 0) {
                return s2.compareTo(s1);
            } else {
                return div;
            }
        });

        for (String word : map.keySet()) {
            maxHeap.offer(word);
            if (k == 0) {
                maxHeap.poll();
            } else {
                k--;
            }
        }

        List<String> list = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            list.add(0, maxHeap.poll());
        }
        return list;
    }

    public static void main(String[] args) {





    }

}
