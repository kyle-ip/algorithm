package com.ywh.problem.leetcode.easy;

/**
 * 回文数
 * [数学] [双指针]
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1：
 *      输入: 121
 *      输出: true
 * 示例 2：
 *      输入: -121
 *      输出: false
 *      解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 *      输入: 10
 *      输出: false
 *      解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶：
 *      你能不将整数转为字符串来解决这个问题吗？
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode9 {

    /**
     * 转换成字符串，再用双指针法求解。
     * Time: O(m), Space: O(1)
     *
     * @param x
     * @return
     */
    public boolean isPalindromeString(int x) {
        if (x < 0) {
            return  false;
        }
        String s = String.valueOf(x);
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 用取模的方法从低位向高位逐位取数、并从高位到低位逐位编排成新数，最终返回对比结果即可。
     * Time: O(m), Space: O(1)
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 负数都不是回文数，因此判断条件为 i>0 即可。
        int y = 0;
        for (int i = x; i > 0; i /= 10) {
            y = y * 10 + i % 10;
        }
        return y == x;
    }
}
