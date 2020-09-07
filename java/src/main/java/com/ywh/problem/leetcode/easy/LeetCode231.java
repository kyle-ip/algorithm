package com.ywh.problem.leetcode.easy;

/**
 * 2 的幂
 * [数学] [位操作]
 *
 * @author ywh
 * @since 2019/12/6/006
 */
public class LeetCode231 {

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwoBruteForce(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }

    /**
     * Time: O(1), Space: O(1)
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwoBit(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
