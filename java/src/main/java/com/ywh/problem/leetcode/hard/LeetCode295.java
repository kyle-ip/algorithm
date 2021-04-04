package com.ywh.problem.leetcode.hard;

import java.util.*;

/**
 * 数据流的中位数
 * [堆] [设计]
 * 
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 *      [2,3,4] 的中位数是 3
 *      [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 *      void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 *      double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *      addNum(1)
 *      addNum(2)
 *      findMedian() -> 1.5
 *      addNum(3)
 *      findMedian() -> 2
 * 进阶:
 *      如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 *      如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
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
     * 数据流中，最小堆存放较大的一半，最大堆存放较小的一半（最大堆倒序排列，堆顶元素为最大值），两堆顶元素即为最中间的两个数值。
     */
    private final Queue<Integer> minHeap = new PriorityQueue<>(),
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    /**
     * Time: O(log(n))
     *
     * @param num
     */
    public void addNumHeap(int num) {

        // 元素先放入最小堆（较大的一半），再从中取出最小值放入最大堆（较小的一半）。
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        // 两堆均衡：保证最大堆与最小堆元素个数相等，或最大堆比最小堆多一个元素。
        if (maxHeap.size() - minHeap.size() > 1) {
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
