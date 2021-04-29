package com.ywh.problem.leetcode.hard;

import com.ywh.problem.leetcode.easy.LeetCode122;
import com.ywh.problem.leetcode.medium.LeetCode121;
import com.ywh.problem.leetcode.medium.LeetCode714;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机 IV
 * [动态规划]
 * 
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1：
 *      输入：k = 2, prices = [2,4,1]
 *      输出：2
 *      解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *      输入：k = 2, prices = [3,2,6,5,0,3]
 *      输出：7
 *      解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *           随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * 提示：
 *      0 <= k <= 10^9
 *      0 <= prices.length <= 1000
 *      0 <= prices[i] <= 1000
 *
 * @author ywh
 * @since 2020/12/21/021
 */
public class LeetCode188 {

    /**
     *  {@link LeetCode121}、{@link LeetCode122}、{@link LeetCode123}、{@link LeetCode714}
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // sell[i]、buy[i] 分别表示交易 i 次时在前一天卖出/买入得到的最大收益。
        int[] sell = new int[k + 1], buy = new int[k + 1];
        Arrays.fill(buy, -prices[0]);
        // 遍历每天的价格。
        for (int price : prices) {
            // 第 i 次时买入，即从第 i-1 次卖出的最大收益减去当天价格。
            // 第 i 次时卖出，即取“不卖出”和“上次买入所得最大收益加上今天价格”的较大者。
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }
}
