package com.ywh.sorting;

/**
 * 快速排序
 * [排序] [不稳定排序]
 *
 * @author ywh
 * @since 14/11/2019
 */
public class QuickSort {

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private int lomutoPartition(int[] arr, int low, int high) {
        // 选取基准值、游标从左端开始。
        int pivot = arr[high], i = low;

        // 遍历元素，如果小于基准值，则交换到前面，同时移动游标。
        for (int j = low; j < high; j++) {
            if (arr[j] < arr[high]) {
                swap(arr, i++, j);
            }
        }
        // 最后游标的落点即为基准值的位置（左小右大），把基准值换到这里。
        swap(arr, i, high);
        return i;
    }

    private void lomutoSort(int[] arr, int low, int high) {
        if (low < high) {
            // 不包含 k
            int k = lomutoPartition(arr, low, high);
            lomutoSort(arr, low, k - 1);
            lomutoSort(arr, k + 1, high);
        }
    }

    /**
     * 使用一个游标，一轮遍历后落点为基准值位置
     *
     * 如果把分割后元素较少的子序列先进行递归处理，另一个子序列使用尾递归优化或转成迭代处理，即可把空间复杂度控制在 O(log(n)) 内
     *
     * @param arr
     */
    public void lomutoSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        lomutoSort(arr, 0, arr.length - 1);
    }

    /**
     *
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private int hoarePartition(int[] arr, int low, int high) {
        int pivot = arr[low + (high - low) / 2], i = low, j = high;
        for(;;) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i >= j) {
                return j;
            }
            swap(arr, i++, j--);
        }
    }

    private void hoareSort(int[] arr, int low, int high) {
        if (low < high) {
            int k = hoarePartition(arr, low, high);

            // 由于最后落点不是基准值，所以递归要包含 k
            hoareSort(arr, low, k);
            hoareSort(arr, k + 1, high);
        }
    }

    /**
     * 使用两个游标，每轮循环向中间移动，把大/小于基准值的交换到右/左边，直到游标相遇后返回
     *
     * @param arr
     */
    public void hoareSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        hoareSort(arr, 0, arr.length - 1);
    }
}
