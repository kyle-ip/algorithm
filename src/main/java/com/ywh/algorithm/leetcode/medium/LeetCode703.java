package com.ywh.algorithm.leetcode.medium;

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
            // 每有新数字加入，即与 minHeap 中的最小元素对比
            // 如果新数字比较大，则把 minHeap 中的最小元素删除，加入新数字
            else if(val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }

            // 返回 minHeap 的最小值
            return minHeap.peek();
        }
    }

}
