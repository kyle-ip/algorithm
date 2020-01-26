package com.ywh.problem.leetcode.easy;

/**
 * 不限次数的买卖股票的最大利润
 * [数组] [贪心]
 *
 * @author ywh
 * @since 04/11/2019
 */
public class LeetCode122 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfitLocalMaxMin(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0, buyPrice, sellPrice, i = 0, n = prices.length;
        while (i < n - 1) {
            // 跳过下坡，到波谷后买入
            while (i < n - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }
            buyPrice = prices[i];

            // 跳过上坡，到波峰后卖出
            while (i < n - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            sellPrice = prices[i];

            // 记入收益
            profit += (sellPrice - buyPrice);
        }

        return profit;
    }

    /**
     * 波峰值 - 波谷值 = 中间两两插值之和
     * 因此只要把所有第二天比第一天价格高的情况计入收益即可（不限买卖次数）
     *
     * 1, 2, 4
     * 4 - 1 = (2 - 1) + (4 - 2)
     *
     * Time: O(n), Space: O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfitGreedy(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += (prices[i] - prices[i - 1]);
            }
        }
        return profit;
    }

}
