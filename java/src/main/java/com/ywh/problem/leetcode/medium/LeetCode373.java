package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 查找和最小的 K 对数字
 * [堆]
 *
 * @author ywh
 * @since 12/01/2020
 */
public class LeetCode373 {

    class Elem implements Comparable<Elem> {
        int idx1, idx2, sum;

        Elem(int idx1, int idx2, int sum) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.sum = sum;
        }

        @Override
        public int compareTo(Elem o) {
            return this.sum - o.sum;
        }
    }

    /**
     * 最大堆解法
     *
     * Time: O(n1*n2*log(k)), Space: O(k)
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsMaxHeap(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) {
            return new ArrayList<>();
        }
        // 使用最大堆：便于每次比较中淘汰当前堆中的最大值
        // 1. 堆中元素小于 k：当前元素直接入堆；
        // 2. 堆中元素大于等于 k：与堆顶元素比较，如果比堆顶元素小，则弹出堆顶元素后再加入当前元素
        Queue<Elem> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                if (maxHeap.size() < k) {
                    maxHeap.add(new Elem(i, j, sum));
                } else if (sum < maxHeap.peek().sum) {
                    maxHeap.poll();
                    maxHeap.add(new Elem(i, j, sum));
                }
            }
        }
        List<List<Integer>> res = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            Elem elem = maxHeap.poll();
            res.add(0, Arrays.asList(nums1[elem.idx1], nums2[elem.idx2]));
        }

        return res;
    }

    /**
     * 最小堆解法：利用数组有序的条件，参考 {@link LeetCode378}
     *
     * Time: O(k*log(k)), Space: O(k)
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsMinHeap(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();

        // 使用最小堆：每次从堆中取出的值即为最小值，直接加入结果集
        Queue<Elem> minHeap = new PriorityQueue<>();

        // 由于必须从两个数组各取一个数字、数组都是递增的，所以必然会取到第二个数组的第一个值（不超过 k 个）
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.add(new Elem(i, 0, nums1[i] + nums2[0]));
        }
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) {
            Elem elem = minHeap.poll();
            res.add(Arrays.asList(nums1[elem.idx1], nums2[elem.idx2]));
            elem.idx2++;
            if (elem.idx2 < nums2.length) {
                elem.sum = nums1[elem.idx1] + nums2[elem.idx2];
                minHeap.add(elem);
            }
        }

        return res;
    }
}
