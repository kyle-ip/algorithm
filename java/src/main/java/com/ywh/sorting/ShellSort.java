package com.ywh.sorting;

/**
 * 希尔排序
 * [排序] [不稳定排序]
 *
 * @author ywh
 * @since 13/11/2019
 */
public class ShellSort {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int gap = arr.length >> 1; gap > 0; gap >>= 1) {
            for (int i = gap; i < arr.length; i++) {
                int j = i - gap, cur = arr[i];
                while (j >= 0 && arr[j] > cur) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = cur;
            }
        }
    }
}
