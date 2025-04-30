package org.algorithm.hot_100.first_time.binary_search;

//题目: 35.搜索插入位置
//思路: 这里居然不是使用递归实现的，不论哪种方法，这里的边界条件都是不太好处理的，最好作为代码框架记忆
//题解: https://leetcode.cn/problems/search-insert-position/solutions/8017/hua-jie-suan-fa-35-sou-suo-cha-ru-wei-zhi-by-guanp/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/24 本题是二分查找的典型框架，记忆！


public class T35 {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                return mid;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;    // 为什么返回left？
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1, 3, 5, 6};

        T35 t35 = new T35();
        int i = t35.searchInsert(nums, 2);
    }
}
