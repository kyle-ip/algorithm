package com.ywh.ds;

/**
 * 最大堆
 *
 * @author ywh
 * @since 2020/11/2/002
 */
public class MaxHeap {

    /**
     * 数组（从 1 开始使用）
     */
    private int[] a;

    /**
     * 堆大小
     */
    private int size;

    /**
     * 堆当前元素个数
     */
    private int count;

    public MaxHeap(int capacity) {
        a = new int[capacity + 1];
        size = capacity;
        count = 0;
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
        for (int i = count; i / 2 > 0 && a[i] > a[i / 2]; i /= 2) {
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
        // 把堆尾元素覆盖到堆顶，然后堆元素个数 -1。
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
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]){
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(i, maxPos);
            i = maxPos;
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
