package org.xjtu_learner.hot_100.first_time.stack;

import java.util.Objects;
import java.util.Stack;

//题目: 155.最小栈
//思路: 通过两个栈来实现，一个norm栈正常插入，一个min栈维护一个栈顶到栈底递增的栈，插入时norm正常插入，如果值比min栈
//      顶元素小，则同时插入min栈；出栈时先从norm弹栈，然后判断是否与min栈顶元素等值，如果是则min栈也弹栈

// !*! 2025/4/25 好题，初次接触最小栈，这个思路需要记忆

public class T155 {

    static class MinStack {
        Stack<Integer> min;
        Stack<Integer> norm;

        public MinStack() {
            min = new Stack<>();
            norm = new Stack<>();
        }

        public void push(int val) {
            // norm栈正常插入
            norm.push(val);
            // 与min中的元素去比较，如果比min栈顶的元素还小（或等于），则同时插入min栈，否则不插
            if(min.isEmpty()){
                min.push(val);
                return;
            }
            if (val <= min.peek()) {
                min.push(val);
            }

        }

        public void pop() {
            Integer pop = norm.pop();
            if (Objects.equals(pop, min.peek())) {
                min.pop();
            }
        }

        public int top() {
            return norm.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
