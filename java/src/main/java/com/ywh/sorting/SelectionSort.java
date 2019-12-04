package com.ywh.sorting;

/**
 * 选择排序
 * [排序] [不稳定排序]
 *
 * @author ywh
 * @since 13/11/2019
 */
public class SelectionSort {

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
        }
    }

    public void sortFromEnd(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = arr.length - 1; i > 0; --i) {
            int maxIdx = i;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            swap(arr, i, maxIdx);
        }
    }
}
