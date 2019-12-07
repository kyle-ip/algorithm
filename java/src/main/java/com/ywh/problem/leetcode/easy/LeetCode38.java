package com.ywh.problem.leetcode.easy;

/**
 * 报数
 * [字符串]
 *
 * @author ywh
 * @since 07/12/2019
 */
public class LeetCode38 {



    /**
     * Time: O(1.3^n), Space: O(1.3^n)
     * Time: O(2^n), Space: O(2^n)
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n < 1) {
            return null;
        }
        String s = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int idx = 0, cnt = 1; idx < s.length(); ++idx) {
                if (idx+1 < s.length() && s.charAt(idx) == s.charAt(idx+1)) {
                    ++cnt;
                } else {
                    sb.append(cnt).append(s.charAt(idx));
                    cnt = 1;
                }
            }
            s = sb.toString();
        }
        return s;
    }

}
