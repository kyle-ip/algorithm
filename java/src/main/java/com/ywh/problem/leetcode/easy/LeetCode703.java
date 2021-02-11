package com.ywh.problem.leetcode.easy;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 数据流中第 K 大的元素
 * [堆]
 * 
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest 类：
 *      KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 *      int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * 示例：
 *      输入：
 *          ["KthLargest", "add", "add", "add", "add", "add"]
 *          [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 *      输出：
 *          [null, 4, 5, 5, 8, 8]
 *      解释：
 *          KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 *          kthLargest.add(3);   // return 4
 *          kthLargest.add(5);   // return 5
 *          kthLargest.add(10);  // return 5
 *          kthLargest.add(9);   // return 8
 *          kthLargest.add(4);   // return 8
 * 提示：
 *      1 <= k <= 10^4
 *      0 <= nums.length <= 10^4
 *      -10^4 <= nums[i] <= 10^4
 *      -10^4 <= val <= 10^4
 *      最多调用 add 方法 10^4 次
 *      题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 *
 * @author ywh
 * @since 2/22/2019
 */
public class LeetCode703 {

    public class KthLargest {

        /**
         * 优先级队列，默认即为最小堆，用于存放 Top K 元素
         * poll、peek 方法总是返回最小值（即数据流中的）
         */
        private Queue<Integer> minHeap = new PriorityQueue<>();

        private int k;

        /**
         * Time: O(n*log(k))
         *
         * @param k
         * @param nums
         */
        public KthLargest(int k, int[] nums) {
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
            // 如果新数字比较大，则把 minHeap 中的最小元素删除，加入新数字
            // 可确保当前堆中最小的元素为数据流中的第 k 大元素
            else if(val > minHeap.peek()) {
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
