package org.algorithm.hot_100.first_time.binary_search;

//题目: 153.寻找旋转排序数组中的最小值
//思路: 和T33的思路相似

public class T153 {

    public int findMin(int[] nums) {

        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= nums[right]) {   //右半部分是有序的，则直接往左半部分走
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            } else {     //左半部分是有序的，则将min置为左半部分最小并向右走
                min = Math.min(min, nums[left]);
                left = mid + 1;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{11,13,15,17};

        T153 t153 = new T153();
        int min = t153.findMin(nums);
    }
}
