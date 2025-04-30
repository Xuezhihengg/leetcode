package org.algorithm.hot_100.first_time.graph;

//题目: 463.岛屿的周长

public class T463 {

    int count;

    public int islandPerimeter(int[][] grid) {
        count = 0;
        boolean flag = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    flag = true;
                }
                if(flag) return count;
            }
        }

        return count;
    }

    void dfs(int[][] grid, int r, int c) {

        if (!inArea(grid, r, c) || grid[r][c] == 0) {
            count++;
            return;
        }

        if (grid[r][c] == 2) {
            return;
        }

        grid[r][c] = 2;

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }

    public static void main(String[] args) {

        int[][] grid = new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };

        T463 t463 = new T463();
        int i = t463.islandPerimeter(grid);
    }

}
