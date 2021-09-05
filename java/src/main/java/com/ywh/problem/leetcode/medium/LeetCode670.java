package com.ywh.problem.leetcode.medium;

/**
 * 最大交换
 * [数学] [数组]
 *
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * 示例 1 :
 *      输入: 2736
 *      输出: 7236
 *      解释: 交换数字2和数字7。
 * 示例 2 :
 *      输入: 9973
 *      输出: 9973
 *      解释: 不需要交换。
 * 注意:
 *      给定数字的范围是 [0, 108]
 *
 * @author ywh
 * @since 31/03/2021
 */
public class LeetCode670 {

    /**
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        char[] charArray = s.toCharArray();

        // 记录每个数字最后一次出现的下标。
        int[] last = new int[10];
        for (int i = 0; i < s.length(); i++) {
            last[charArray[i] - '0'] = i;
        }

        // 从左向右扫描，找到当前位置右边的最大的数字并交换。
        for (int i = 0; i < s.length() - 1; i++) {
            // 找最大，所以倒着找
            for (int d = 9; d > charArray[i] - '0'; d--) {
                // 字符 d 在 i 右边，
                if (last[d] > i) {
                    swap(charArray, i, last[d]);
                    // 只允许交换一次，因此直接返回。
                    return Integer.parseInt(new String(charArray));
                }
            }
        }
        return num;
    }

    /**
     *
     * @param a
     * @param i
     * @param j
     */
    private void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
