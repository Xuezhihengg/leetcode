package org.xjtu_learner.hot_100.first_time.matrix;

//题目: 48.旋转图像
//思路: 算是二刷吧，四个一组进行旋转就可以在原地进行，这个思路是被记住了，有思路实现起来并不复杂

public class T48 {

    public void rotate(int[][] matrix) {

        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                // 四个一组进行旋转
                int temp = matrix[i][j];
                int curI = i;
                int curJ = j;
                for (int k = 0; k < 3; k++) {
                    int prevI = n - 1 - curJ;
                    int prevJ = curI;
                    matrix[curI][curJ] = matrix[prevI][prevJ];
                    curI = prevI;
                    curJ = prevJ;
                }
                matrix[curI][curJ] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        T48 t48 = new T48();
        t48.rotate(m);
    }
}
