package org.xjtu_learner.hot_100.first_time.greed;

//题目: 45.跳跃游戏II
//思路: 在T55的基础上实现，通过end记录当前这一步能跳到的最远距离，如果到达这一位置，意味着必须跳下一步了。本题的大问题
//      拆分成子问题就是当前这一步何时跳，贪心的跳法就是只有到了非跳不可的时候才需要跳
//题解: https://leetcode.cn/problems/jump-game-ii/solutions/230241/tiao-yue-you-xi-ii-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/27 贪心算法没有固定的框架，比较难想

public class T45 {

    public int jump(int[] nums) {
        int count = 0;
        int farthest = 0;
        int end = 0;    // end表示你当前这一步能跳到的最远距离，如果到达这一位置，意味着你必须跳下一步了

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {    // 必须跳下一步了
                end = farthest;  // 新的一步内能跳到的最远距离
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};

        T45 t45 = new T45();
        int jump = t45.jump(nums);
    }
}
