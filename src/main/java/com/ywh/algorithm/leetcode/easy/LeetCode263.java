package com.ywh.algorithm.leetcode.easy;

/**
 * 丑数
 * [数学]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode263 {

    public boolean isUglyNumber(int num) {
        if (num < 0) {
            return false;
        }
        for(int n: new int[]{2, 3, 5}) {
            while (num % n == 0) {
                num /= n;
            }
        }
        return num == 1;
    }

}
