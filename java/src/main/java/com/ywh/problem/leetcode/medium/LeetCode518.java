package com.ywh.problem.leetcode.medium;

/**
 * 零钱兑换 II
 * [动态规划]
 *
 * @author ywh
 * @since 2/22/2019
 */
public class LeetCode518 {

    /**
     * 求从面值数组 coins 的第 start 个面值开始取，组成 sum 的组合数量
     * （由于不能重复取相同的面值而形成相同组合，所以不能从 coins 已访问的值取、需要记录下标）
     *
     * @param coins
     * @param start
     * @param sum
     * @return
     */
    private int coinCombination(int[] coins, int start, int sum) {
        // 找到一个组合
        if (sum == 0) {
            return 1;
        }
        // 组合无效
        if (sum < 0) {
            return 0;
        }
        int ret = 0;
        for (int i = start; i < coins.length; i++) {
            ret += coinCombination(coins, i, sum - coins[i]);
        }
        return ret;
    }

    /**
     * 递归解法（指数级，不建议）
     *
     * @param sum
     * @param coins
     * @return
     */
    public int numberOfCoinCombinationRecursive(int sum, int[] coins) {
        return coinCombination(coins, 0, sum);
    }

    /**
     * 动态规划解法
     *
     * @param sum
     * @param coins
     * @return
     */
    public int numberOfCoinCombinationDP(int sum, int[] coins) {

        // d[i][j] 表示使用前 i 种面值的硬币（0 ~ i - 1）凑成数值 j 的组合数量
        int[][] d = new int[coins.length + 1][sum + 1];

        // 凑成 0 元都只有一种（不取）
        for (int i = 0; i <= coins.length; i++) {
            d[i][0] = 1;
        }

        // i - 1：第 i 个面值，j：待凑成的数值
        // 第一层循环表示逐个面值去试凑，第二层循环表示用这个面值凑成 1 ~ sum 元
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= sum; j++) {

                // 使用当前面值 i 的组合数量：要求待凑成的数值 j 大于等于第 i 个面值；否则不能使用当前面值，组合数量为 0
                // j - coins[i - 1]：使用第 i 个面值凑数，凑 j - coins[i - 1] 元的组合数
                int useCurCoin = j >= coins[i - 1] ? d[i][j - coins[i - 1]] : 0;

                // 两种情况之和：是否使用第 i 种面值组成数值 j
                d[i][j] = d[i - 1][j] + useCurCoin;

            }
        }
        return d[coins.length][sum];
    }

    /**
     * 动态规划解法（优化存储空间）
     *
     * Time: O(n*sum), Space: O(sum)
     *
     * @param sum
     * @param coins
     * @return
     */
    public int numberOfCoinCombinationDPOsum(int sum, int[] coins) {
        int[] d = new int[sum + 1];

        // d[j] 表示凑成数值 j 的组合数量（其中 dp[0] 表示凑成 0 元的方法只有 1 种，即不取）。
        d[0] = 1;

        // 尝试每个面值的硬币。
        for (int coin : coins) {
            // 使用面值为 [coin, sum] 的硬币凑 sum：
            // 比如现有 2 元硬币，凑成 10 - 2 == 8 元硬币的方法有 n 种，则凑成 10 元的硬币的方法也有 n 种。
            for (int j = coin; j <= sum; d[j] += d[j - coin], j++);
        }
        return d[sum];
    }
}
