package org.algorithm.hot_100.first_time.stack;

import java.util.Stack;

//题目: 42.接雨水
//题解: https://leetcode.cn/problems/trapping-rain-water/solutions/185678/trapping-rain-water-by-ikaruga/

// !*! 接雨水的单调栈解法，需要对单调栈比较深入的理解，比较难想清楚，很值得重刷，这次刷完还是懵懵懂懂

public class T42 {

    //思路: 使用单调递减栈，单调递减栈有这样的特点：当前元素大于栈顶元素时，栈顶元素就是当前元素左边凹槽的最低元素
    //      当当前元素大于栈顶元素时，就可以结算以当前元素为右柱所能接住的雨水，这些雨水不是一次性算出来的
    //      而是在while不断弹栈的过程中累加起来的，每次弹栈都只考虑以当前元素为右柱，刚弹出来的元素为凹槽最低元素，以及
    //      最低元素再左边的元素作为左柱所接住的雨水
    public int trap(int[] height) {

        Stack<Integer> stack = new Stack<>();

        int count = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {   // 如果栈顶元素<当前元素，则出栈
                Integer lowest = stack.pop();
                if(stack.isEmpty()){
                    break;  // 保证下面的peek不会报错
                }
                Integer left = stack.peek();

                int curWidth = i - left - 1;
                int curHeight = Math.min(height[left], height[i]) - height[lowest];
                count += (curWidth * curHeight);
            }
            // 入栈前先累加接水量
            stack.push(i);

        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        T42 t42 = new T42();
        int trap = t42.trap(nums);
    }

}
