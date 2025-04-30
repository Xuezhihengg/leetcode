package org.algorithm.hot_100.first_time.matrix;

import java.util.ArrayList;
import java.util.List;

//题目: 54.螺旋矩阵
//思路: 这道题算是二刷吧，通过四个指针left、right、up、bottom会让代码逻辑简单很多，记住就行

public class T54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length;
        int up = 0;
        int bottom = matrix.length;

        List<Integer> result = new ArrayList<>();
        while (left < right && up < bottom) {
            // 遍历up这一行（从left到right）
            for (int i = left; i < right; i++) {
                result.add(matrix[up][i]);
            }
            up++;
            if(up == bottom) break;

            // 遍历right这一列（从up到bottom）
            for (int i = up; i < bottom; i++) {
                result.add(matrix[i][right-1]);
            }
            right--;
            if(left == right) break;

            // 遍历bottom这一行（从right到left）
            for (int i = right - 1; i >= left; i--) {
                result.add(matrix[bottom-1][i]);
            }
            bottom--;
            if(up == bottom) break;

            // 遍历left这一列（从bottom到up）
            for (int i = bottom - 1; i >= up; i--) {
                result.add(matrix[i][left]);
            }
            left++;
            if(left == right) break;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        T54 t54 = new T54();
        List<Integer> integers = t54.spiralOrder(m);
    }
}
