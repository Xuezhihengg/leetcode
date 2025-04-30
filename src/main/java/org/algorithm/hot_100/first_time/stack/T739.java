package org.xjtu_learner.hot_100.first_time.stack;

import java.util.Stack;

//题目: 739.每日温度
//思路: 这是一个单调递减栈，它有以下特点：
//                          - 每个元素入栈时能确定它左侧第一个大于它的元素（入栈的时候要和栈顶元素比较，这个时候可知）
//                          - 每个元素出栈时能确定它右侧第一个大于它的元素（要出栈说明有新元素进来且比它大，新元素即它右侧第一个大于它的）
//      后面的特点即解决本题的关键

// !*! 2025/4/26 自己倒是做出来了，但没有发现本题的单调栈框架，在写T84时发现本题本质上是一个单调递减栈

public class T739 {

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<int[]> stack = new Stack<>(); //stack的元素是一个二元组，即(天数，温度)

        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            // 如果栈为空或者当前温度比栈顶温度低（或等于）则入栈
            int temperature = temperatures[i];
            if (stack.isEmpty() || temperature <= stack.peek()[1]) {
                stack.push(new int[]{i, temperature});
            } else {
                // 如果当前温度大于栈顶温度，则出栈，并记录结果，这里需要一直弹栈直到当前温度小于栈顶温度
                int[] pop;
                while (!stack.isEmpty() && temperature > stack.peek()[1]) {
                    pop = stack.pop();
                    result[pop[0]] = i - pop[0];
                }
                stack.push(new int[]{i, temperature});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{34, 80, 80, 34, 34, 80, 80, 80, 80, 34};

        T739 t739 = new T739();
        int[] ints = t739.dailyTemperatures(nums);
    }
}
