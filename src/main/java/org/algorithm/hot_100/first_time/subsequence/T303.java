package org.xjtu_learner.hot_100.first_time.subsequence;

//题目: 303. 区域和检索 - 数组不可变
//思路: 由于数组是不变的，因此任意区间和从一开始就是固定的，所以可以在初始化NumArray时确定前缀和，
//      这样在sumRange()方法的时间复杂度就是O(1)了，但代价是初始化NumArray时需要O(n)
//题解: https://leetcode.cn/problems/range-sum-query-immutable/solutions/2693473/javapython3cqian-zhui-he-qiu-ren-yi-qu-j-zwpz/

// !*! 2025/4/22 第一次接触前缀和，原来就是对于不会变的事物，提前计算一些东西，这样之后的操作就会节省一些时间代价

public class T303 {

    static class NumArray {

        int[] preSum;  // preSum[i]表示[0,i)元素的和

        public NumArray(int[] nums) {
            preSum = new int[nums.length + 1];  //要加一是因为在当前preSum定义下，表达所有元素和，即[0,nums.length)的元素和需要preSum(nums.length)
            preSum[0] = 0;  // preSum[0]表示[0,0)的元素和，即为0
            // 生成前缀和
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i-1] + nums[i-1];
            }

        }

        public int sumRange(int left, int right) {
            // [left,right]的元素和等于[0,right+1) - [0,left)
            return preSum[right+1] - preSum[left];
        }
    }
}
