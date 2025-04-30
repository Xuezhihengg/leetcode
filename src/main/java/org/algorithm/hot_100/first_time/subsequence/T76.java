package org.algorithm.hot_100.first_time.subsequence;

import java.util.HashMap;
import java.util.Map;

//题目: 76.最小覆盖子串
//思路:双指针法：左右指针都从0出发，右指针先向右走，直到窗口内完全覆盖t，然后左指针向右移动缩小窗口，直到最小，更新min
//             然后右指针继续开始向右移动，重复上述步骤。其中一个比较关键的点在于如何判断s覆盖t，这可以通过一个need哈希表
//             实现，当然最笨的办法就是类似T438一样通过两个哈希表单词频率的覆盖来判断，时间复杂度是一样的但空间代价更大，
//             这个need的想法在这里是比较巧妙的一点。时间复杂度分析：初始化need时遍历一次t，right指针遍历一次s，每个循环
//             要判断s是否覆盖t，这跟t所包含的字符集大小有关，设为C（<26），总时间复杂度O(C*s + t)
//题解: https://leetcode.cn/problems/minimum-window-substring/solutions/258513/tong-su-qie-xiang-xi-de-miao-shu-hua-dong-chuang-k/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/30 值得重刷，思路比较重要，有了思路编码难度倒不是很大，还是双指针的代码框架

public class T76 {

    // 双指针法：左右指针都从0出发，右指针先向右走，直到窗口内完全覆盖t，然后左指针向右移动缩小窗口，直到最小，更新min
    //          然后右指针继续开始向右移动，重复上述步骤
    public String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        String result = "";

        // c(in t) -> c出现的频率
        Map<Character, Integer> need = new HashMap<>();

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char cur = s.charAt(right);
            // 将该字符从need中减去一次，如果不是需要的就不用管
            if (need.containsKey(cur)) {
                need.put(cur, need.get(cur) - 1);
            }
            // 判断是否完全覆盖t（看need中的数值是否都小于等于0）
            boolean cover = true;
            for (int v : need.values()) {
                if (v > 0) {
                    cover = false;
                    break;
                }
            }

            // 如果完全覆盖，则left开始向右走，直到遇到t中的不可减少的字符（数值为0的）
            if (cover) {
                while (!need.containsKey(s.charAt(left)) || need.get(s.charAt(left)) < 0) {
                    if (need.containsKey(s.charAt(left))) {
                        need.put(s.charAt(left), need.get(s.charAt(left)) + 1);
                    }
                    left++;
                }
                // 更新min
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    result = s.substring(left, right + 1);
                }

                // 将left右移一位，并在need中添加该需求
                need.put(s.charAt(left), need.get(s.charAt(left)) + 1);
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        T76 t76 = new T76();
        String s = t76.minWindow("a", "aa");
    }
}
