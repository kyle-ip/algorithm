package com.ywh.ds.heap;

/**
 * 最小堆
 *
 * @author ywh
 * @since 2020/11/11/011
 */
public class MinHeap {


    /**
     * 数组（从 1 开始使用）
     */
    private final int[] a;

    /**
     * 堆大小
     */
    private final int size;

    /**
     * 堆当前元素个数
     */
    private int count;

    public MinHeap(int size) {
        this.size = size;
        this.count = 0;
        this.a = new int[size + 1];
    }

    /**
     * 插入元素
     *
     * @param val
     */
    public void insert(int val) {
        if (count >= size) {
            throw new RuntimeException();
        }
        a[++count] = val;
        for (int i = count; i / 2 > 0 && a[i] < a[i / 2] ; i /= 2) {
            swap(i, i / 2);
        }
    }

    /**
     * 删除最大元素
     */
    public void removeMax() {
        if (count == 0) {
            throw new RuntimeException();
        }
        a[1] = a[count--];
        heapify(count, 1);
    }

    /**
     * 取最大元素
     *
     * @return
     */
    public int getMax() {
        if (count > 0) {
            return a[1];
        }
        throw new RuntimeException();
    }

    /**
     * 堆化
     *
     * @param n
     * @param i
     */
    private void heapify(int n, int i) {
        while (true) {
            int minPos = i;
            if (i * 2 <= n && a[i] > a[i * 2]) {
                minPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[i] > a[i * 2 + 1]) {
                minPos = i * 2 + 1;
            }
            if (minPos == i) {
                return;
            }
            swap(i, minPos);
            i = minPos;
        }
    }

    /**
     * 交换元素
     *
     * @param x
     * @param y
     */
    private void swap(int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }
}
