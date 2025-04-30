package org.xjtu_learner.hot_100.first_time.subsequence;

import java.util.HashMap;

//题目: 560.和为K的子数组
//题解: https://leetcode.cn/problems/subarray-sum-equals-k/solutions/2883683/javapython3cqian-zhui-he-ha-xi-biao-lian-r2hn/

// !*! 2024/4/22 前缀和的方法不太好理解

public class T560 {

    //思路: 由i确定子数组的开始位置，通过j向后累加直到尾部，这样可以边累加边统计满足条件的个数，这样的时间复杂度为O(n^2)
    //      更笨的办法是i确定子数组的开始位置，j确定子数组的结束位置或者子数组长度，然后对子数组累加，这样的时间复杂度是O(n^3)
    public int subarraySum1(int[] nums, int k) {

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];

                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }


    //思路: 前缀和 + 哈希表
    //      由于nums一开始就确定了，可以得到nums的前缀和数组，子数组和为k即preSum[i]-preSum[j]=k，可以遍历preSum数组，
    //      每次循环找到对于确定的preSum[i]，有多少种子数组满足preSum[i]-preSum[j]=k，即在preSum[i]前面有多少个元素
    //      j满足preSum[j]=preSum[i]-k
    public int subarraySum2(int[] nums, int k) {

        // 生成nums的前缀和数组
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int count = 0;
        // 这个哈希表存的是 sj -> sj出现的次数，这样对于每一个确定的si，就不需要再次求其前面sj出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        // 遍历preSum数组
        for (int si : preSum) {
            int sj = si - k;
            if (map.containsKey(sj)) {
                count += map.get(sj);
            }

            // 更新map
            map.put(si, map.getOrDefault(si, 0) + 1);
        }

        return count;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        T560 t560 = new T560();
        int i = t560.subarraySum2(nums, 3);
    }
}
