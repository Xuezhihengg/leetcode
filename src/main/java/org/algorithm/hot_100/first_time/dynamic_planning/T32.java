package org.algorithm.hot_100.first_time.dynamic_planning;

import java.util.ArrayDeque;
import java.util.Deque;

//题目: 32.最长有效括号

// !*! 2025/4/28 两种思路都很值得学习

public class T32 {

    //思路: 动态规划，dp[i]表示以s中第i个字符为结尾的最长有效括号子串的长度（这种定义方式在连续字串问题中很常见）
    //      递推公式比较复杂，见题解，这种方法的难点就在于递推公式的思考:
    //          - 以'('结尾，dp[i]=0，因为以'('结尾的一定不合法
    //          - 以')'结尾：
    //                  - 前一个字符为'('，构成......()模式，dp[i] = dp[i-2]+2
    //                  - 前一个字符为')'，构成......))模式，dp[i] = dp[i-2-dp[i-1]] + dp[i-1] + 2，即 (A)((B)) 中 A + B + 2，这里边界不好直接确定，可以debug确定
    //题解: https://leetcode.cn/problems/longest-valid-parentheses/solutions/314683/zui-chang-you-xiao-gua-hao-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked
    public int longestValidParentheses1(String s) {

        if (s.length() <= 1) {
            return 0;
        }

        int[] dp = new int[s.length() + 1];
        dp[0] = 0;
        dp[1] = 0;  // 少于两个字符无法组成有效括号

        int max = 0;
        for (int i = 2; i <= s.length(); i++) {
            char c = s.charAt(i - 1);
            if (c == '(') {
                dp[i] = 0;  // 以'('结尾的一定不合法
            } else {
                char before = s.charAt(i - 2);
                if (before == '(') {     // ......() 的情况
                    dp[i] = dp[i - 2] + 2;
                } else {                 // ......)) 的情况
                    if (i - 2 - dp[i - 1] >= 0 && s.charAt(i - 2 - dp[i - 1]) == '(') {   // (...)((...)) 的情况
                        dp[i] = dp[i - 2 - dp[i - 1]] + dp[i - 1] + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }

    //思路: 使用栈，栈中保存的是最远一个没有被匹配的下标
    //    对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
    //    对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
    //         - 如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新「最远一个没有被匹配的下标」
    //         - 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
    //题解: https://leetcode.cn/problems/longest-valid-parentheses/solutions/314683/zui-chang-you-xiao-gua-hao-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked
    public int longestValidParentheses2(String s) {

        // 栈内保存的是未被匹配的括号
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 哨兵节点

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            // 如果是'('则入栈
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            }

            // 如果是')'
            if (c == ')') {
                stack.pop();  // 弹出栈顶表示匹配这个右括号
                if (stack.isEmpty()) { // 如果栈为空，则说明这个右括号是无法被匹配的，则将该右括号入栈
                    stack.push(i);
                } else {    // 计算长度
                    Integer left = stack.peek();
                    max = Math.max(max, i - left);
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {

        T32 t32 = new T32();
        int i = t32.longestValidParentheses2(")()())");
    }
}
