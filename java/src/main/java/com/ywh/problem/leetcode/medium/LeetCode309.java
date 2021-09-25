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

        // 第一天结束有三种情况：当天买入、当天卖出、当天不操作。
        int buy = -prices[0], sell = 0, freeze = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp1 = buy + prices[i], tmp2 = Math.max(sell, freeze);

            // 今天买入：之前为冷冻期的最大收益 - 今天股价（表示冷冻期过后才允许再买入）
            buy = Math.max(buy, freeze - prices[i]);

            // 今天卖出：买入后最大收益 + 今天股价。
            sell = tmp1;

            // 不操作：对比两个旧状态来更新，之前已卖出，或之前是冷冻期。
            freeze = tmp2;
        }
        // 最后必然是没有持有股票，可能是刚卖出，也可能是之前就卖出、现在冷冻。
        return Math.max(sell, freeze);
    }
}
