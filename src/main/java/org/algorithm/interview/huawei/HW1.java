package org.algorithm.interview.huawei;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HW1 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        int target = scanner.nextInt();
        int k =scanner.nextInt();

        // 初始化二维数组
        int[][] pic = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pic[i][j] = scanner.nextInt();
            }
        }

        // 找到中心点的坐标
        int midW = (w - 1) / 2;
        int minH = (h - 1) / 2;

        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> {
            // 如果距离不同则按距离远近排序
            if (a.dist != b.dist) return Integer.compare(a.dist, b.dist);
            // 如果x不同则按x排序
            if (a.x != b.x) return Integer.compare(a.x, b.x);
            // 如果x相同则按y排序
            return Integer.compare(a.y, b.y);
        });

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                // 如果找到target
                if (pic[y][x] == target) {
                    int dist = Math.abs(x - midW) + Math.abs(y - minH);
                    pq.offer(new Point(x, y, dist));
                }
            }
        }

        // 输出结果
        int count = 0;
        while (count < k && !pq.isEmpty()) {
            Point p = pq.poll();
            System.out.print(p.x + " " + p.y);
            if (count != k-1) {
                System.out.print(" ");
            }
            count++;
        }
    }

    static class Point {
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}


//5 5
//10
//3
//10 2 3 4 5
//1 2 3 4 10
//1 2 3 10 5
//1 10 3 4 5
//1 2 3 4 5

