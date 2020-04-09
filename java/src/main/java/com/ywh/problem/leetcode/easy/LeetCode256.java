package com.ywh.problem.leetcode.easy;

/**
 * 粉刷房子
 * [动态规划]
 * <p>
 * 第一维 m = 3，表示三种颜色对应的价格，第二维表示 n 个房子
 *
 * @author ywh
 * @since 09/04/2020
 */
public class LeetCode256 {
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     * d[i][0] 表示粉刷从 0 到 i 号房子，而且 i 号房子刷成颜色 0 的最小费用
     * d[i][0] = min(d[i-1][1], d[i-1][2]) + a[i][0]
     * d[i][1] = min(d[i-1][0], d[i-1][2]) + a[i][1]
     * d[i][2] = min(d[i-1][0], d[i-1][1]) + a[i][2]
     * 且 d[0][0] = a[0][0], d[0][1] = a[0][1], d[0][2] = a[0][2]
     * <p>
     * Time: O(n), Space: O(n)
     *
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] d = new int[n][3];
        d[0][0] = costs[0][0];
        d[0][1] = costs[0][1];
        d[0][2] = costs[0][2];
        for (int i = 0; i < n; i++) {
            d[i][0] = Math.min(d[i - 1][1], d[i - 1][2]) + costs[i][0];
            d[i][1] = Math.min(d[i - 1][0], d[i - 1][2]) + costs[i][1];
            d[i][2] = Math.min(d[i - 1][0], d[i - 1][1]) + costs[i][2];
        }
        return min(d[n - 1][0], d[n - 1][1], d[n - 1][2]);
    }

    /**
     * 辅助数组长度 + 1，向右错开一位，则不需要初始化
     * <p>
     * Time: O(n), Space: O(n)
     *
     * @param costs
     * @return
     */
    public int minCostOpt(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] d = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            d[i][0] = Math.min(d[i - 1][1], d[i - 1][2]) + costs[i - 1][0];
            d[i][1] = Math.min(d[i - 1][0], d[i - 1][2]) + costs[i - 1][1];
            d[i][2] = Math.min(d[i - 1][0], d[i - 1][1]) + costs[i - 1][2];
        }
        return min(d[n][0], d[n][1], d[n][2]);
    }

    /**
     * 压缩空间：二维数组（第一维长度固定） -> 固定长度的一维数组 -> 三个变量
     * <p>
     * Time: O(n), Space: O(1)
     *
     * @param costs
     * @return
     */
    public int minCostO1(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int pre0 = 0, pre1 = 0, pre2 = 0;
        for (int i = 1; i <= n; i++) {
            int cur0 = Math.min(pre1, pre2) + costs[i][0];
            int cur1 = Math.min(pre0, pre2) + costs[i][1];
            int cur2 = Math.min(pre0, pre1) + costs[i][2];
            pre0 = cur0;
            pre1 = cur1;
            pre2 = cur2;
        }
        return min(pre0, pre1, pre2);
    }
}
