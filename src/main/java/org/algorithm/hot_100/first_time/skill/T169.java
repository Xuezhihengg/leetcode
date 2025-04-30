package org.algorithm.hot_100.first_time.skill;

//题目: 169. 多数元素
//思路: 摩尔投票法，其实本质上就是一直去做配对抵消（假设众数cur，与cur相同votes++，不同则votes--），当votes==0说明
//      前面部分已经相互抵消，当然这个假设的众数不一定是真的众数：如果不是真的众数，那么真的众数将更占优势（非众数自己内耗了）
//      如果是真的众数，也没关系，哪怕每次都是真的众数和非众数两两抵消，真的众数也会剩下来。所以一次遍历，最后剩下的就是真的众数了
//题解: https://leetcode.cn/problems/majority-element/solutions/2362000/169-duo-shu-yuan-su-mo-er-tou-piao-qing-ledrh/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/28 比较难想到

public class T169 {

    public int majorityElement(int[] nums) {
        int cur = 0;
        int votes = 0;

        for (int num : nums) {
            if (votes == 0) {
                cur = num;
            }
            if (cur == num) {
                votes++;
            } else {
                votes--;
            }
        }

        return cur;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        T169 t169 = new T169();
        int i = t169.majorityElement(nums);
    }
}