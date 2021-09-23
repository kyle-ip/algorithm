package com.ywh.sorting;

/**
 * 堆排序
 * [排序] [不稳定排序]
 *
 * @author ywh
 * @since 14/11/2019
 */
public class HeapSort {

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 下沉，大元素排前面，小元素排后面
     * Time: O(log(n))
     *
     * @param arr
     * @param i
     * @param end
     */
    private void siftDown(int[] arr, int i, int end) {
        int parent = i, child = 2 * parent + 1;
        while (child <= end) {
            if (child + 1 <= end && arr[child + 1] > arr[child]) {
                child++;
            }
            if (arr[parent] >= arr[child]) {
                break;
            }
            swap(arr, parent, child);
            parent = child;
            child = 2 * parent + 1;
        }
    }

    /**
     * 构造大顶堆
     *
     * @param arr
     */
    private void buildMaxHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            siftDown(arr, i, arr.length - 1);
        }
    }

    /**
     * Time: O(n*log(n)), Space: O(1)
     *
     * @param arr
     */
    public void sort(int[] arr) {
        buildMaxHeap(arr);
        for (int end = arr.length - 1; end > 0; end--) {
            // 每次把堆顶元素换到最后，再把交换后的堆顶元素下沉合适的位置
            swap(arr, 0, end);
            siftDown(arr, 0, end - 1);
        }
    }
}
