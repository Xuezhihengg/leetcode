package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 152.乘积最大子数组

// !*! 2025/4/27 好题，很值得重刷，类似T300，题目中出现了连续子序列的要素，dp数组的定义就可以是以xxx为结尾的xxx，
//               递推公式类似与要不要带上前面的或者带上前面的哪一个？


public class T152 {

    //思路: dp数组定义以及递推公式类似T300，本题特殊之处在于由于nums[i]可能是负值，所以dp[i]不仅仅是从dp[i-1]*nums[i]
    //      得到，还有可能从当前最小的负值得到，所以dp数组的定义变为二维的，dp[0][i]代表最大正乘积，dp[1][i]代表最大负乘积
    public int maxProduct1(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        // dp[0][i] 表示以nums中第i个元素为结尾的连续子序列的最大正乘积
        // dp[1][i] 表示以nums中第i个元素为结尾的连续子序列的最大负乘积
        int[][] dp = new int[2][nums.length + 1];

        dp[0][0] = 1;
        dp[1][0] = 1;

        int totalMax = -Integer.MAX_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            // 递推dp[0][i]
            if (nums[i - 1] > 0) {
                dp[0][i] = Math.max(nums[i - 1], dp[0][i - 1] * nums[i - 1]);   // 要么选择带上前面的，要么抛弃前面的只选当前的
            } else {
                dp[0][i] = Math.max(nums[i - 1], dp[1][i - 1] * nums[i - 1]);
            }
            // 递推dp[1][i]
            if (nums[i - 1] > 0) {
                dp[1][i] = Math.min(nums[i - 1], dp[1][i - 1] * nums[i - 1]);
            } else {
                dp[1][i] = Math.min(nums[i - 1], dp[0][i - 1] * nums[i - 1]);
            }
            totalMax = Math.max(totalMax, dp[0][i]);
        }

        return totalMax;
    }

    //思路: 由于上面的递推公式只由前一想=项推出，所以可以把dp数组省掉
    //题解: https://leetcode.cn/problems/maximum-product-subarray/solutions/7561/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/?envType=study-plan-v2&envId=top-100-liked
    public int maxProduct2(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int max = Integer.MIN_VALUE;    // 记录到目前为止的最大乘积
        int imax = 1;                   // 以当前元素结尾的子数组中的最大乘积
        int imin = 1;                   // 以当前元素结尾的子数组中的最小乘积

        for (int num : nums) {
            if (num < 0) {    // 当前数小于0会使最大值变为最小值，最小值变为最大值
                int temp = imax;
                imax = imin;
                imin = temp;
            }

            imax = Math.max(imax * num, num);   // 要么选择带上前面的，要么抛弃前面的只选当前的
            imin = Math.min(imin * num, num);

            max = Math.max(max, imax);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, -2, 4};
        T152 t152 = new T152();
        int i = t152.maxProduct2(nums);
    }
}
