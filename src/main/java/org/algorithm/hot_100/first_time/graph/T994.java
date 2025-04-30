package org.algorithm.hot_100.first_time.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//题目: 994.腐烂的橘子
//题解: https://leetcode.cn/problems/rotting-oranges/solutions/129831/li-qing-si-lu-wei-shi-yao-yong-bfsyi-ji-ru-he-xie-/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/23 自己实现的orangesRotting1不够优，对BFS的理解还不够深

public class T994 {

    //思路: 自己实现的算法，由于考虑到可能有多个橘子同时开始传播，所有要使用广度优先搜索，同时使用多个队列来实现多个污染源
    //      （实际上是不必要的，见orangesRotting2的实现，对于BFS来说，多个污染源无非就是初始化队列时多加几个而已）
    public int orangesRotting1(int[][] grid) {

        int count = 0;  //新鲜橘子的个数
        List<Queue<int[]>> all = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 先找到所有腐烂的橘子并初始化所有队列
                if (grid[i][j] == 2) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    all.add(queue);
                }
                if(grid[i][j] == 1){
                    count++;
                }
            }
        }

        // 然后对所有腐烂的橘子同时进行广度优先搜索
        int doRotting;
        int round = 0;
        while (true){
            doRotting = 0;
            for (Queue<int[]> q : all) {
                int size = q.size();
                if (size == 0) {
                    continue;
                }
                boolean hasRotting = false;
                for (int i = 0; i < size; i++) {
                    int[] cur = q.remove();
                    int r = cur[0];
                    int c = cur[1];

                    // cur将其周围四个橘子全部感染
                    if (inArea(grid, r - 1, c) && grid[r - 1][c] == 1) {
                        grid[r - 1][c] = 2;
                        count--;
                        q.add(new int[]{r - 1, c});
                        hasRotting = true;
                    }
                    if (inArea(grid, r + 1, c) && grid[r + 1][c] == 1) {
                        grid[r + 1][c] = 2;
                        count--;
                        q.add(new int[]{r + 1, c});
                        hasRotting = true;
                    }
                    if (inArea(grid, r, c - 1) && grid[r][c - 1] == 1) {
                        grid[r][c - 1] = 2;
                        count--;
                        q.add(new int[]{r, c - 1});
                        hasRotting = true;
                    }
                    if (inArea(grid, r, c + 1) && grid[r][c + 1] == 1) {
                        grid[r][c + 1] = 2;
                        count--;
                        q.add(new int[]{r, c + 1});
                        hasRotting = true;
                    }
                }
                // 只要感染了一个doRotting就可以加1
                if(hasRotting) doRotting++;
            }
            if(doRotting == 0) break;
            round++;
        }


        if(count>0) return -1;
        return round;
    }


    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }


    public int orangesRotting2(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        int count = 0; // count 表示新鲜橘子的数量
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (grid[r][c] == 1) {
                    count++;
                } else if (grid[r][c] == 2) {
                    queue.add(new int[]{r, c});
                }
            }
        }

        int round = 0; // round 表示腐烂的轮数，或者分钟数
        while (count > 0 && !queue.isEmpty()) {
            round++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] orange = queue.poll();
                int r = orange[0];
                int c = orange[1];
                if (r-1 >= 0 && grid[r-1][c] == 1) {
                    grid[r-1][c] = 2;
                    count--;
                    queue.add(new int[]{r-1, c});
                }
                if (r+1 < M && grid[r+1][c] == 1) {
                    grid[r+1][c] = 2;
                    count--;
                    queue.add(new int[]{r+1, c});
                }
                if (c-1 >= 0 && grid[r][c-1] == 1) {
                    grid[r][c-1] = 2;
                    count--;
                    queue.add(new int[]{r, c-1});
                }
                if (c+1 < N && grid[r][c+1] == 1) {
                    grid[r][c+1] = 2;
                    count--;
                    queue.add(new int[]{r, c+1});
                }
            }
        }

        if (count > 0) {
            return -1;
        } else {
            return round;
        }
    }


    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 2}
        };

        T994 t994 = new T994();
        int i = t994.orangesRotting1(grid);
    }
}
