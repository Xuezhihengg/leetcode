package org.xjtu_learner.hot_100.first_time.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//题目: 827.最大人工岛
//思路: 第一轮循环先找到各个岛屿（grid[i][j]指向岛屿标号）及其面积（存在一个list中，可以通过岛屿标号找到其面积），
//      第二轮循环找到为海洋（grid[i][j]=0）且相邻不同岛屿（通过一个set记录以及访问过的岛屿）面积（通过前面的list找到对于面积）和最大的
//题解: https://leetcode.cn/problems/number-of-islands/solutions/211211/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/23 好题，T695的加强版

public class T827 {

    public int largestIsland(int[][] grid) {

        int max = 0;
        int curGroupTag = 2;
        List<Integer> areas = new ArrayList<>();

        // 第一轮循环先找到各个岛屿（grid[i][j]指向岛屿标号）及其面积（存在一个list中，可以通过岛屿标号找到其面积）
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                int dfs = dfs(grid, i, j, curGroupTag);
                if (dfs != 0) {
                    curGroupTag++;
                    areas.add(dfs);
                    max = Math.max(max, dfs);
                }
            }
        }

        // 第二轮循环找到为海洋且相邻不同岛屿面积和最大的
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) continue;
                // 找到该格子临接的不同岛屿的面积和
                int plus = 1;
                Set<Integer> visited = new HashSet<>();
                visited.add(0);
                if (inArea(grid, i - 1, j) && !visited.contains(grid[i - 1][j])) {
                    plus += areas.get(grid[i - 1][j] - 2);
                    visited.add(grid[i - 1][j]);
                }
                if (inArea(grid, i + 1, j) && !visited.contains(grid[i + 1][j])) {
                    plus += areas.get(grid[i + 1][j] - 2);
                    visited.add(grid[i + 1][j]);
                }
                if (inArea(grid, i, j - 1) && !visited.contains(grid[i][j - 1])) {
                    plus += areas.get(grid[i][j - 1] - 2);
                    visited.add(grid[i][j - 1]);
                }
                if (inArea(grid, i, j + 1) && !visited.contains(grid[i][j + 1])) {
                    plus += areas.get(grid[i][j + 1] - 2);
                    visited.add(grid[i][j+1]);
                }

                max = Math.max(max, plus);
            }
        }

        return max;
    }

    int dfs(int[][] grid, int r, int c, int groupTag) {
        if (!inArea(grid, r, c)) return 0;
        if (grid[r][c] != 1) return 0;

        grid[r][c] = groupTag;
        return 1 +
                dfs(grid, r - 1, c, groupTag) +
                dfs(grid, r + 1, c, groupTag) +
                dfs(grid, r, c - 1, groupTag) +
                dfs(grid, r, c + 1, groupTag);
    }

    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1},
                {1, 1}
        };

        T827 t827 = new T827();
        System.out.println(t827.largestIsland(grid));
    }
}
