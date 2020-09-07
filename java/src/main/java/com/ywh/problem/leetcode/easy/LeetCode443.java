package com.ywh.problem.leetcode.easy;

/**
 * 压缩字符串
 * [字符串] [双指针]
 *
 * @author ywh
 * @since 05/12/2019
 */
public class LeetCode443 {

    /**
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int n = chars.length - 1, left = n, right = n, idx = n, count;
        while (left >= 0) {
            while (left >= 0 && chars[left] == chars[right]) {
                left--;
            }
            count = right - left;
            if (count > 1) {
                while (count > 0) {
                    chars[idx--] = (char) (count % 10 + '0');
                    count /= 10;
                }
            }
            chars[idx--] = chars[left + 1];
            right = left;
        }
        return n - idx;
    }
}
