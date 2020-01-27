package com.ywh.problem.leetcode.easy;

/**
 * 不用 +/- 求两数之和
 * [位操作]
 * TODO 暂时未理解
 *
 * @author ywh
 * @since 2/14/2019
 */
public class LeetCode371 {

    public int getSumRecursive(int a, int b) {
        return b == 0 ? a : getSumRecursive(a ^ b, (a & b) << 1);
    }

    /**
     * Time: O(m), Space: O(1)
     *
     * @param a
     * @param b
     * @return
     */
    public int getSumIterative(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }

}
