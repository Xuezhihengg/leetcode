package org.xjtu_learner.hot_100.first_time.binary_search;

//题目: 33.搜索旋转排序数组
//思路: 每次选定mid后，一定有一边是有序的而另一边是无序的（不把mid排除在外），如果在有序的一边，target还在其范围内，就
//      进入这一边，否则就进入另一边。
//      本题又是二分查找的一个变式，何时向左走和何时向右走的条件发生了变化，这便是这个框架可以变通的地方
//题解: https://leetcode.cn/problems/search-in-rotated-sorted-array/solutions/22068/ji-bai-liao-9983de-javayong-hu-by-reedfan/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/25 又一二分查找的一个变式，不会，值得重刷

public class T33 {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                ans = mid;
                break;
            } else if (nums[left] <= nums[mid]) {   //左边部分[left, mid]是有序的
                if (nums[left] <= target && target < nums[mid]) { //且target在其范围内
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { //右边部分[mid+1, right]是有序的
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 8, 1, 2, 3};

        T33 t33 = new T33();
        int search = t33.search(nums, 8);
    }

}
