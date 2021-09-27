package com.ywh.sorting;

/**
 * 归并排序
 * [排序] [稳定排序] [分治]
 *
 * @author ywh
 * @since 14/11/2019
 */
public class MergeSort {

    private void mergeSort(int[] arr, int low, int high, int[] tmp) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(arr, low, mid, tmp);
        mergeSort(arr, mid + 1, high, tmp);
        // System.arraycopy(arr, low, tmp, low, high - low + 1);
        for (int i = low; i <= high; tmp[i] = arr[i++]);
        for (int l = low, r = mid + 1, k = low; k <= high; k++) {
            if (l > mid) {
                arr[k] = tmp[r++];
            } else if (r > high) {
                arr[k] = tmp[l++];
            } else if (tmp[l] < tmp[r]) {
                arr[k] = tmp[l++];
            } else {
                arr[k] = tmp[r++];
            }
        }
    }

    /**
     * 递归解法（自顶向下）
     *
     * @param arr
     */
    public void sortRecursive(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    /**
     * 迭代解法（自底向上）
     *
     * @param arr
     */
    public void sortIterative(int[] arr) {
        int n = arr.length;
        int[] tmp = new int[n];
        for (int len = 1; len < n; len *= 2) {
            for (int low = 0; low < n; low += 2 * len) {
                int mid = Math.min(low + len - 1, n - 1), high = Math.min(low + 2 * len - 1, n - 1);
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
        }
    }
}
