package org.xjtu_learner.hot_100.first_time.double_pointer;

//题目: 11.盛最多水的容器
//思路: 每次让高度较小的指针向中央走，直到遇到比它高的柱子，现在高度增加但是宽度减小，更新max。本题就很符合双指针框架性，
//      只要清楚思路，通过简单的debug基本就能写出来

public class T11 {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int leftH = height[left];
        int rightH = height[right];

        int max = Math.min(leftH, rightH) * (right - left);

        while (left < right) {
            if (leftH < rightH) {   // 左边柱子向右走
                while (left < height.length && height[left] <= leftH) {
                    left++;
                }
                leftH = height[left];
            } else { // 右边柱子向左走
                while (right > 0 && height[right] <= rightH) {
                    right--;
                }
                rightH = height[right];
            }
            // 计算新的容量并更新max
            int newCap = Math.min(leftH, rightH) * (right - left);
            max = Math.max(max, newCap);
        }

        return max;
    }

    public static void main(String[] args) {
        T11 t11 = new T11();
        int i = t11.maxArea(new int[]{1, 1});
    }


}
