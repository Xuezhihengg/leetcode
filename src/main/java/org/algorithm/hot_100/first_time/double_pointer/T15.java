package org.algorithm.hot_100.first_time.double_pointer;

import java.util.*;

//题目: 15.三数之和
//思路: 最笨的方法就是三层for循环把每种可能都找出来，时间复杂度为O(n^3)，如果使用hashmap则可以使第三层的
//      时间复杂度降低到O(1)，总时间复杂度为O(n^2)~O(n^3)（因为最坏情况下使用hashmap也是O(n)复杂度），同时使
//      用hashmap写会有复杂的去重逻辑，不推荐
//      这里的方法是排序+双指针的方法，固定一个数，然后通过双指针只需要遍历剩下的元素即可找到包含该固定元素的所以三
//      数组合。这里的代码时间代价比较高，主要是因为去重的逻辑放在了set上，而不是循环体内部

//题解: https://leetcode.cn/problems/3sum/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/29 这个双指针的思路没有想到，自己实现的代码中，去重逻辑是通过set实现的，下一步优化为放在循环体中

public class T15 {

    public List<List<Integer>> threeSum(int[] nums) {

        // 先将nums排序
        Arrays.sort(nums);

        Set<List<Integer>> result = new HashSet<>();

        int fixed = 0;
        while (fixed < nums.length && nums[fixed] <= 0) {
            int left = fixed + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[fixed] + nums[left] + nums[right];

                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[fixed]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    left++;
                    right--;
                } else if (sum < 0) {  // 如果三者之和小于0，则左指针右移
                    left++;
                } else {     // 如果三者之和大于0，则右指针左移
                    right--;
                }
            }
            fixed++;
        }

        return result.stream().toList();
    }

    public static void main(String[] args) {
        T15 t15 = new T15();
        List<List<Integer>> lists = t15.threeSum(new int[]{-2, 0, 1, 1, 2});
    }
}
