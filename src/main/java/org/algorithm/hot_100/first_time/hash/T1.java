package org.algorithm.hot_100.first_time.hash;

import java.util.HashMap;
import java.util.Map;

//题目: 1. 两数之和
//思路: 本题类似T560，si + sj = target，那就是要遍历nums，遍历到si时找前面出现过的sj = target - si，为了减少
//      这个查找时间，可以在每次遍历的si后，把这个si及其下标存入map中

// !*! 2025/4/29 初遇见哈希表，T560、T124也使用了哈希表的思想

public class T1 {
    public int[] twoSum(int[] nums, int target) {
        // 先将nums存入map: value -> index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (map.containsKey(need)) {
                return new int[]{i, map.get(need)};
            }
            map.put(nums[i], i);
        }

        return nums;
    }

    public static void main(String[] args) {

        T1 t1 = new T1();
        int[] ints = t1.twoSum(new int[]{3,3}, 6);
    }
}
