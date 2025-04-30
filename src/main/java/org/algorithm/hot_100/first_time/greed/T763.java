package org.xjtu_learner.hot_100.first_time.greed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//题目: 763.划分字母区间
//思路: 和T45很相似，使用farthest记录本次切割至少要到达的位置（考虑已出现的字符），遍历s如果到达farthest则执行切割，
//      这样的切割是一个局部最优解。这个大问题拆分成多个子问题，每个子问题就是这一刀怎么切，贪心的切法就是直接切其中
//      已经出现字符的最远位置处

// !*! 2025/4/27 不会，看了题解才会

public class T763 {

    public List<Integer> partitionLabels(String s) {

        // 统计每个字符最后出现的位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        int farthest = 0;   // farthest保持本次切割至少要到达的位置（考虑已出现的字符）
        // 遍历s，达到farthest则进行一次切割（说明farthest以内的字符没有在别处再出现过）
        List<Integer> result = new ArrayList<>();

        int last = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            farthest = Math.max(farthest, map.get(c));
            if (i == farthest) {
                result.add(farthest + 1 - last);
                last = farthest + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        T763 t763 = new T763();
        List<Integer> r = t763.partitionLabels("ababcbacadefegdehijhklij");
    }
}
