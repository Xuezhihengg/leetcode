package org.xjtu_learner.hot_100.first_time.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//题目: 131.分割回文串
//思路: 是一个子集树，n个字符中间n-1处是否分割，即使加入了记忆map，这个算法虽然不会超时，但是时间表现不佳，
//      有一种优化的思路是提前使用动态规划将子串是否为回文存储，这样在递归过程中就不需要再去判断

// !*! 2025/4/24 自己写的没问题，但是时间表现不佳，使用动态规划预处理提升时间性能

public class T131 {

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();
        // 记忆map，存储已经回文判断过的字符串的结果
        Map<String, Boolean> map = new HashMap<>();

        int n = s.length();
        int[] attend = new int[n - 1];

        dfs(s, attend, 0, result, map);

        return result;
    }

    void dfs(String s, int[] attend, int index, List<List<String>> result, Map<String, Boolean> map) {

        if (index == attend.length) {
            // output
            List<String> subs = new ArrayList<>();
            int start = 0;
            int end = 0;
            for (int j : attend) {
                end++;
                if (j == 1) {
                    subs.add(s.substring(start, end));
                    start = end;
                }
            }
            subs.add(s.substring(start, end + 1));

            // 检查sub中的字串是否都是回文的
            for (String sub : subs) {
                Boolean isReply = map.get(sub);
                if (isReply == null) {
                    isReply = isReply(sub);
                    map.put(sub, isReply);
                }

                if (!isReply) {
                    return;
                }
            }
            // 到这里说明全是回文的，保存结果
            result.add(subs);

            return;
        }

        for (int i = 0; i < 2; i++) {
            attend[index] = i;
            dfs(s, attend, index + 1, result, map);
        }
    }

    boolean isReply(String s) {
        return s.contentEquals(new StringBuilder(s).reverse());
    }

    public static void main(String[] args) {

        T131 t131 = new T131();
        List<List<String>> result = t131.partition("aab");
    }
}
