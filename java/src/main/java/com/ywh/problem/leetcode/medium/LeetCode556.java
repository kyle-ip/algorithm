package com.ywh.problem.leetcode.medium;

/**
 * 下一个更大元素 III
 * [字符串]
 * 
 * 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。
 * 如果不存在这样的正整数，则返回 -1 。
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * 示例 1：
 *      输入：n = 12
 *      输出：21
 * 示例 2：
 *      输入：n = 21
 *      输出：-1
 * 提示：
 *      1 <= n <= 2^31 - 1
 * 
 * @author ywh
 * @since 4/21/2021
 */
public class LeetCode556 {

    /**
     * 参考 {@link LeetCode31}
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        char[] a = String.valueOf(n).toCharArray();
        int k = a.length - 2;
        for (; k >= 0 && a[k + 1] <= a[k]; k--);
        if (k < 0) {
            return -1;
        }
        int i = a.length - 1;
        for (; i >= 0 && a[i] <= a[k]; i--);
        swap(a, k, i);

        for (int l = k + 1, r = a.length - 1; l < r; l++, r--) {
            swap(a, l, r);
        }
        try {
            return Integer.parseInt(new String(a));
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     *
     * @param a
     * @param i
     * @param j
     */
    private void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
