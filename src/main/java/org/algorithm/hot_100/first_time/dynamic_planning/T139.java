package org.xjtu_learner.hot_100.first_time.dynamic_planning;

import java.util.ArrayList;
import java.util.List;

//题目: 139.单词拆分
//思路: dp[i] 表示能否使用wordDict中的单词构成s的前i部分，即[0,i]，递推公式为：
//      dp[i] = (dp[0] && contains(s.substring(0, i)) || (dp[1] && contains(s.substring(1, i)) || ...
//      本题不能使用二维dp数组
//题解: https://leetcode.cn/problems/word-break/solutions/302471/dan-ci-chai-fen-by-leetcode-solution/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/27 这里的动态规划不好想到

public class T139 {

    public boolean wordBreak(String s, List<String> wordDict) {

        int length = s.length();
        // dp[i] 表示能否使用wordDict中的单词构成s的前i部分，即[0,i]
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }

        return dp[length];
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pen");
        T139 t139 = new T139();
        boolean b = t139.wordBreak("applepenapple", list);
    }


}
