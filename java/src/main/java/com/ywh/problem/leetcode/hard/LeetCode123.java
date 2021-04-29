package com.ywh.problem.leetcode.hard;

import com.ywh.problem.leetcode.easy.LeetCode122;
import com.ywh.problem.leetcode.medium.LeetCode121;
import com.ywh.problem.leetcode.medium.LeetCode714;

/**
 * 买卖股票的最佳时机 III
 * [数组] [动态规划]
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 *      输入: [3,3,5,0,0,3,1,4]
 *      输出: 6
 *      解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *           随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 *      输入: [1,2,3,4,5]
 *      输出: 4
 *      解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *           注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *           因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *      输入: [7,6,4,3,1]
 *      输出: 0
 *      解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 示例 4：
 *      输入：prices = [1]
 *      输出：0
 * 提示：
 *      1 <= prices.length <= 10^5
 *      0 <= prices[i] <= 10^5
 *
 * @author ywh
 * @since 2020/12/21/021
 */
public class LeetCode123 {

    /**
     * 参考 {@link LeetCode121}、{@link LeetCode122}、{@link LeetCode188}、{@link LeetCode714}
     *
     * Time: O(n), Space: O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // 定义在 第 i 天时达到 4 种状态下的最大收益：
        // 第一次买入（初始值为 -prices[0]，可理解为只付出了买入的价、没有收益）、第一次卖出、第二次买入、第二次卖出。
        int buy1 = -prices[0], sell1 = 0, buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, - prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
