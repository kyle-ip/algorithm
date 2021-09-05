package com.ywh.problem.leetcode.medium;

/**
 * 数值的 n 次方
 * [数学] [递归]
 * 
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * 示例 1：
 *      输入：x = 2.00000, n = 10
 *      输出：1024.00000
 * 示例 2：
 *      输入：x = 2.10000, n = 3
 *      输出：9.26100
 * 示例 3：
 *      输入：x = 2.00000, n = -2
 *      输出：0.25000
 *      解释：2-2 = 1/22 = 1/4 = 0.25
 * 提示：
 *      -100.0 < x < 100.0
 *      -231 <= n <= 231-1
 *      -104 <= xn <= 104
 * 
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode50 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param x
     * @param n
     * @return
     */
    public double pow(double x, int n) {
        double ret = 1;
        for (int i = 0; i < Math.abs(n); i++) {
            ret *= x;
        }
        return n < 0 ? 1 / ret : ret;
    }

    /**
     * TODO 暂时未理解
     *
     * Time: O(log(n)), Space: O(1)
     *
     * @param x
     * @param n
     * @return
     */
    public double powFast(double x, int n) {
        double ret = 1;
        for (long N = Math.abs((long) n); N != 0; x *= x, N >>= 1) {
            if ((N & 1) == 1) {
                ret *= x;
            }
        }
        return n < 0 ? 1 / ret : ret;
    }

    public double myPow(double x, int n) {
        return (long) n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -(long) n);
    }

    public double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }
}
