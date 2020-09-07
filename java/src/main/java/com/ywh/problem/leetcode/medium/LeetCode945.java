package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 使数组唯一的最小增量
 * [数组]
 *
 * @author ywh
 * @since 23/12/2019
 */
public class LeetCode945 {

    /**
     *
     * @param A
     * @return
     */
    public int minIncrementForUniqueHash(int[] A) {
        int[] count = new int[100_000];

        // 对每个元素计数
        for (int i : A) {
            count[i]++;
        }
        // 记录最小增量、每次出现重复时需要移动的次数
        int ret = 0, taken = 0;
        for (int i = 0; i < 100_000; i++) {
            // 如果当前元素出现超过 1 次
            if (count[i] >= 2) {
                // 留下一个，剩余的 count[i] - 1 个需要递增，先从 ret 中减去（留待有空位时再补上）
                taken += count[i] - 1;
                ret -= i * (count[i] - 1);
            }
            // 如果当前元素出现 0 次（可插入空位），且之前需要递增的元素还未处理完
            else if (taken > 0 && count[i] == 0) {
                taken--;
                ret += i;
            }
        }

        return ret;
    }

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param A
     * @return
     */
    public int minIncrementForUniqueSort(int[] A) {
        Arrays.sort(A);
        int count = 0;

        // 数组按从小到达排序
        for (int i = 1; i < A.length; i++) {
            // 如果当前元素小于（连续几个相等）等于前一个元素，则添加增量，并使当前元素为上一个元素 + 1
            // 1, 1, 1, 3 => 1, 2, 1, 3
            if (A[i] <= A[i - 1]) {
                count += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }
        return count;
    }

}
