package org.xjtu_learner.hot_100.first_time.hash;

import java.util.HashSet;
import java.util.Set;

//题目: 128.最长连续序列
//思路: 先将所有数存入hashset，然后遍历所有数组（这里遍历set而不是nums，是因为set去重了，需要遍历的元素更少且不会影响结果）
//      对于每个数通过set向后找，直到找到以该数开头的最长连续序列的结尾，由于hash查找的平均时间复杂度是O(1)，所以这样的平均
//      时间复杂为O(n)，但是最坏情况下还是O(n^2)

// !*! 2025/4/29 通过这道题才慢慢理解哈希表的一个作用，以时间换空间，减少查找时间

public class T128 {

    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();
        // 先将nums中的数全部放入set
        for (int n : nums) {
            set.add(n);
        }

        int max = 0;
        // 遍历一遍set，对于每个数，通过set不断向后找，找到以该数开头的最长连续序列
        for (int n : set) {    // 这里的遍历应该通过set而不是nums，因为set实现了去重，如果用nums会超时
            // 如果n有前驱数字，那就跳过，避免不必要的枚举
            if (set.contains(n - 1)) continue;
            int count = 1;
            int next = n + 1;
            while (set.contains(next)) {
                count++;
                next++;
            }
            max = Math.max(max, count);
        }

        return max;
    }

    public static void main(String[] args) {
        T128 t128 = new T128();
        int i = t128.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
    }
}
