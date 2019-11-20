package com.ywh.problem.leetcode.easy;

/**
 * 罗马数字转阿拉伯数字
 * [数学] [字符串]
 *
 * @author ywh
 * @since 19/11/2019
 */
public class LeetCode13 {

    private static final int[] MAPPING = new int[256];

    static {
        MAPPING['I'] = 1;
        MAPPING['V'] = 5;
        MAPPING['X'] = 10;
        MAPPING['L'] = 50;
        MAPPING['C'] = 100;
        MAPPING['D'] = 500;
        MAPPING['M'] = 1000;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {

        // 如果数字从递增，则表示相减，否则相加
        // M, M, C, M, X, C, I, X
        int n = s.length();
        int result = MAPPING[s.charAt(n - 1)];
        for (int i = n - 2; i >= 0; i--) {
            int cur = MAPPING[s.charAt(i)];
            int right = MAPPING[s.charAt(i + 1)];
            if (cur >= right) {
                result += cur;
            } else {
                result -= cur;
            }
        }
        return result;
    }
}
