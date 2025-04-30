package org.algorithm.hot_100.first_time.backtracking;

import java.util.*;

//题目: 22.括号生成
//题解: https://leetcode.cn/problems/generate-parentheses/solutions/192912/gua-hao-sheng-cheng-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/24 自己实现的方法会超时，括号的有效性可以通过合适的剪枝函数实现，进而减少时间消耗，值得重刷

public class T22 {

    //思路: 这是一个排列树，需要实现一个方法判断括号的排列是否合法，这个暴力搜索的方法会超出时间限制
    public List<String> generateParenthesis1(int n) {

        char[] parenthesis = new char[n * 2];
        for (int i = 0; i < n * 2; i++) {
            if (i % 2 == 0) {
                parenthesis[i] = '(';
            } else {
                parenthesis[i] = ')';
            }
        }

        Set<String> result = new HashSet<>();
        dfs1(parenthesis, 0, result);

        return result.stream().toList();
    }

    void dfs1(char[] parenthesis, int index, Set<String> result) {
        if (index == parenthesis.length) {
            // output
            if (isValid(parenthesis)) {
                result.add(new String(parenthesis));
            }
            return;
        }

        for (int i = index; i < parenthesis.length; i++) {
            swap(parenthesis, index, i);
            dfs1(parenthesis, index + 1, result);
            swap(parenthesis, index, i);
        }
    }

    void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    boolean isValid(char[] parenthesis) {
        Stack<Character> stack = new Stack<>();
        for (char c : parenthesis) {
            if (c == '(') stack.push(c);
            if (c == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return true;
    }

    int left;
    int right;

    //思路: 括号的有效性通过以下剪枝函数实现：左括号数<=n、右括号数<=左括号数，然后通过子集树即可满足时间限制
    //      本题的递归函数没有index这一入参，是因为index的作用是判断当前确定到了哪一位置，何时到达完全确定的
    //      状态进而进行output，本题StringBuilder的长度可以反映这一信息，所以不需要index
    public List<String> generateParenthesis2(int n) {

        left = 0;
        right = 0;
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        dfs2(n, builder, result);

        return result;
    }

    void dfs2(int n, StringBuilder sb, List<String> result) {
        if (sb.length() == n * 2) {
            // output
            result.add(sb.toString());
            return;
        }

        // 左括号剪枝
        if (left < n) {
            // 将本位置括号设置为'('
            sb.append('(');
            left++;
            dfs2(n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            left--;
        }

        // 右括号剪枝
        if (right < left) {
            // 将本位置括号设置为')'
            sb.append(')');
            right++;
            dfs2(n, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            right--;
        }
    }


    public static void main(String[] args) {
        T22 t22 = new T22();
        List<String> strings = t22.generateParenthesis2(6);
    }
}
