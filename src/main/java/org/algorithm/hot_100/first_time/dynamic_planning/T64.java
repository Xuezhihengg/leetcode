package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 64.最小路径和

public class T64 {

    public int minPathSum(int[][] grid) {
        int h = grid.length;
        int w = grid[0].length;

        // dp[i][j] 表示到达(i,j)的最小路径数字和
        int[][] dp = new int[h][w];

        // 初始化dp数组
        dp[0][0] = grid[0][0];
        for (int i = 1; i < h; i++) {
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }
        for (int i = 1; i < w; i++) {
            dp[0][i] = grid[0][i] + dp[0][i-1];
        }

        // 递推公式: dp[i][j] = grid[i][j] + min{ dp[i-1][j], dp[i][j-1] }
        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[h-1][w-1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        T64 t64 = new T64();
        int i = t64.minPathSum(grid);
    }
}
