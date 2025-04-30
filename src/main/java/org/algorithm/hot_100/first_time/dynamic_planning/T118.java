package org.xjtu_learner.hot_100.first_time.dynamic_planning;

import java.util.ArrayList;
import java.util.List;

//题目: 118. 杨辉三角

public class T118 {

    public List<List<Integer>> generate(int numRows) {

        int[][] dp = new int[numRows][numRows];

        // 初始化dp数组
        for (int i = 0; i < numRows; i++) {
            dp[i][0] = 1;
            dp[0][i] = 1;
        }

        // 递推公式：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < numRows; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 构造结果
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            result.add(new ArrayList<>());
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numRows; j++) {
                if ((i + j) < numRows) {
                    result.get(i + j).add(dp[i][j]);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        T118 t118 = new T118();
        List<List<Integer>> generate = t118.generate(5);
    }
}
