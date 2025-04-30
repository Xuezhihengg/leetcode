package org.xjtu_learner.hot_100.first_time.stack;

import java.util.*;

//题目: 901.股票价格跨度
//思路: 单调递减栈，每个元素在入栈的时候就可以知道它左边第一个大于它的元素位置，作差即可算出跨度

public class T901 {

    static class StockSpanner {

        Deque<Integer> stack;   // 维护一个单调递减栈，内容是下标
        List<Integer> prices;   // 价格数组

        public StockSpanner() {
            stack = new ArrayDeque<>();
            prices = new ArrayList<>();
        }

        public int next(int price) {
            // 如果栈顶价格<=当前价格，则出栈
            while (!stack.isEmpty() && prices.get(stack.peek()) <= price) {
                stack.pop();
            }
            // 入栈时可以知道它左边第一个大于它的元素位置
            prices.add(price);
            int cur = prices.size() - 1;

            int left = stack.isEmpty() ? -1 : stack.peek();
            stack.push(cur);
            return cur - left;
        }
    }

    public static void main(String[] args) {
        StockSpanner ss = new StockSpanner();

        System.out.println(ss.next(28));
        System.out.println(ss.next(14));
        System.out.println(ss.next(28));
        System.out.println(ss.next(70));
        System.out.println(ss.next(60));
        System.out.println(ss.next(75));
        System.out.println(ss.next(85));
    }
}
