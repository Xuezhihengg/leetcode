package org.algorithm.hot_100.first_time.sliding_window;

import java.util.*;

//题目: 438.找到字符串中所有字母异位词
//思路: 方法就是遍历一遍s确定每一个长度为pLen的字串的开头，然后判断这个子串是不是异位词，判断是不是异位词要通过以下几种方法：
//          1）将子串排序与p比较，时间复杂度O(nmlogm)
//          2）使用hashmap保存字符频率（字符频率完全相同则为异位词），时间复杂度O(n)：外循环少不了O(n)，每一循环离开窗
//          口的频率-1，新进入窗口的字符频率+1，最后两个map比较由于字符只有26个，故为O(26)，总时间复杂度O(n(2+26))=O(n)
//          3）类似方法2），但字符频率使用26位数组表示，时间代价更小

// !*! 2025/4/30 仍然没明白说明是滑动窗口，本题给的比较大的启示就是异位词的判断方法，特别是「字符频率完全相同则为异位词」


public class T438 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        int sLen = s.length();
        int pLen = p.length();

        if (sLen < pLen) {
            return result;
        }

        int[] sFrequency = new int[26]; // s指定窗口中26个字母出现的频率，频率一样说明是异位词
        int[] pFrequency = new int[26];

        // 初始化频率
        for (int i = 0; i < pLen; i++) {
            sFrequency[s.charAt(i) - 'a']++;
            pFrequency[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(sFrequency, pFrequency)) {
            result.add(0);
        }

        for (int i = 0; i < sLen - pLen; i++) {
            sFrequency[s.charAt(i) - 'a']--;
            sFrequency[s.charAt(i + pLen) - 'a']++;

            if (Arrays.equals(sFrequency, pFrequency)) {
                result.add(i + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        T438 t438 = new T438();
        List<Integer> anagrams = t438.findAnagrams("cbaebabacd", "abc");
    }
}
