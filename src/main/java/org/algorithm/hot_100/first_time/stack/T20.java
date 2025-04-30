package org.algorithm.hot_100.first_time.stack;

import java.util.Stack;

//题目: 20.有效的括号

public class T20 {

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        if (chars.length % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }

            if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (c == ')' && pop != '(') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
                if (c == '}' && pop != '{') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
