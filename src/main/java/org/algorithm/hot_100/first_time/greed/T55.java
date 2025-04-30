package org.algorithm.hot_100.first_time.greed;

//题目: 55.跳跃游戏
//思路: 使用farthest记录考虑到当前格子能跳到到最远距离，farthest = max{ farthest , i + nums[i] }，最后
//      只用判断farthest是否能大于等于最后一个元素下标即可

public class T55 {

    public boolean canJump(int[] nums) {

        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (farthest >= i) {
                farthest = Math.max(farthest, i + nums[i]);
            }
        }

        return farthest >= nums.length - 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 2, 3};
        T55 t55 = new T55();

        boolean b = t55.canJump(nums);
    }
}
