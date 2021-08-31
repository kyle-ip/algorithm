package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 不同路径
 * [数组] [动态规划]
 * 
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * 示例 1：
 *      输入：m = 3, n = 7
 *      输出：28
 * 示例 2：
 *      输入：m = 3, n = 2
 *      输出：3
 *      解释：
 *          从左上角开始，总共有 3 条路径可以到达右下角。
 *          1. 向右 -> 向右 -> 向下
 *          2. 向右 -> 向下 -> 向右
 *          3. 向下 -> 向右 -> 向右
 * 示例 3：
 *      输入：m = 7, n = 3
 *      输出：28
 * 示例 4：
 *      输入：m = 3, n = 3
 *      输出：6
 * 提示：
 *      1 <= m, n <= 100
 *      题目数据保证答案小于等于 2 * 10^9
 *
 * @author ywh
 * @since 05/11/2019
 */
public class LeetCode62 {

    /**
     * 动态规划
     *
     * Time: O(m*n), Space: O(m*n)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[j][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 动态规划（空间优化）
     *
     * Time: O(m*n), Space: O(n)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsDP2(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // [1] [2] <--- 更新前的 dp[j]，表示从到达 [2] 的路径数。
                // [3] [4] <--- 更新后的 dp[j]，两者相加表示到达 [4] 的路径数（意思是从上边或左边到达都可以）。
                //  ↑
                //  +---------- 更新后的 dp[j - 1]，表示从到达 [3] 的路径数。
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    /**
     * 组合数学：
     * 在 m * n 的矩阵中从左上角走到右下角，都必须向下走 m-1 步、向右走 n-1 步，总步数 m+n-2；
     * 可以从总步数 m+n-1 中任意选 m-1 步向下走，剩下向右走、或者任意选 n-1 步向下走，剩下向右走；
     * 即 C(m-1, m+n-2) == C(n-1, m+n-2)
     * 其中组合数公式：C(b, a) == a!/(b!*(a-b)!) == a*(a-1)*...* (a-b+1)/b!
     *
     * Time: O(min(m, n)), Space: O(1)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsMath(int m, int n) {
        int small = Math.max(m - 1, n - 1), total = m + n - 2;

        // 避免乘法溢出
        long ret = 1;

        // C(b, a) == a/1 * (a-1)/2 * ... (a-b+1)/b
        for (int i = 0; i < small; i++) {
            ret = ret * (total - i) / (i + 1);
        }
        return (int) ret;
    }

}
