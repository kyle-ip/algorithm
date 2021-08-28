package com.ywh.problem.leetcode.medium;

/**
 * 买卖股票的最佳时机含冷冻期
 * [动态规划]
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *      输入: [1,2,3,0,2]
 *      输出: 3
 *      解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 
 * @author ywh
 * @since 2020/12/21/021
 */
public class LeetCode309 {

    /**
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        // 第一天结束有三种情况：当天买入股票、当天卖出股票、不做任何操作。
        int f0 = -prices[0], f1 = 0, f2 = 0;
        for (int i = 1; i < prices.length; i++) {
            int sell1 = f0 + prices[i], sell2 = Math.max(f1, f2);

            // 当天买入股票的最大收益：第 i 天持有股票，可能是今天刚买入（f2 - price[i]）或是前一天已经持有股票（f0），即取买或不买（之前买）的较大者。
            f0 = Math.max(f0, f2 - prices[i]);

            // 当天卖出股票的最大收益：第 i 天不持有股票且在冷冻期，即前一天还持有的股票在今天卖出（f0+prices[i]）。
            f1 = sell1;

            // 不做任何操作的最大收益：可能是前一天刚卖出、还处于冷冻期（f1）或是前一天和今天一样（f2）。
            f2 = sell2;
        }
        return Math.max(f1, f2);
    }
}
