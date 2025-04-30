package org.algorithm.hot_100.first_time.binary_search;

//题目: 74.搜索二维矩阵

public class T74 {

    public boolean searchMatrix(int[][] matrix, int target) {

        int[] theLine = new int[0];
        // 先找到target可能所在的行
        for (int[] line : matrix) {
            int lineMax = line[line.length - 1];
            if (lineMax < target) {   // 本行全部比target小
                continue;
            }
            theLine = line;
            break;
        }

        int index = binarySearch(theLine, target);

        return index != -1;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;  // 没找到返回-1
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };

        T74 t74 = new T74();
        boolean b = t74.searchMatrix(m, 3);
    }
}
