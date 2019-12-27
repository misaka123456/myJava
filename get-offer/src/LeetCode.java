import myTools.MyArrayTools;

import java.util.*;

public class LeetCode {

    /**
     * 997. 找到小镇的法官
     * @param N
     * @param trust
     * @return
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
     *
     * @param abba
     * @param S
     * @return
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
     * @param T
     * @return
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
     * @param pattern
     * @param str
     * @return
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


    public static int partitionDisjoint(int[] A) {

        if (A.length == 2) {
            return 1;
        }
        int max = A[0];
        int min = A[A.length - 1];
        int left = 0;
        int right = A.length - 1;
        boolean ex = true;
        while (left < right) {
            if (ex) {
                ex = false;
                if (A[left] > min) {
                    return left;
                }
                max = Math.max(max, A[left]);
                left++;
            } else {
                ex = true;
                if (A[right] < max) {
                    return A.length - right;
                }
                min = Math.min(min, A[right]);
                right--;
            }
        }
        return left + 1;

    }







}
