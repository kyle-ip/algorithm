package com.ywh.algorithm.leetcode.easy;

/**
 * 买卖股票的最大利润
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode121 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfitBruteForce(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }


    /**
     * Time: O(n), Space: O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfitOnePass(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int max = 0, buy = prices[0];
        for (int price: prices) {

            // 当天价格低于买入价，重置买入价；当天价格高于或等于买入价，最大收入取当前最大收入与当天价格和买入价差值的较大者
            if (price < buy) {
                buy = price;
            } else {
                max = Math.max(max, price - buy);
            }
        }
        return max;
    }
}
