package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 零钱兑换
 * [动态规划]
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 示例 1：
 *      输入：coins = [1, 2, 5], amount = 11
 *      输出：3
 *      解释：11 = 5 + 5 + 1
 * 示例 2：
 *      输入：coins = [2], amount = 3
 *      输出：-1
 * 示例 3：
 *      输入：coins = [1], amount = 0
 *      输出：0
 * 示例 4：
 *      输入：coins = [1], amount = 1
 *      输出：1
 * 示例 5：
 *      输入：coins = [1], amount = 2
 *      输出：2
 * 提示：
 *      1 <= coins.length <= 12
 *      1 <= coins[i] <= 2^31 - 1
 *      0 <= amount <= 10^4
 *
 * @author ywh
 * @since 11/11/2019
 */
public class LeetCode322 {

    /**
     * DP 状态数组设计参考 {@link LeetCode518}
     *
     * Time: O(n*amount), Space: O(n*amount)
     *
     * @param coins
     * @param amount
     * @return
     */
    public int minCoinCombination(int[] coins, int amount) {

        // d[i][j] 表示使用前 i 种面值的硬币（0 ~ i - 1）凑成数值 j 的最小硬币数
        int[][] d = new int[coins.length + 1][amount + 1];

        // 把凑成各种面值的最小硬币数初始化成最大值，表示默认凑不齐给定值
        // 尽管题目要求凑不成时返回 - 1，但负数不利于大小判断，所以设置成一个不影响判断的值、最后返回再特殊处理
        for (int j = 0; j <= amount; j++) {
            d[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                // 使用当前面值凑 i 元所用币数
                int useCurCoin = j >= coins[i - 1] ? d[i][j - coins[i - 1]] : Integer.MAX_VALUE;
                if (useCurCoin != Integer.MAX_VALUE) {
                    useCurCoin += 1;
                }
                // 与使用上一种面值凑 j 元对比，取较小者
                d[i][j] = Math.min(d[i - 1][j], useCurCoin);
            }
        }

        return d[coins.length][amount] == Integer.MAX_VALUE ? -1 : d[coins.length][amount];
    }

    /**
     * Time: O(n*amount), Space: O(amount)
     *
     * @param coins
     * @param amount
     * @return
     */
    public int minCoinCombinationOsum(int[] coins, int amount) {
        // dp[i] 表示凑成 i 元的最小硬币数，其中 dp[0] == 0 表示凑成 0 元的最小硬币数为 0，其余先则初始化为 Integer.MAX_VALUE - 1。
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, amount + 1, Integer.MAX_VALUE - 1);
        for (int coin : coins) {
            // 遍历每种面值的硬币，用来凑 [coin, amount] 元。
            // 从当前硬币面值开始凑（假设现有 5 元面值，至少凑 5 元及以上才有意义），使用面值为 coin 的硬币凑 sum：
            // 比如要用 2 元硬币凑 10 元，如果之前已经把凑成 10 - 2 == 8 元的最小硬币数算好，存在两种可能：
            //      1. 使用这个硬币，加上之前凑的 8 元，刚好 10 元（硬币数 + 1）。
            //      2. 不使用这个硬币，通过其他方法凑成的 10 元。
            // 取其较小者即可。
            for (int j = coin; j <= amount; dp[j] = Math.min(dp[j], dp[j - coin] + 1), j++);
        }
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }
}