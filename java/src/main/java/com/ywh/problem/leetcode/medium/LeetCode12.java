package com.ywh.problem.leetcode.medium;

/**
 * 整数转罗马数字
 * [数学] [字符串]
 *
 * @author ywh
 * @since 03/09/2020
 */
public class LeetCode12 {

    private static final String[] THOUSANDS = {"", "M", "MM", "MMM"};

    private static final String[] HUNDREDS = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

    private static final String[] TENS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

    private static final String[] ONES = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    /**
     * Time: O(1), Space: O(1)
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        return THOUSANDS[num / 1000] + HUNDREDS[num % 1000 / 100] + TENS[num % 100 / 10] + ONES[num % 10];
    }
}
