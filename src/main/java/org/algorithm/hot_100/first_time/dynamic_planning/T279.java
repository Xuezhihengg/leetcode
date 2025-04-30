package org.xjtu_learner.hot_100.first_time.dynamic_planning;

//题目: 279.完全平方数

public class T279 {

    public int numSquares(int n) {

        // dp[i] 表示「和为 n 的完全平方数的最少数量」
        int[] dp = new int[n + 1];
        dp[0] = 0;

        // 递推公式: dp[i] = min{dp[i-4] + 1, dp[i-9] + 1, ...}
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j] + 1);
            }
            dp[i] = min;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        T279 t279 = new T279();
        int i = t279.numSquares(12);
    }
}
