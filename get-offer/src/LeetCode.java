import java.util.HashMap;

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









}
