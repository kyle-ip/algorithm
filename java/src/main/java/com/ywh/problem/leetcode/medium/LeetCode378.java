package com.ywh.problem.leetcode.medium;

import com.ywh.model.MatrixElem;
import com.ywh.problem.leetcode.easy.LeetCode21;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 有序矩阵中第 K 小的元素
 * [堆] [二分搜索]
 * 
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * 示例 1：
 *      输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 *      输出：13
 *      解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 * 示例 2：
 *      输入：matrix = [[-5]], k = 1
 *      输出：-5
 * 提示：
 *      n == matrix.length
 *      n == matrix[i].length
 *      1 <= n <= 300
 *      -10^9 <= matrix[i][j] <= -10^9
 *      题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 *      1 <= k <= n^2
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

    /**
     * Time: O(n), Space: O(1)
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallestBinarySearch(int[][] matrix, int k) {
        int n = matrix.length, min = matrix[0][0], max = matrix[n - 1][n - 1];
        while (min < max) {
            // 矩阵从左上到右下递增，人去一个数 mid 满足 min <= mid <= mid，矩阵中不大于 mid 的元素都分布在左上角。
            //      设置初始位置为左下角 matrix[n-1][0]，设置当前位置为 matrix[i][j]（以左下到右上作分界线）。
            //      如果 matrix[i][j] <= mid，则将当前所在列的不大于 mid 的数的数量累加到 num 中，不断向右移动，否则向上移动。
            //      不断移动直到走出格子为止。
            int mid = min + (max - min) / 2, i = n - 1, j = 0, num = 0;
            while (i >= 0 && j < n) {
                // 平均数在分界线左上，因此记录累加“当前所在列的不大于 mid 的数的数量”，分界线右移。
                if (matrix[i][j] <= mid) {
                    num += i + 1;
                    j++;
                }
                // 平均数在分界线右下，因此分界线上移。
                else {
                    i--;
                }
            }
            if (num >= k) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

}
