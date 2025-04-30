package org.algorithm.interview.huawei;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Order {
    long src;
    long dst;
    long profit;

    public Order(long src, long dst, long profit) {
        this.src = src;
        this.dst = dst;
        this.profit = profit;
    }
}

class Result {
    long profit;
    List<Long> path;

    public Result(long profit, List<Long> path) {
        this.profit = profit;
        this.path = path;
    }
}
// We have imported the necessary tool classes.
// If you need to import additional packages or classes, please import here.

public class HW3 {
    public static Result calcTotalProfit(long startLocation, long time, List<Order> orders) {

        dfs(startLocation, time, orders);

        return new Result(0L, new ArrayList<>());
    }

    static void dfs(long startLocation, long time, List<Order> orders) {

        // 基本情况



    }

    public static void main(String[] args) {
        // please define the JAVA input here. For example: Scanner s = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        // Input handling
        long startSite = scanner.nextLong();
        long duration = scanner.nextLong();

        long m = scanner.nextLong();

        List<Order> orders = new ArrayList<>();
        for (long i = 0; i < m; i++) {
            long src = scanner.nextLong();
            long dst = scanner.nextLong();
            long profit = scanner.nextLong();
            orders.add(new Order(src, dst, profit));
        }

        Result result = calcTotalProfit(startSite, duration, orders);
        // please define the JAVA output here. For example: System.out.println(s.nextInt());
        long totalProfit = result.profit;
        List<Long> path = result.path;
        System.out.println(totalProfit);
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(path.get(i));
        }
        System.out.println();

        scanner.close();

    }
}



