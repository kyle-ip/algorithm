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
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i - gap, cur = arr[i];
                for (; j >= 0 && arr[j] > cur; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = cur;
            }
        }
    }
}
