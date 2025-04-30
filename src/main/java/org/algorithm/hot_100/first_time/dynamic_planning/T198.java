package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 198.打家劫舍

public class T198 {

    public int rob(int[] nums) {

        int length = nums.length;
        if(length == 1){
            return nums[0];
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 递推公式: dp[i] = max{dp[i-1] , dp[i-2]+nums[i]}
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        return dp[length -1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        T198 t198 = new T198();
        int rob = t198.rob(nums);
    }
}
