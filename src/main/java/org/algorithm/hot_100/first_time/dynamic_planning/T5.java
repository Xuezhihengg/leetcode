package org.algorithm.hot_100.first_time.dynamic_planning;

//题目: 5.最长回文子串
//思路: dp[i][j] 表示s的[i,j]字串是否为回文,
//      递推公式:  - i与j相差1 -> dp[i][j] = dp[i][j-1] && s[i] == s[j]
//                - i与j相差大于1 -> dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
//      本题需要注意两层for循环的设置，外层循环是倒序的，因为推导dp[i][j]需要dp[i+1][j-1]，所以i到倒序，j要正序

// !*! 2025/4/28 虽然自己写出来了，但是还是调试了比较久的时间，需要更加熟悉

public class T5 {

    public String longestPalindrome(String s) {

        int length = s.length();

        // dp[i][j] 表示s的[i,j]字串是否为回文
        boolean[][] dp = new boolean[length][length];

        // 初始化dp数组
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        int max = 1;
        int left = length - 1;
        int right = length;

        // 递推公式: 1) i与j相差1 -> dp[i][j] = dp[i][j-1] && s[i] == s[j]
        //          2) i与j相差大于1 -> dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if ((j - i) == 1) {
                    dp[i][j] = dp[i][j - 1] && s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        left = i;
                        right = j + 1;
                    }
                }
            }
        }

        return s.substring(left, right);
    }

    public static void main(String[] args) {
        T5 t5 = new T5();
        String s = t5.longestPalindrome("acaa");
    }
}
