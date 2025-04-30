package org.algorithm.hot_100.first_time.array;

//题目: 53.最大子数组和

public class T53 {

    //思路: 动态规划
    public int maxSubArray(int[] nums) {

        // dp[i] 表示以nums中第i个元素结尾的连续数组的最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        // 递推公式: dp[i] = Math.max(dp[i - 1], 0) + nums[i];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        T53 t53 = new T53();
        int i = t53.maxSubArray(new int[]{5, 4, -1, 7, 8});
    }
}
