package com.ywh.problem.leetcode.easy;

/**
 * 验证回文串
 * [双指针] [字符串]
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 示例 1:
 *      输入: "A man, a plan, a canal: Panama"
 *      输出: true
 * 示例 2:
 *      输入: "race a car"
 *      输出: false
 *
 * Time: O(n), Space: O(1)
 *
 * @author ywh
 * @since 2/11/2019
 */
public class LeetCode125 {

    /**
     * 判断是否大写字母
     *
     * @param c 字符
     * @return 判断结果
     */
    private boolean isUppercase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /**
     * 判断是否字母
     *
     * @param c 字符
     * @return 判断结果
     */
    private boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * 判断是否数字
     *
     * @param c
     * @return
     */
    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 判断是否字母或数字
     *
     * @param c 字符
     * @return 判断结果
     */
    private boolean isAlphanumeric(char c) {
        return isNumber(c) || isAlphabet(c);
    }

    /**
     * 忽略大小写，判断是否相同字符
     *
     * @param a 字符
     * @param b 字符
     * @return 判断结果
     */
    private boolean isEqualIgnoreCase(char a, char b) {
        if (!isAlphanumeric(a) || !isAlphanumeric(b)) {
            return false;
        }
        if (isUppercase(a)) {
            a += 32;
        }
        if (isUppercase(b)) {
            b += 32;
        }
        return a == b;
    }

    /**
     * 判断是否回文串
     * Time: O(n), Space: O(1)
     *
     * @param s 字符串
     * @return 判断结果
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 双指针跳过非字母和非数字向中间移动
            while (left < right && !isAlphanumeric(s.charAt(left))) {
                ++left;
            }
            while (left < right && !isAlphanumeric(s.charAt(right))) {
                --right;
            }
            // 未越界，且所指字符不相等
            if (left < right && !isEqualIgnoreCase(s.charAt(left), s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}