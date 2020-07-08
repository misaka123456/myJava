package myTools;

/**
 * @author xiakai
 * @create 2020-07-05 11:56
 */
public class MyPattern {

    int[][] dp;
    int M;

    public MyPattern(String compile) {
        M = compile.length();
        dp = new int[M + 1][256];
        dp[0][compile.charAt(0)] = 1;
        int X = 0;
        for (int i = 1; i < M; i++) {
            for (int c = 0; c < 256; c++) {
                dp[i][c] = dp[X][c];
            }
            dp[i][compile.charAt(i)] = i + 1;
            X = dp[X][compile.charAt(i)];
        }
    }

    public int matches(String str) {
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            j = dp[j][str.charAt(i)];
            if (j == M) {
                return i - M + 1;
            }
        }
        return -1;
    }

}
