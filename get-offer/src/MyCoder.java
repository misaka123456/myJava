import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCoder {


    /**
     * 最长公共子序列
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最长长度
     */
    public static int maxLengthOfCommonStr(String str1, String str2) {

        if (str1 == null || str1.length() == 0
        || str2 == null || str2.length() == 0) {
            return 0;
        }
        return maxLengthOfCommonStr(str1.toCharArray(), str2.toCharArray());
    }

    public static int maxLengthOfCommonStr(char[] chars1, char[] chars2) {

        if (chars1 == null || chars1.length == 0
        || chars2 == null || chars2.length == 0) {
            return 0;
        }

        int[][] dp = new int[chars1.length][chars2.length];

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
            }
        }
        for (int i = 0; i < chars2.length; i++) {
            if (chars1[0] == chars2[i]) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
            }
        }
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[chars1.length - 1][chars2.length - 1];
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

}
