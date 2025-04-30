package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 62.不同路径

public class T62 {

    public int uniquePaths(int m, int n) {

        // dp[i][j] 表示到达(i,j)位置的不同路径数
        int[][] dp = new int[m][n];

        // 初始化dp数组
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        // 递推公式: dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {

        T62 t62 = new T62();
        int i = t62.uniquePaths(3, 7);

    }
}
