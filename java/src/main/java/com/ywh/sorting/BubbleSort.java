package com.ywh.sorting;

/**
 * 冒泡排序
 * [排序] [稳定排序]
 *
 * @author ywh
 * @since 13/11/2019
 */
public class BubbleSort {

    /**
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * end: [n - 1, 0)
     * i: [0, end)
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    /**
     * 提早返回
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param arr
     */
    public void sortEarlyReturn(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {

            // 如果子循环中没有发生交换，表示排序已完成，直接返回
            boolean swapped = false;
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
        }
    }

    /**
     * 跳过非必要的位置
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param arr
     */
    public void sortSkip(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int end = arr.length - 1; end > 0; ) {

            // 记录最后一次发生交换的位置，下次子循环到达这个位置即可
            int newEnd = 0;
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    newEnd = i;
                }
            }
            end = newEnd;
        }

    }

}
