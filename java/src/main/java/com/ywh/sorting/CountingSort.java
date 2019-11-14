package com.ywh.sorting;

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
        if (arr == null || arr.length == 0) {
            return;
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, k, start = 0;
        for (int num : arr) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        k = max - min;

        // 对数组元素计数
        int[] indexes = new int[k + 1], tmp = new int[arr.length];
        for (int num : arr) {
            ++indexes[num - min];
        }

        // 根据计数计算出每个元素的开始下标
        for (int i = 0; i <= k; ++i) {
            int count = indexes[i];
            indexes[i] = start;
            start += count;
        }

        for (int num : arr) {
            // 遍历数组元素，获取它在新数组的开始下标，填充到结果数组
            int startIdx = indexes[num - min];
            tmp[startIdx] = num;

            // 填充后开始下标要右移，表示下一次填写的位置
            ++indexes[num - min];
        }
        System.arraycopy(tmp, 0, arr, 0, arr.length);
    }

    /**
     * 这种解法最坏时间复杂度取决于有多少个重复元素，不建议
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
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
        if (arr == null || arr.length == 0) {
            return;
        }
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
