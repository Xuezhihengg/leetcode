package org.algorithm.hot_100.first_time.stack;

import java.util.Stack;

//题目: 394.字符串解码
//思路: 遇到数字、字母、'['都直接入栈，遇到']'开始出栈，构造字母和倍数，并将结果重新入栈，如此重复知道s都被处理完
//      这里使用了Stack<Character>，限制了栈只能插入字符，构造的结果重新入栈的时候只能一个一个入，如果能直接将
//      一整个String入栈，这将会更快

// !*! 2025/4/25 自己实现的方法在时间表现上不优，还有优化的空间，值得重刷，可以加深对栈类问题的理解

public class T394 {

    public String decodeString(String s) {
        int cur = 0;

        Stack<Character> stack = new Stack<>();
        while (cur < s.length()) {
            char c = s.charAt(cur++);
            if (Character.isLetterOrDigit(c)) {   // 如果是数字或字母则直接入栈
                stack.push(c);
            }
            if (c == '[') {
                stack.push(c);
            }
            // 如果为']'则开始出栈
            if (c == ']') {
                // 弹栈弹到']'
                StringBuilder sb = new StringBuilder();
                char temp;
                while ((temp = stack.pop()) != '[') {
                    sb.append(temp);
                }
                String reversed = sb.reverse().toString();
                // 弹栈找到倍数，注意这个倍数不一定只是一位数
                sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(temp = stack.peek())) {
                    sb.append(temp);
                    stack.pop();
                }
                int times = Integer.parseInt(sb.reverse().toString());
                char[] toPush = reversed.repeat(times).toCharArray();
                for (char to : toPush) {
                    stack.push(to);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        T394 t394 = new T394();
        String s = t394.decodeString("3[a]2[bc]");
    }
}
