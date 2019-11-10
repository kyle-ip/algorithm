package com.ywh.problem.leetcode.medium;

/**
 * 硬币面值组合问题
 * [动态规划]
 *
 * TODO 暂时未理解
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
        int result = 0;
        for (int i = start; i < coins.length; i++) {
            result += coinCombination(coins, i, sum - coins[i]);
        }
        return result;
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

        // i - 1：当前面值，j：待凑成的数值
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= sum; j++) {

                // 使用当前面值 i 的组合数量：要求待凑成的数值 j 大于等于第 i - 1 个面值，否则不能使用当前面值，组合数量为 0
                // j - coins[i - 1]：使用第 i - 1 个面值凑数，待凑数值 - coins[i - 1]
                int useCurCoin = j >= coins[i - 1] ? d[i][j - coins[i - 1]] : 0;

                // 两种情况之和：是否使用第 i 种面值组成数值 j
                d[i][j] = d[i - 1][j] + useCurCoin;

            }
        }
        return d[coins.length][sum];
    }

    /**
     * 动态规划解法（优化存储空间）
     * <p>
     * Time: O(n*sum), Space: O(sum)
     *
     * @param sum
     * @param coins
     * @return
     */
    public int numberOfCoinCombinationDPOsum(int sum, int[] coins) {
        int[] d = new int[sum + 1];

        // d[j] 表示凑成数值 j 的组合数量
        d[0] = 1;

        // 逐个面值尝试：i - 1 表示当前面值，即第 i 个面值
        for (int i = 1; i <= coins.length; i++) {

            // 凑成 1 ~ sum 的数值
            for (int j = 1; j <= sum; j++) {
                int useCurCoin = j >= coins[i - 1] ? d[j - coins[i - 1]] : 0;

                // 两种情况：使用或不使用 coins[i - 1] 凑成 j
                d[j] = d[j] + useCurCoin;
            }
        }
        return d[sum];
    }
}
