package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode122;

/**
 * 买卖股票的最佳时机
 * [数组] [动态规划]
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 * 示例 1：
 *      输入: [7,1,5,3,6,4]
 *      输出: 5
 *      解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *           注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *      输入: [7,6,4,3,1]
 *      输出: 0
 *      解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 提示：
 *      1 <= prices.length <= 10^5
 *      0 <= prices[i] <= 10^4
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
     * 参考 {@link LeetCode714}、{@link LeetCode122}、{@link LeetCode123}、{@link LeetCode188}
     *
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
        int ret = 0, buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 当天价格低于买入价，重置买入价。
            if (prices[i] < buy) {
                buy = prices[i];
            }
            // 当天价格高于买入价，更新最大收入。
            else {
                ret = Math.max(ret, prices[i] - buy);
            }
        }
        return ret;
    }
}
