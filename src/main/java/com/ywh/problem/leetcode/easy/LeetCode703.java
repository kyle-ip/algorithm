package com.ywh.problem.leetcode.easy;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 数据流中第 K 大的元素
 * [堆]
 *
 * @author ywh
 * @since 2/22/2019
 */
public class LeetCode703 {

    public class KthLargestElementInStream {

        /**
         * 优先级队列，默认即为最小堆，用于存放 Top K 元素
         * poll、peek 方法总是返回最小值（即数据流中的）
         *
         * TODO 自己实现最小堆
         */
        private Queue<Integer> minHeap = new PriorityQueue<>();

        private int k;

        /**
         * Time: O(n*log(k))
         *
         * @param k
         * @param nums
         */
        public KthLargestElementInStream(int k, int[] nums) {
            this.k = k;
            for (int num: nums) {
                add(num);
            }
        }

        /**
         * Time: O(log(k))
         *
         * @param val
         * @return
         */
        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.add(val);
            }
            // 堆容量已满，与 minHeap 中的最小元素对比：
            // 如果新数字比较大，则把 minHeap 中的最小元素删除，加入新数字，可确保当前堆中最小的元素为数据流中的第 k 大元素
            else if(!minHeap.isEmpty() && val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }

            // 返回 minHeap 的最小值
            return minHeap.peek();
        }
    }

    // [1] 5 2
    // 1 5 2 8 => (1) 5 [2] 8
    // 5 2 8 9 => [5] (2) 8 9

}
