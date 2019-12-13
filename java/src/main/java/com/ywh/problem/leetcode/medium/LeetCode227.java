package com.ywh.problem.leetcode.medium;

/**
 * 简易计算器
 * [字符串]
 *
 * @author ywh
 * @since 13/12/2019
 */
public class LeetCode227 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        // 把表达式中的乘除运算视为一个部分，整体视为各个部分之和；表达式符号初始化为“+”，视为 0 + ...

        // part 为上一个部分的解结果，sum 为当前和
        int p = 0, sum = 0, part = 0, n = s.length();
        char op = '+';

        while (p < n) {
            while (s.charAt(p) == ' ') {
                p++;
            }
            int num = 0;
            while (p < n && s.charAt(p) >= '0' && s.charAt(p) <= '9') {
                num = num * 10 + s.charAt(p) - '0';
                p++;
            }

            // 当遇上 + 或 -，则添加到 sum，并记录 num
            if (op == '+') {
                sum += part;
                part = num;
            }
            else if (op == '-') {
                sum -= part;
                part = num;
            }
            else if (op == '*') {
                part *= num;
            }
            else {
                part /= num;
            }
            while (p < n && s.charAt(p) == ' ') {
                p++;
            }
            if (p < n) {
                op = s.charAt(p++);
            }
        }

        return sum + part;
    }
}
