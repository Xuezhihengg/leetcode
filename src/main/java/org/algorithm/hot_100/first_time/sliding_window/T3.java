package org.xjtu_learner.hot_100.first_time.sliding_window;

import java.util.HashMap;
import java.util.Map;

//题目: 3.无重复字符的最长子串
//思路: 本题的笨方法就是两层循环，时间复杂度为O(n^2)，但是呢双循环中有些遍历是重复的，比如(abcd)abcd的下一循环
//      a(bcda)bcd，其中的bcd无重复在上一以循环已经检验过了，只需要检验新来的b在上次重复之后（a之后）有无重复即可
//      于是有了本题的双指针解法（题解说是滑动窗口），但个人觉得其的本质就是双指针，且左右指针都从左边出发，符合T283
//      中所总结的双指针框架之一，现在，right指针循环一遍s，left指针因为在跳跃更新，也只循环一遍s，总的时间复杂度为O(n)

public class T3 {
    public int lengthOfLongestSubstring(String s) {

        char[] chars = s.toCharArray();
        // c -> c出现的位置 + 1
        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        int left = 0;
        for (int right = 0; right < chars.length; right++) {
            char cur = chars[right];
            // 判断这个右指针元素是否在前面出现过
            if (map.containsKey(cur) && map.get(cur) >= left) {
                left = map.get(cur) + 1;
            }
            map.put(cur, right);
            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        T3 t3 = new T3();
        int i = t3.lengthOfLongestSubstring("abba");
    }
}
