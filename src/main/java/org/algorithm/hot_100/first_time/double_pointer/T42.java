package org.xjtu_learner.hot_100.first_time.double_pointer;

//题目: 42.接雨水
//思路: 正向遍历找左低右高的凹槽的积水量，逆向遍历找左高右低的凹槽的积水量。正向遍历时：左右指针都从最左边出发，
//      右指针向右找第一个高于或等于左柱的位置，计算左右指针之间的积水量，逆向遍历同理。本方法时间复杂度为O(n)，空间复
//      杂度为O(1)，且与动态规划法、双指针法、单调栈法相比要简单明了很多

// !*! 2025/4/29 没有去学习动态规划法和双指针法，下一学习时尝试

public class T42 {

    public int trap(int[] height) {

        int result = 0;
        // 正向扫描
        int left = 0;
        for (int right = 1; right < height.length; right++) {
            if (height[left] <= height[right]) {
                // 计算左右指针之间的积水量
                result += calcu(height, left, right);
                left = right;
            }
        }

        // 逆向扫描
        int right = height.length - 1;
        for (left = height.length - 2; left >= 0; left--) {
            if (height[left] > height[right]) {
                result += calcu(height, left, right);
                right = left;
            }
        }

        return result;
    }

    int calcu(int[] height, int l, int r) {
        int temp = 0;
        int h = Math.min(height[l], height[r]);
        for (int i = l + 1; i < r; i++) {
            temp += (h - height[i]);
        }

        return temp;
    }

    public static void main(String[] args) {
        T42 t42 = new T42();
        int trap = t42.trap(new int[]{2,0,2});
    }
}
