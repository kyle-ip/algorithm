package com.ywh.algorithm.leetcode.easy;

/**
 * 回文字符串判断
 * [双指针] [字符串]
 * <p>
 * Time: O(n), Space: O(1)
 *
 * @author ywh
 * @since 2/11/2019
 */
public class LeetCode125 {

    /**
     * 判断是否大写字母
     *
     * @param c 字符c
     * @return 判断结果
     */
    private static boolean isUppercase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /**
     * 判断是否字母
     *
     * @param c 字符c
     * @return 判断结果
     */
    private static boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * 判断是否数字
     *
     * @param c
     * @return
     */
    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 判断是否字母或数字
     *
     * @param c 字符c
     * @return 判断结果
     */
    private static boolean isAlphanumeric(char c) {
        return isNumber(c) || isAlphabet(c);
    }

    /**
     * 忽略大小写，判断是否相同字符
     *
     * @param a 字符a
     * @param b 字符b
     * @return 判断结果
     */
    private static boolean isEqualIgnoreCase(char a, char b) {
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
     *
     * @param s 字符串s
     * @return 判断结果
     */
    public static boolean isPalindrome(String s) {
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