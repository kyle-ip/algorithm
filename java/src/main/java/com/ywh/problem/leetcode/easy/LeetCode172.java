package com.ywh.problem.leetcode.easy;

/**
 * 阶乘末尾 0 的个数
 * [数学]
 *
 * @author ywh
 * @since 20/11/2019
 */
public class LeetCode172 {

    /**
     * Time: O(log_5(n)), Space: O(1)
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        // 只要找出 n! 中有多少个 2 和 5 的配对（末尾 0 是由 2*5=10 贡献的）即可知道末尾 0 的个数
        // 由于每隔一个数字就有一个偶数，因此 2 出现的频率原高于 5，因此计算因子 5 的个数即可
        // 但 5 的倍数会贡献额外多个 5（比如 25 为 5*5，即贡献 2 个 5），因此 cnt += n，即 n/5 + n/25 + n/125 + ...
        int cnt = 0;
        while (n > 0) {
            n /= 5;
            cnt += n;
        }
        return cnt;
    }
}
