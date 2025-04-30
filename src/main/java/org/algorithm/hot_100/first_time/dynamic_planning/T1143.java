package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 1143.最长公共子序列
//思路: dp[i][j] 表示text1前i个字符 [0,i] 和text2前j个字符 [0,j] 的最长公共子序列长度
//      递推公式: 1)如果 text1[i] == text2[j] 则dp[i][j] = dp[i-1][j-1] + 1;
//               2)如果 text1[i] != text2[j] 则dp[i][j] = max{ dp[i-1][j], dp[i][j-1] }

public class T1143 {

    public int longestCommonSubsequence(String text1, String text2) {

        int len1 = Math.min(text1.length(), text2.length());
        int len2 = Math.max(text1.length(), text2.length());

        // 使text1为较短的，text2为较长的
        if(text1.length() > text2.length()){
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        // dp[i][j] 表示text1前i个字符 [0,i] 和text2前j个字符 [0,j] 的最长公共子序列长度
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 递推公式: 1)如果 text1[i] == text2[j] 则dp[i][j] = dp[i-1][j-1] + 1;
        //          2)如果 text1[i] != text2[j] 则dp[i][j] = max{ dp[i-1][j], dp[i][j-1] }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        T1143 t1143 = new T1143();
        int i = t1143.longestCommonSubsequence("abcde", "ace");
    }
}
