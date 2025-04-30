package org.xjtu_learner.hot_100.first_time.subsequence;

import java.util.ArrayDeque;
import java.util.Deque;

//题目: 239.滑动窗口最大值
//思路: 首先本题的思路就是遍历nums确定窗口起始位置，然后在每一个窗口内找到最大值，如果每个窗口内的最大值现场比较的话，
//      总的时间复杂度为O(nk)。这里的做法是实现一个「最大队列」，类似T155「最小栈」，能在正常队列的基础上在O(1)时间内
//      找到队列中的最大值，这样每个循环内的事情就是一次入队、一次出队以及一次寻找最大值O(1+1+1)，总的时间复杂度为O(n)

// !*! 2025/4/30 好题，本题倒是体现出了滑动窗口的特征，与T438有一定的共性，窗口维护着一种状态，每次滑动窗口都有一个元素离开，一个
//      元素进入，窗口的状态随之变化。同时本题另一个比较大的收获就是「最大(小)队列」，类似「最大(小)栈」


public class T239 {

    public int[] maxSlidingWindow(int[] nums, int k) {

        MaxDeque window = new MaxDeque();
        int[] result = new int[nums.length - k + 1];
        // 初始化md
        for (int i = 0; i < k; i++) {
            window.offer(nums[i]);
        }

        result[0] = window.getMax();

        for (int i = k; i < nums.length; i++) {
            window.poll();
            window.offer(nums[i]);
            result[i - k + 1] = window.getMax();
        }

        return result;
    }

    // 实现一个数据结构，本身是队列，同时能在O(1)时间内找到最大值
    static class MaxDeque {
        Deque<Integer> q;
        Deque<Integer> max;     // 维护一个单调递增队列（较大值靠近队首）

        MaxDeque() {
            q = new ArrayDeque<>();
            max = new ArrayDeque<>();
        }

        void offer(int o) {
            // 正常入队
            q.offer(o);
            // 将o以单调递增的关系从队尾插入
            while (!max.isEmpty() && o > max.peekLast()) {
                max.pollLast();
            }
            max.offer(o);
        }

        int poll() {
            // 正常出队
            int poll = q.poll();
            // 如果出队元素就是最大值（即队首元素），则max同时出队
            if (poll == max.peek()) {
                max.pop();
            }
            return poll;
        }

        int getMax() {  // 获取max队列首部元素即可
            return max.peek();
        }
    }

    public static void main(String[] args) {
        T239 t239 = new T239();
        int[] ints = t239.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }
}
