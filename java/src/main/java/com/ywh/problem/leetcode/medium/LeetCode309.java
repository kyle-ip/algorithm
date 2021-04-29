package com.ywh.problem.leetcode.medium;

/**
 * 最佳买卖股票时机含冷冻期
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

        // 三种情况：当天结算后持有股票、不持有股票且处于冻结期、不持有股票且不处于冻结期。
        int f0 = -prices[0], f1 = 0, f2 = 0;
        for (int i = 1; i < prices.length; ++i) {
            int new0 = Math.max(f0, f2 - prices[i]), new1 = f0 + prices[i], new2 = Math.max(f1, f2);

            // 结算后手上持有股票的最大收益：第 i 天持有股票，可能是今天刚买入（f2 - price[i]）或是前一天已经持有股票（f0）。
            f0 = new0;

            // 结算后手上不持有股票，并且处于冷冻期中的累计最大收益：第 i 天不持有股票且在冷冻期，即前一天还持有的股票在今天卖出（f0+prices[i]）。
            f1 = new1;

            // 结算后手上不持有股票，并且不在冷冻期中的累计最大收益：第 i 天不持有股票且不在冷冻期，即当天没有任何操作，可能是前一天还处于冷冻期（f1）或是前一天和今天一样（f2）。
            f2 = new2;
        }
        return Math.max(f1, f2);
    }
}
