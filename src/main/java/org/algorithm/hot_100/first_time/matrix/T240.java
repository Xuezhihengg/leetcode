package org.xjtu_learner.hot_100.first_time.matrix;

//题目: 240.搜索二维矩阵2
//思路: 模拟二叉树，利用从左向右递增，从上向下递增的特性
//题解: https://leetcode.cn/problems/search-a-2d-matrix-ii/solutions/2361487/240-sou-suo-er-wei-ju-zhen-iitan-xin-qin-7mtf/?envType=study-plan-v2&envId=top-100-liked

public class T240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int curI = matrix.length - 1;
        int curJ = 0;
        return solve(curI, curJ, target, matrix);
    }

    private boolean solve(int curI, int curJ, int target, int[][] matrix) {
        int flag = matrix[curI][curJ];
        if (flag == target) {
            return true;
        }

        // 删除本行
        if (flag > target) {
            if(curI == 0) return false;
            return solve(curI - 1, curJ, target, matrix);
        } else { // 删除本列
            if(curJ == matrix[0].length - 1) return false;
            return solve(curI, curJ + 1, target, matrix);
        }
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int target = 20;
        T240 t240 = new T240();
        System.out.println(t240.searchMatrix(test, target));
    }
}
