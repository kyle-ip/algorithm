package com.ywh.sorting;

/**
 * 鸡尾酒排序
 * [排序] [稳定排序]
 *
 * @author ywh
 * @since 13/11/2019
 */
public class CocktailShakerSort {

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
        // 控制左右边界，每轮排序向中间缩进
        int l = 0, r = arr.length - 1;
        while (l < r) {
            for (int i = l; i < r; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
            --r;
            for (int i = r; i > l; i--) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                }
            }
            ++l;
        }
    }

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param arr
     */
    public void sortEarlyReturn(int[] arr) {
        for (int l = 0, r = arr.length - 1; l < r; ) {
            boolean swapped = false;
            for (int i = l; i < r; ++i) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            --r;

            for (int i = r; i > l; --i) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                    swapped = true;
                }
            }
            ++l;

            if (!swapped) {
                return;
            }
        }
    }

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param arr
     */
    public void sortSkip(int[] arr) {
        for (int l = 0, r = arr.length - 1; l < r; ) {
            int newR = l;
            for (int i = l; i < r; ++i) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    newR = i;
                }
            }
            r = newR;

            int newL = r;
            for (int i = r; i > l; --i) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                    newL = i;
                }
            }
            l = newL;
        }
    }
}
