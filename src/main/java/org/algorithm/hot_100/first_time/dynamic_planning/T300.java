package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 300.最长递增子序列
//思路: 本题的关键在于确定dp数组的含义以及确定递推公式，dp[i]并不表示「考虑nums中前i个元素的最长严格递增子序列的长度」，
//      而是「以nums中第i个元素结尾的最长严格递增子序列的长度」，这样才方便考虑要不要加入第i个元素。递推公式是：
//      dp[i] = max{ if(nums[i] > nums[0]) dp[0] + 1, if(nums[i] > nums[1]) dp[1] + 1, ... }，也就是在
//      确定dp[i]时，遍历(0,i)，并通过nums[i] > nums[j]，筛选出可以作为nums[i]前缀部分的可能性，并找到这些可能性
//      中最长的严格递增子序列的长度

public class T300 {

    public int lengthOfLIS(int[] nums) {

        if (nums.length == 1) {
            return 1;
        }
        // dp[i] 表示以nums中第i个元素结尾的最长严格递增子序列的长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int totalMax = Integer.MIN_VALUE;

        // 递推公式: dp[i] = max{ if(nums[i] > nums[0]) dp[0] + 1, if(nums[i] > nums[1]) dp[1] + 1, ... }
        for (int i = 1; i < nums.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            totalMax = Math.max(totalMax, dp[i]);
        }

        return totalMax;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 10, 4, 3, 8, 9};

        T300 t300 = new T300();
        int i = t300.lengthOfLIS(nums);
    }

}
