package com.ywh.sorting;

/**
 * 归并排序
 * [排序] [稳定排序] [分治]
 *
 * @author ywh
 * @since 14/11/2019
 */
public class MergeSort {

    private void merge(int[] arr, int low, int mid, int high, int[] tmp) {
        System.arraycopy(arr, low, tmp, low, high - low + 1);
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                arr[k] = tmp[j++];
            } else if (j > high) {
                arr[k] = tmp[i++];
            } else if (tmp[i] < tmp[j]) {
                arr[k] = tmp[i++];
            } else {
                arr[k] = tmp[j++];
            }
        }
    }

    private void mergeSort(int[] arr, int low, int high, int[] tmp) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(arr, low, mid, tmp);
            mergeSort(arr, mid + 1, high, tmp);
            merge(arr, low, mid, high, tmp);
        }
    }

    /**
     * 递归解法（自顶向下）
     *
     * @param arr
     */
    public void sortRecursive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    /**
     * 迭代解法（自底向上）
     *
     * @param arr
     */
    public void sortIterative(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int n = arr.length;
        int[] tmp = new int[n];
        for (int len = 1; len < n; len *= 2) {
            for (int low = 0; low < n; low += 2 * len) {
                int mid = Math.min(low + len - 1, n - 1), high = Math.min(low + 2 * len - 1, n - 1);
                merge(arr, low, mid, high, tmp);
            }
        }
    }
}
