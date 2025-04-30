package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 72.编辑距离
//思路: dp[i][j] 表示将word1前i个字符 [0,i] 转化为word2前j个字符 [0,j] 需要的最少操作数
//     递推公式: 1)如果word1[i] == word2[j]，则dp[i][j] = dp[i-1][j-1]
//              2)如果word1[i] != word2[j]，则dp[i][j] = min{dp[i-1][j-1]+1, dp[i][j-1]+1, dp[i-1][j]+1 }
//              (分别代表通过替换使最后一个字母相同、删除text2最后一个字符，删除text1最后一个字符)

public class T72 {

    public int minDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();
        // dp[i][j] 表示将word1前i个字符 [0,i] 转化为word2前j个字符 [0,j] 需要的最少操作数
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 初始化dp数组
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }

        // 递推公式: 1)如果word1[i] == word2[j]，则dp[i][j] = dp[i-1][j-1]
        //          2)如果word1[i] != word2[j]，则dp[i][j] = min{dp[i-1][j-1]+1, dp[i][j-1]+1, dp[i-1][j]+1 }
        //          (分别代表通过替换使最后一个字母相同、删除text2最后一个字符，删除text1最后一个字符)
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        T72 t72 = new T72();
        int i = t72.minDistance("intention", "execution");
    }
}
