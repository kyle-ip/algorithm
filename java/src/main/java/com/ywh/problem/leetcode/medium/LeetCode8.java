package com.ywh.problem.leetcode.medium;

/**
 * 字符串转整数
 * [数学] [字符串]
 *
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 *      如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 *      假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 *      该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 *      在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * 提示：
 *      本题中的空白字符只包括空格字符 ' ' 。
 *      假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode8 {

    /**
     * Time: O(n), Space: O(1)
     *
     * 依次判断、长度；
     * 再逐位转换为 long 型，注意判断是否超出 int 的范围。
     * 判断条件：
     *      1. 空格、正负号、前导 0
     *      2. 下标边界（s.length()）
     *      3. 数值范围（数值部分长度不超过 10 位，数值本身在 [Integer.MIN_VALUE, Integer.MAX_VALUE] 之间）
     *
     * @param s
     * @return
     */
    public int string2Integer(String s) {
        int p = 0, n = s.length();
        boolean negative = false;

        // 空白
        for (; p < n && s.charAt(p) == ' '; p++);
        if (p == n) {
            return 0;
        }

        // 正负号
        if (s.charAt(p) == '+') {
            ++p;
        } else if (s.charAt(p) == '-') {
            ++p;
            negative = true;
        }

        // 前导 0
        for (; p < n && s.charAt(p) == '0'; p++);

        // 数字（start 与 p 之间）
        int i = p;
        for (; p < n && s.charAt(p) >= '0' && s.charAt(p) < '9'; p++);
        if (p == i) {
            return 0;
        }

        // 长度（超过 10，返回最大值或最小值）
        if (p - i > 10) {
            return negative? Integer.MIN_VALUE: Integer.MAX_VALUE;
        }

        // 逐位转换
        long num = 0;
        for (; i < p; num = num * 10 + s.charAt(i++) - '0');

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
