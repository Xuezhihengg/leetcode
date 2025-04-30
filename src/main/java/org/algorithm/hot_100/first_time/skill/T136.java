package org.algorithm.hot_100.first_time.skill;

//题目: 136.只出现一次的数字
//思路: 通过异或运算可在O(n)时间复杂度和线性空间复杂度内得到只出现一次的数字，这是异或运算的性质的体现
//题解: https://leetcode.cn/problems/single-number/solutions/242211/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/28 这个思路确实也称得上是技巧了，没想到，需要掌握这种思路

public class T136 {

    public int singleNumber(int[] nums) {
        int result = 0;
       for (int n:nums){
           result ^= n;
       }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 1, 2};
        T136 t136 = new T136();
        int i = t136.singleNumber(nums);
    }
}
