package org.xjtu_learner.hot_100.first_time.dynamic_planning;

//题目: 70.爬楼梯

public class T70 {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        // dp[i] 表示爬到第i个台阶的方法数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {

        T70 t70 = new T70();
        int i = t70.climbStairs(3);
    }
}
