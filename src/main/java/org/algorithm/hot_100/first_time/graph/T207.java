package org.algorithm.hot_100.first_time.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//问题: 207.课程表
//思路: 拓扑排序问题，即将一个有向无环图转化为一个线性序列，通过BFS解决，每次将入度为0的课程入度，然后将它们的后驱
//      节点的入度都减1
//题解: https://leetcode.cn/problems/course-schedule/solutions/250377/bao-mu-shi-ti-jie-shou-ba-shou-da-tong-tuo-bu-pai-/?envType=study-plan-v2&envId=top-100-liked

// !*! 2025/4/24 经典的拓扑排序问题，思路已经全忘了，值得重刷


public class T207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 入度数组
        int[] inDegree = new int[numCourses];

        // 邻接表存储依赖关系  (课程 -> 它的直接后驱课程列表)
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // 初始化入度数组与邻接表
        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
            adjList.get(p[1]).add(p[0]);
        }

        // BFS：每次让入度为0的课程入队
        Queue<Integer> q = new LinkedList<>();
        // 初始化q
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int count = numCourses;
        while (!q.isEmpty()) {
            // 入度为0的课程出队表示被选
            Integer c = q.remove();
            count--;
            // 将其后驱课程的入度减1
            for (int ac : adjList.get(c)) {
                inDegree[ac]--;
                if (inDegree[ac] == 0) {
                    q.add(ac);
                }
            }

        }

        return count == 0;
    }

    public static void main(String[] args) {
        int[][] cs = new int[][]{
                {1, 0},
                {0, 1}
        };

        T207 t207 = new T207();
        boolean b = t207.canFinish(2, cs);
    }
}
