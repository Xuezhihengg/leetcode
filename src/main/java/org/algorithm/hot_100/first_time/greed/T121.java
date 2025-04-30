package org.algorithm.hot_100.first_time.greed;

//题目: 121. 买卖股票的最佳时机
//思路: 使用动态规划解而不是贪心，考虑前i天的价格变化能得到的最大利润即为dp[i]，则递推公式为：
//      dp[i] = max{ dp[i-1], prices[i] - minCost }，由于只需要dp[i]这个答案，故不需要
//      dp数组，只要一个maxProfit记录目前的最大利润即可
//      当然使用贪心理解也可以，贪心的思想是「通过局部最优解得到全局最优解」，它需要满足以下两个性质：
//          - 贪心选择性质：每一步局部最优选择，最终可以推导出全局最优解
//          - 无后效性：当前的决策不会影响以后未来的决策，并不会因为未来的情况需要后悔之前的选择（比如“早知道我不在第二天买了”这种不会发生，因为你一直记录的是最优的买入价格）
//      本题买入的时机一定是前i天的最低价格，在这里贪心决策的是在哪天卖出，本题非常单纯，决定新一天是否卖出只与这一天
//      的利润是否更大，天然具有无后效性

// !*! 2025/4/27 没太理解贪心算法的核心思想，什么情况下能用，用的时候怎么写代码

public class T121 {

    public int maxProfit(int[] prices) {

        // 遍历一遍prices
        int minCost = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        for (int price : prices) {
            // 记录前i天的最低价格（即购入的最低成本）
            minCost = Math.min(minCost, price);
            maxProfit = Math.max(maxProfit, price - minCost);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};

        T121 t121 = new T121();
        int i = t121.maxProfit(nums);
    }
}
