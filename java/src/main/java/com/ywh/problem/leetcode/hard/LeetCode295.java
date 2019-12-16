package com.ywh.problem.leetcode.hard;

import java.util.*;

/**
 * 在数据流中查找中位数
 * [堆] [设计]
 *
 * @author ywh
 * @since 16/12/2019
 */
public class LeetCode295 {

    private final List<Integer> data = new ArrayList<>();

    /**
     * Time: O(n)
     *
     * @param num
     */
    public void addNumArray(int num) {
        int idx = 0;
        while (idx < data.size() && data.get(idx) < num) {
            idx++;
        }
        // 此时 data[idx] >= num，因此在 data[idx] 前插入 num
        data.add(idx, num);
    }

    /**
     * Time: O(1)
     *
     * @return
     */
    public double findMedianArray() {
        int n = data.size();
        if ((n & 1) == 1) {
            return data.get(n / 2);
        } else {
            return (data.get(n / 2 - 1) + data.get(n / 2)) / 2.0;
        }
    }

    /**
     * 最小堆存放数据流中较大的一半，两堆顶元素即为最中间的两个数值
     */
    private final Queue<Integer> minHeap = new PriorityQueue<>();

    /**
     * 最大堆存放数据流中较小的一半（倒序排列，即堆顶元素为最大值）
     */
    private final Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    /**
     * Time: O(log(n))
     *
     * @param num
     */
    public void addNumHeap(int num) {
        // 元素先放入最小堆
        minHeap.add(num);

        // 最小堆的堆顶元素（最小值）放到最大堆
        maxHeap.add(minHeap.poll());

        // 要么最大堆与最小堆元素个数相等，要么最大堆比最小堆多一个元素
        if (maxHeap.size() - minHeap.size() > 1) {
            // 最大堆的堆顶元素（最大值）放到最小堆
            minHeap.add(maxHeap.poll());
        }
    }

    /**
     * Time: O(1)
     *
     * @return
     */
    public double findMedianHeap() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}
