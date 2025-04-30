package org.algorithm.hot_100.first_time.matrix;

//题目: 73.矩阵置零
//思路: 最笨的方法就是使用两个数组分别记录含0的行和含0的列，然后把它们都置为0，这需要遍历两遍矩阵O(mn)，空间复杂度为O(m+n)
//      为了原地进行操作，可以用矩阵的第一行和第一列来代替上述的两个数组，但是这样的问题是第一行和第一列是否含0就不知道了，
//      所以需要两个额外标志位来记录
//题解: https://leetcode.cn/problems/set-matrix-zeroes/solutions/669901/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/30 感觉很屎山，随随便便就在修改matrix，把matrix的空间占有并使用

import java.util.Arrays;

public class T73 {

    public void setZeroes(int[][] matrix) {

        boolean firstRow = false;
        boolean firstCol = false;
        // 遍历第一列
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }

        // 遍历第一行
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }

        // 从（1，1）开始遍历
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    // 第一行标记列是否有0
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // 开始置0
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {  // 这一行全部置为0
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {  // 这一列全部置为0
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if(firstCol){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if(firstRow){
            Arrays.fill(matrix[0], 0);
        }
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{
                {3, 1, 2, 0},
                {0, 4, 0, 2},
                {1, 3, 1, 5}
        };
        T73 t73 = new T73();
        t73.setZeroes(m);
    }
}
