package org.algorithm.hot_100.first_time.binary_search;

//题目: 34.在排序数组中查找元素的第一个和最后一个位置
//思路: 基于二分查找法框架实现找到元素最左端位置下标（如果元素出现多次）和元素最右端位置下标（如果元素出现多次）的方法，
//      主要是在原框架上修改左走或右走的条件，现在即使等于target也要左走（找最左端元素）或右走（找最右端元素）
//题解: https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solutions/504484/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3-4/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/24 在二分查找法框架上的变式，值得重刷

public class T34 {

    public int[] searchRange(int[] nums, int target) {

        if (nums.length == 0) return new int[]{-1, -1};

        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);

        if(left <= right && nums[left] == target && nums[right] == target){
            return new int[]{left, right};
        }

        return new int[]{-1, -1};
    }

    int binarySearch(int[] nums, int target, boolean leftest) {
        int left = 0;
        int right = nums.length - 1;
        int ans = leftest ? nums.length : 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (leftest) {
                if (target <= nums[mid]) {    // 向左走（即使相同也向左走，这样可以得到相同元素最左端下标）
                    right = mid - 1;
                    ans = mid;
                } else {     // 向右走
                    left = mid + 1;
                }
            } else {

                if (nums[mid] <= target) {    // 向右走（即使相同也向右走，这样可以得到相同元素最右端下标）
                    left = mid + 1;
                    ans = mid;
                } else {     // 向左走
                    right = mid - 1;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1};

        T34 t34 = new T34();
        int[] ints = t34.searchRange(nums, 0);
    }

}
