package org.algorithm.hot_100.first_time.skill;

//题目: 287.寻找重复数
//思路: 本题思路是否巧妙，在题目的限制下（1 <= nums[i] <= n，nums中只有一个整数出现两次或多次，其余整数均只出现一次）
//      本题数组可以看作一个链表，而且一定是一个带环的链表（别的数组可没有这种特性），如以[1,3,4,2,2]为例：
//      0 -> 1, 1 -> 3, 2 -> 4, 3 -> 2, 4 -> 2，即如下链表：   0->1->3->2->4-|
//                                                                    ^    |
//                                                                    |----|
//      所以找重复数的问题就转换为了环形链表找入口的问题（见T42）
//题解: https://leetcode.cn/problems/find-the-duplicate-number/solutions/58841/287xun-zhao-zhong-fu-shu-by-kirsche/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/28 根本想不到，技巧性太强

public class T287 {

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        // 先进行第一次相遇
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];

        } while (slow != fast);

        // 将快指针移到队首，现在二指针都一次一步直到相遇，相遇点即为环的入口，也就重复元素
        fast = 0;
        do {
            slow = nums[slow];
            fast = nums[fast];

        } while (slow != fast);

        return slow;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,4,2,2};
        T287 t287 = new T287();
        int duplicate = t287.findDuplicate(nums);
    }

}
