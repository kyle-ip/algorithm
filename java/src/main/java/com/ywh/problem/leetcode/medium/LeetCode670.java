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
        char[] arr = String.valueOf(num).toCharArray();

        // 记录每个数字最后一次出现的下标。
        int[] last = new int[10];
        for (int i = 0; i < arr.length; i++) {
            last[arr[i] - '0'] = i;
        }

        // 从左向右扫描，找到当前位置右边的最大的数字并交换。
        for (int i = 0; i < arr.length; i++) {
            // 按 [9, 1] 的顺序，找到比 i 的位置更大（右边）、且值比 charArray[i] 大的值，交换到左边。
            for (int d = 9; d > arr[i] - '0'; d--) {
                if (last[d] > i) {
                    swap(arr, i, last[d]);
                    // 只交换一次，直接返回。
                    return Integer.parseInt(new String(arr));
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
