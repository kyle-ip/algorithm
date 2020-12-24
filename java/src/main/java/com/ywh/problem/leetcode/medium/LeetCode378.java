package com.ywh.problem.leetcode.medium;

import com.ywh.model.MatrixElem;
import com.ywh.problem.leetcode.easy.LeetCode21;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 行列递增矩阵中第 K 小的元素
 * [堆] [二分搜索]
 *
 * @author ywh
 * @since 01/01/2020
 */
public class LeetCode378 {

    /**
     * 最大堆解法
     *
     * Time: O(m*n*log(k)), Space: O(k)
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallestMaxHeap(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        // 大小为 k 的最大堆，堆顶元素为最大元素
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] row : matrix) {
            for (int num : row) {

                // 如果堆未填满，直接添加
                if (maxHeap.size() < k) {
                    maxHeap.add(num);
                }
                // 如果当前元素小于堆顶元素，则取出堆顶元素、添加新元素（确保堆中存放最小的 k 个数）
                else if (num < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.add(num);
                }
            }
        }
        // 由于堆顶元素是最大值，堆中存放矩阵中最小的 k 个数，所以返回堆顶元素
        return maxHeap.peek();
    }

    /**
     * 最小堆解法（利用矩阵行列递增特点）
     * 参考 {@link LeetCode21}
     *
     * Time: O(k*log(k)), Space: O(k)
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallestMinHeap(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;

        // 由于矩阵从左到右、从上到下递增，先把每行第一个元素（行最小值）添加到堆（不超过 k 个）
        Queue<MatrixElem> minHeap = new PriorityQueue<>();
        for (int i = 0; i < Math.min(m, k); i++) {
            minHeap.add(new MatrixElem(i, 0, matrix[i][0]));
        }
        // 此时堆中存放了最小的 Math.min(m, k) 个元素，逐行加入下一个元素（同时取出堆顶元素），直到 k - 1 次
        for (int i = 0; i < k - 1; ++i) {
            MatrixElem e = minHeap.poll();
            ++e.col;
            // 如该行未越界，则取下一个元素（只修改列和值）添加到堆
            if (e.col < n) {
                e.val = matrix[e.row][e.col];
                minHeap.add(e);
            }
        }
        return minHeap.peek().val;
    }
}
