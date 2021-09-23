package com.ywh.sorting;

import java.util.Arrays;

/**
 * 计数排序
 * [排序] [稳定排序]
 *
 * @author ywh
 * @since 14/11/2019
 */
public class CountingSort {

    /**
     * Time: O(n+k), Space: O(n+k)
     *
     * @param arr
     */
    public void sortLeft2Right(int[] arr) {
        // 确定元素个数：[min, max]。
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, k, start = 0;
        for (int num : arr) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        k = max - min;

        // 对数组元素计数：把 [min, max] 范围内的 arr.length 个元素，统计数量后存放在 [0, k] 的数组 indexes 中。
        // 比如 [2, 5, 3, 0, 2, 3, 0, 3]，计数：[2, 0, 2, 3, 0, 1]（即 2 个 0，0 个 1，2 个 2...）
        //                                    0  1  2  3  4  5
        int[] indexes = new int[k + 1], tmp = new int[arr.length];
        Arrays.fill(tmp, -1);
        for (int num : arr) {
            ++indexes[num - min];
        }

        // 计数数组转化为位置数组：根据计数计算出每个元素的开始下标：
        // 比如计数：[2, 0, 2, 3, 0, 1]，转化为下标：[0, 2, 2, 4, 7, 7]（即 0 从 0 开始，1 从 2 开始，2 从 2 开始...）
        //           0  1  2  3  4  5              0  1  2  3  4  5
        for (int i = 0; i <= k; i++) {
            int count = indexes[i];
            indexes[i] = start;
            start += count;
        }

        // 填充结果数组
        for (int num : arr) {
            // 遍历数组元素，获取它在新数组的开始下标，填充到结果数组。
            //     num              indexes         indexes[num]               tmp
            //      2	    [0, 2, 2, 4, 7, 7, ]	    2	        [-1, -1, -1, -1, -1, -1, -1, -1, ]
            //      5	    [0, 2, 3, 4, 7, 7, ]	    7	        [-1, -1, 2, -1, -1, -1, -1, -1, ]
            //      3	    [0, 2, 3, 4, 7, 8, ]	    4	        [-1, -1, 2, -1, -1, -1, -1, 5, ]
            //      0	    [0, 2, 3, 5, 7, 8, ]	    0	        [-1, -1, 2, -1, 3, -1, -1, 5, ]
            //      2	    [1, 2, 3, 5, 7, 8, ]	    3	        [0, -1, 2, -1, 3, -1, -1, 5, ]
            //      3	    [1, 2, 4, 5, 7, 8, ]	    5	        [0, -1, 2, 2, 3, -1, -1, 5, ]
            //      0	    [1, 2, 4, 6, 7, 8, ]	    1	        [0, -1, 2, 2, 3, 3, -1, 5, ]
            //      3	    [2, 2, 4, 6, 7, 8, ]	    6	        [0, 0, 2, 2, 3, 3, -1, 5, ]
            //                                                      [0, 0, 2, 2, 3, 3, 3, 5, ]
            tmp[indexes[num - min]] = num;

            // 填充后开始下标要右移，表示下一次填写的位置
            ++indexes[num - min];
        }

        // 写回原数组
        System.arraycopy(tmp, 0, arr, 0, arr.length);
    }

    /**
     * 这种解法最坏时间复杂度取决于有多少个重复元素，不建议
     *
     * @param arr
     */
    public void sort(int[] arr) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, k, index = 0;
        for (int num : arr) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        k = max - min;
        int[] indexes = new int[k + 1];
        for (int num : arr) {
            indexes[num - min]++;
        }

        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < indexes[i]; j++) {
                arr[index++] = i + min;
            }
        }
    }

    /**
     * Time: O(n+k), Space: O(n+k)
     *
     * @param arr
     */
    public void sortRight2Left(int[] arr) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, k;
        for (int num : arr) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        k = max - min;

        int[] indexes = new int[k + 1];
        for (int num : arr) {
            ++indexes[num - min];
        }

        --indexes[0];
        for (int i = 1; i <= k; ++i) {
            indexes[i] = indexes[i] + indexes[i - 1];
        }

        int[] tmp = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; --i) {
            int idx = indexes[arr[i] - min];
            tmp[idx] = arr[i];
            --indexes[arr[i] - min];
        }
        System.arraycopy(tmp, 0, arr, 0, arr.length);
    }

}
