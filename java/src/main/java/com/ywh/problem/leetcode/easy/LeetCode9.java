package com.ywh.problem.leetcode.easy;

/**
 * 回文数字判断
 * [数学] [双指针]
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
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
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
        if (x < 0) {
            return false;
        }
        // 保存 x 的值，用于取每一位给 y。
        int y = 0, tmp = x;
        while (tmp != 0) {
            y = y * 10 + tmp % 10;
            tmp /= 10;
        }
        return x == y;
    }
}
