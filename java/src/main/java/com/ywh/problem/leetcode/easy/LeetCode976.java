package com.ywh.problem.leetcode.easy;

import java.util.Arrays;

/**
 * 三角形的最大周长
 * [排序] [数学]
 *
 * @author ywh
 * @since 2020/11/29
 */
public class LeetCode976 {

    /**
     * 排序 + 贪心解法：
     * 对数组按从小到大排序，每轮循环取后三个元素，如果满足两边之和大于第三边，则返回周长。
     *
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int k = A.length - 1; k >= 2; k--) {
            if (A[k - 1] + A[k - 2] > A[k]) {
                return A[k - 1] + A[k - 2] + A[k];
            }
        }
        return 0;
    }

}
