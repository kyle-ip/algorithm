package com.ywh.problem.leetcode.medium;

/**
 * 单调递增的数字
 * [贪心]
 *
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * 示例 1:
 *      输入: N = 10
 *      输出: 9
 * 示例 2:
 *      输入: N = 1234
 *      输出: 1234
 * 示例 3:
 *      输入: N = 332
 *      输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 *
 * @author ywh
 * @since 2020/12/15/015
 */
public class LeetCode738 {

    /**
     *
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();

        // 从左到右，定位到不再递增/持平的位置（比如 1,2,6,3,7,8 的 1,2,6 递增，因此为 3）。
        int i = 1;
        for (; i < strN.length && strN[i - 1] <= strN[i]; i++);

        // 如果不是整串递增（比如 12445 就无法找到小于等于它、且每位都是递增的数）。
        if (i < strN.length) {
            // 1, 2, 6, 3, 7, 8     =>     1, 2, 5, 3, 7, 8
            //          i                     i
            // 1, 2, 6, 6, 3, 7, 8  =>     1, 2, 5, 5, 3, 7, 8
            //             i                     i
            for (;i > 0 && strN[i - 1] > strN[i]; i--) {
                strN[i - 1]--;
            }
            //
            // 1, 2, 5, 3, 7, 8     =>      1, 2, 5, 9, 9, 9
            //       i
            // 1, 2, 5, 5, 3, 7, 8  =>      1, 2, 5, 9, 9, 9, 9
            //       i
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }
}
