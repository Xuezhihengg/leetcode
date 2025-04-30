package org.xjtu_learner.hot_100.first_time.backtracking;

import java.util.ArrayList;
import java.util.List;

//题目: 51.N皇后
//思路: 子集树问题，pos[i]代表第i列的皇后所在的行号，通过合适的剪枝函数实现题目要求，本题需要注意理解题目限制
//      ‘不在同一斜线’不仅仅是紧挨的俩皇后不能在同一斜线，而是所有皇后都不能在同一斜线（不然就会被攻击）

public class T51 {

    //思路: 通过子集树
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new ArrayList<>();
        int[] pos = new int[n];

        dfs(pos, 0, result);

        return result;
    }

    void dfs(int[] pos, int index, List<List<String>> result) {
        if (index == pos.length) {
            // output
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < pos.length; i++) {
                StringBuilder builder = new StringBuilder();
                for (int p : pos) {
                    if (p == i) {
                        builder.append('Q');
                    }else {
                        builder.append('.');
                    }
                }
                temp.add(builder.toString());
            }
            result.add(temp);
            return;
        }

        // 一维数组pos已经保证了皇后不可能在列上冲突
        for (int i = 0; i < pos.length; i++) {
            // 剪枝
            if (!prune(pos, index, i)) {
                continue;
            }
            pos[index] = i;
            dfs(pos, index + 1, result);
        }
    }

    boolean prune(int[] pos, int index, int curPos) {
        for (int i = 0; i < index; i++) {
            // 同列 或 同对角线（主/副）
            if (pos[i] == curPos || Math.abs(index - i) == Math.abs(curPos - pos[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        T51 t51 = new T51();
        List<List<String>> lists = t51.solveNQueens(4);
    }
}



