package org.algorithm.hot_100.first_time.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//题目: 56.合并区间
//思路: 两个两个得进行合并

public class T56 {

    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        // 先将所有区间按左边界排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[] left = intervals[0];   // 记为[a,b]
        // 每次合并最左边的cur与它右边一个的区间，并将结果保存到cur
        for (int i = 1; i < intervals.length; i++) {
            int[] right = intervals[i]; // 记为[c,d]
            // 如果 a < b < c < d（也就是b < c），则两区间不相交，left保存，更新left为right
            if (left[1] < right[0]) {
                result.add(left);
                left = right;
            } else if (right[1] <= left[1]) { // 如果 a < c < d <= b（也就是d <= b），则left包含right，保持不变
                continue;
            } else {  // 如果 a < c <= b < d（也就是c <= b < d），则二区间相交，left更新为[a,d]
                left[1] = right[1];
            }
        }
        result.add(left);

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 4},
                {4, 5}
        };
        T56 t56 = new T56();
        int[][] merge = t56.merge(nums);
    }
}
