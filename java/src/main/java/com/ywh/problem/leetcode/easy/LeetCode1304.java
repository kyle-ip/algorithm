package com.ywh.problem.leetcode.easy;

/**
 * 和为零的 N 个唯一整数
 * [数组]
 *
 * @author ywh
 * @since 11/01/2020
 */
public class LeetCode1304 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int[] sumZero3(int n) {
        if (n < 1) {
            return new int[]{};
        }
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = i * 2 - n + 1;
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int[] sumZero2(int n) {
        if (n < 1) {
            return new int[]{};
        }
        int[] ret = new int[n];
        for (int i = 1; i <= Math.floor(n / 2.0); i++) {
            ret[i - 1] = i;
            ret[n - i] = -i;
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param n
     * @return
     */
    public int[] sumZero(int n) {
        if (n < 1) {
            return new int[]{};
        }
        int[] ret = new int[n];
        for (int i = 1; i < n; i++) {
            ret[i] = i;
        }
        ret[0] = -n * (n - 1) / 2;
        return ret;
    }

}
