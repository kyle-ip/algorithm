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

        // 与末尾为 0 相关的是因子 5 的个数
        int cnt = 0;
        while (n > 0) {
            n /= 5;
            cnt += n;
        }
        return cnt;
    }
}
