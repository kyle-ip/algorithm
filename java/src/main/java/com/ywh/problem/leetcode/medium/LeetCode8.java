package com.ywh.problem.leetcode.medium;

/**
 * 字符串转整数
 * [数学] [字符串]
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode8 {

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
     * 依次判断空格、正负号、前导 0、长度；
     * 再逐位转换为 long 型，注意判断是否超出 int 的范围
     *
     * @param str
     * @return
     */
    public int string2Integer(String str) {
        int start, p = 0, length = str.length();
        boolean negative = false;

        // 非数字
        while (p < length && !isNumber(str.charAt(p))) {
            ++p;
        }
        if (p == length) {
            return 0;
        }


        // 正负号
        if (str.charAt(p) == '+') {
            ++p;
        } else if (str.charAt(p) == '-') {
            ++p;
            negative = true;
        }

        // 前导 0
        while (p < length && str.charAt(p) == '0') {
            ++p;
        }

        start = p;
        while (p < length && str.charAt(p) >= '0' && str.charAt(p) <= '9') {
            ++p;
        }
        if (p == start) {
            return 0;
        }

        // 长度
        if (p - start > 10) {
            return negative? Integer.MIN_VALUE: Integer.MAX_VALUE;
        }

        // 逐位转换
        long num = 0;
        for (int i = start; i < p; i++) {
            num = num * 10 + (str.charAt(i) - '0');
        }
        num = negative? -num: num;

        if (num < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (num > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) num;
        }
    }
}
