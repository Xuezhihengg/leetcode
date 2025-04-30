package org.xjtu_learner.hot_100.first_time.stack;

import java.util.Stack;

//题目: 84.柱状图中最大的矩形
//题解: https://leetcode.cn/problems/largest-rectangle-in-histogram/solutions/142012/bao-li-jie-fa-zhan-by-liweiwei1419/
//      https://leetcode.cn/problems/largest-rectangle-in-histogram/solutions/108083/84-by-ikaruga/

// !*! 2025.4.26 好题值得重刷，把单调栈的包装了起来，不那么容易看出来使用单调栈

public class T84 {

    //思路: 暴力解法，遍历每一个高度，向左向右拓展它的最大宽度，得到以此处为高度能得到的最大面积，时间复杂度O(n^2)
    public int largestRectangleArea1(int[] heights) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < heights.length; i++) {
            // 向左找，一旦遇到小于它的则停止
            int height = heights[i];
            int leftIndex = i;
            while (leftIndex - 1 >= 0 && heights[leftIndex - 1] >= height) {
                leftIndex--;
            }

            // 向右找，一旦遇到小于它的则停止
            int rightIndex = i;
            while (rightIndex + 1 < heights.length && heights[rightIndex + 1] >= height) {
                rightIndex++;
            }

            max = Math.max(max, height * (rightIndex - leftIndex + 1));
        }

        return max;
    }

    //思路: 使用单调递增栈，它有以下特点：
    //                      - 每个元素在入栈的时候能确定它左侧第一个小于它的元素位置（入栈的时候要和栈顶元素比较，这个时候可知）
    //                      - 在出栈的时候能确定它右侧第一个小于它的位置（要出栈说明有新元素进来且比它小，新元素即它右侧第一个小于它的）
    //      依据这两个特点，通过单调递增栈即可避免暴力解法时的枚举找到最左边界和最右边界
    public int largestRectangleArea2(int[] heights) {

        int[] leftIndexes = new int[heights.length + 1];

        int[] newHeights = new int[heights.length + 1];
        newHeights[newHeights.length - 1] = -1;
        System.arraycopy(heights, 0, newHeights, 0, heights.length);

        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 左边的哨兵节点

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < newHeights.length; i++) {
            int height = newHeights[i];
            while (stack.peek() != -1 && newHeights[stack.peek()] > height) { // 如果栈顶元素>当前元素则出栈，直到栈顶元素<=当前元素
                Integer pop = stack.pop();
                // 出栈时确定出栈元素的右侧第一个小于它的位置
                // 此时这个高度的左边界和右边界都已确定，这里即可更新max{
                int width = i - leftIndexes[pop] - 1;
                max = Math.max(max, heights[pop] * width);

            }
            // 入栈，入栈时确定新元素左侧第一个小于它的元素位置
            leftIndexes[i] = stack.peek();  // 如果是第一个元素入栈则这里会出错，为了避免这个边界问题，设置左边的哨兵节点
            stack.push(i);
        }
        //（如果不加入有边界哨兵节点-1）到这里栈中还存留一些元素没有出栈，它们的右边界也就没有确定，其实它们没出栈说明它们右边界就是最大的，
        // 可以通过右边的哨兵节点实现，把这个-1直接加入到数组中就可以复用上面while内的代码

        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 5, 6, 2, 3};

        T84 t84 = new T84();
        int i = t84.largestRectangleArea2(nums);
    }
}
