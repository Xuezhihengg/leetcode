package org.xjtu_learner.hot_100.first_time.graph;

//题目: 695. 岛屿的最大面积
//思路: 网格背景下的dfs，需要理解基本情况以及注意避免重复搜索，这里的dfs返回值并不好解释，更多是一种网格搜索的效果
//题解: https://leetcode.cn/problems/number-of-islands/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/23 初次遇见岛屿问题，本质是二叉树dfs的网格版本

public class T695 {

    public int maxAreaOfIsland(int[][] grid) {

        // 尝试从所有位置开始搜索，并记录最大值
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int dfs = dfs(grid, i, j);
                max = Math.max(max, dfs);
            }
        }

        return max;
    }

    // 递归返回值含义为以当前格子为起点的连通陆地区域的面积（格子数），同时避免了重复搜索
    int dfs(int[][] grid, int r, int c) {

        if (!inArea(grid, r, c)) return 0;

        // 如果是海洋或已被搜索则结束搜索
        if (grid[r][c] == 0 || grid[r][c] == 2) return 0;

        // 如果是陆地则递归搜索，并标记已搜索
        grid[r][c] = 2;
        return 1 +
                dfs(grid, r + 1, c) +
                dfs(grid, r - 1, c) +
                dfs(grid, r, c + 1) +
                dfs(grid, r, c - 1);
    }

    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }
}
