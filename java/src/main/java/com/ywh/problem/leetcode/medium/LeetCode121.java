package com.ywh.problem.leetcode.medium;

/**
 * 买卖股票的最大利润
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode121 {

    /**
     * 暴力解法
     *
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
        // 穷举所有买入点和卖出点，取最大值。
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

        // 只买卖一次，在一次遍历中求出最低买入价，当求解过程中出现高于买入价的价格即为卖出价，更新最大收入。
        int max = 0, buy = prices[0];
        for (int price: prices) {
            // 当天价格低于买入价，重置买入价。
            if (price < buy) {
                buy = price;
            }
            // 当天价格高于买入价，更新最大收入。
            else {
                max = Math.max(max, price - buy);
            }
        }
        return max;
    }
}
