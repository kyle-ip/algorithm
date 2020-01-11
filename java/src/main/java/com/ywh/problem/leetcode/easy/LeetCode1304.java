package com.ywh.problem.leetcode.easy;

/**
 * 和为零的 N 个唯一整数
 * [数组]
 *
 * @author ywh
 * @since 11/01/2020
 */
public class LeetCode1304 {

    public int[] sumZero3(int n) {
        if (n < 1) {
            return new int[]{};
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i * 2 - n + 1;
        }
        return res;
    }

    public int[] sumZero2(int n) {
        if (n < 1) {
            return new int[]{};
        }
        int[] res = new int[n];
        for (int i = 1; i <= Math.floor(n / 2.0); i++) {
            res[i - 1] = i;
            res[n - i] = -i;
        }
        return res;
    }


    public int[] sumZero(int n) {
        if (n < 1) {
            return new int[]{};
        }
        int[] res = new int[n];
        for (int i = 1; i < n; i++) {
            res[i] = i;
        }
        res[0] = -n * (n - 1) / 2;
        return res;
    }

}
