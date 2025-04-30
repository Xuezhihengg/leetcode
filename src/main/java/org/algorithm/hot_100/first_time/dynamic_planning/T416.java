package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 416.分割等和子集
//思路: 转换为0-1背包问题
//题解: https://leetcode.cn/problems/partition-equal-subset-sum/solutions/442320/fen-ge-deng-he-zi-ji-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/28 没看题解还没想到可以转换为0-1背包问题，非连续子序列（也就是随便选了）以期达到某一目标的就可以转化为背包问题

public class T416 {

    public boolean canPartition(int[] nums) {
        // 先计算出nums元素和的一半
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int Max = nums.length;

        // dp[i][j] 表示只考虑前i个元素，达到和为j最少元素数
        boolean[][] dp = new boolean[nums.length + 1][target + 1];

        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }


        // 递推公式: dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]+1
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 11, 5};
        T416 t416 = new T416();
        boolean b = t416.canPartition(nums);
    }
}
