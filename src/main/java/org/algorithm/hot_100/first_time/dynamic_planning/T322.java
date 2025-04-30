package org.xjtu_learner.hot_100.first_time.dynamic_planning;

import java.util.Arrays;

//题目: 322.零钱兑换
//思路: 完全背包问题，通过初始化（第一行初始化为Max）来实现没有解情况的传递，dp[i][j]没有解就是dp[i-1][j]和
//      dp[i][j-coins[i]]都没有解。完全背包问题的关键就是理解它的递推公式: dp[i][j] = min{dp[i-1][j], dp[i][j-coins[i] + 1}
//题解: https://www.hello-algo.com/chapter_dynamic_programming/unbounded_knapsack_problem/#2_1

// !*! 2025/4/27 不会，很值得重刷


public class T322 {


    public int coinChange(int[] coins, int amount) {

        Arrays.sort(coins);
        int Max = amount + 1;
        // dp[i][j] 表示考虑前i个硬币，组成金额j需要的最少硬币数
        int[][] dp = new int[coins.length][amount + 1];
        // 初始化dp
        for (int i = 1; i <= amount; i++) {
            dp[0][i] = i % coins[0] == 0 ? i / coins[0] : Max;
        }

        // 递推公式: dp[i][j] = min{dp[i-1][j], dp[i][j-coins[i] + 1}
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i] > j) {
                    dp[i][j] = dp[i - 1][j];    // 不选该硬币
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i]] + 1);
                }
            }
        }

        return dp[coins.length - 1][amount] == Max ? -1 : dp[coins.length - 1][amount];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,5};
        T322 t322 = new T322();
        int i = t322.coinChange(nums, 11);
    }

}
