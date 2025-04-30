package org.algorithm.hot_100.first_time.graph;

//题目: 200.岛屿数量

public class T200 {

    int count;

    public int numIslands(char[][] grid) {
        count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                boolean dfs = dfs(grid, i, j);
                if(dfs){
                    count++;
                }
            }
        }

        return count;
    }

    boolean dfs(char[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) return false;

        if (grid[r][c] != '1') return false;

        grid[r][c] = (char) ('2' + count);

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);

        return true;
    }

    boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }


    public static void main(String[] args) {

        char[][] grid = new char[][]{
                {'0','1','0','0'},
                {'1','1','1','0'},
                {'0','1','0','1'},
                {'0','0','1','1'},
        };

        T200 t200 = new T200();
        int i = t200.numIslands(grid);
    }
}
