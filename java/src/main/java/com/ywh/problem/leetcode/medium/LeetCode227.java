package com.ywh.problem.leetcode.medium;

/**
 * 基本计算器 II
 * [字符串]
 * 
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 示例 1：
 *      输入：s = "3+2*2"
 *      输出：7
 * 示例 2：
 *      输入：s = " 3/2 "
 *      输出：1
 * 示例 3：
 *      输入：s = " 3+5 / 2 "
 *      输出：5
 * 提示：
 *      1 <= s.length <= 3 * 10^5
 *      s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 *      s 表示一个 有效表达式
 *      表达式中的所有整数都是非负整数，且在范围 [0, 2^31 - 1] 内
 *      题目数据保证答案是一个 32-bit 整数
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

            // 取数字
            while (s.charAt(p) == ' ') {
                p++;
            }
            int num = 0;
            while (p < n && Character.isDigit(s.charAt(p))) {
                num = num * 10 + s.charAt(p) - '0';
                p++;
            }

            // 当遇上 + 或 -，则添加到 sum，并记录 num
            if (op == '+') {
                sum += part;
                part = num;
            } else if (op == '-') {
                sum += part;
                part = -num;
            } else if (op == '*') {
                part *= num;
            } else {
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
