package com.ywh.problem.leetcode.hard;

/**
 * 数字 1 的个数
 * [递归] [数学] [动态规划]
 *
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * 示例 1：
 *      输入：n = 13
 *      输出：6
 * 示例 2：
 *      输入：n = 0
 *      输出：0
 * 提示：
 *      0 <= n <= 10^9
 *
 * @author ywh
 * @since 9/30/2021
 */
public class LeetCode233 {
    /**
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }
}
