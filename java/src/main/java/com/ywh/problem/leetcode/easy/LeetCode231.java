package com.ywh.problem.leetcode.easy;

/**
 * 2 的幂
 * [数学] [位操作]
 *
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 * 示例 1：
 *      输入：n = 1
 *      输出：true
 *      解释：20 = 1
 * 示例 2：
 *      输入：n = 16
 *      输出：true
 *      解释：24 = 16
 * 示例 3：
 *      输入：n = 3
 *      输出：false
 * 示例 4：
 *      输入：n = 4
 *      输出：true
 * 示例 5：
 *      输入：n = 5
 *      输出：false
 * 提示：
 *      -2^31 <= n <= 2^31 - 1
 *
 * 进阶：你能够不使用循环/递归解决此问题吗？
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
