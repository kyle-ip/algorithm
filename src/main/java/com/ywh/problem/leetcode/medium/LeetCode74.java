package com.ywh.problem.leetcode.medium;

/**
 * 二维数组的二分搜索
 * [数组] [二分搜索]
 *
 * @author ywh
 * @since 2/22/2019
 */
public class LeetCode74 {

    /**
     * 二维的二分查找法：把矩阵看作一行划分区间，
     * 通过对“列数”取商和取模求得中点所在的行数和列数，
     * 并根据坐标与目标值比较大小
     * Time: O(log(m*n)), Space: O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public int[] binarySearchIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[]{-1, -1};
        }
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            int row = mid / n, col = mid % n;
            if (matrix[row][col] == target) {
                return new int[] {row, col};
            }
            if (matrix[row][col] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }


    /**
     * 线性查找：从右到左，从上到下查找
     * Time: O(m*n), Space: O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public int[] linearSearchIn2DArray(int[][] matrix, int target) {

        if (matrix == null
            || matrix.length == 0
            || matrix[0] == null
            || matrix[0].length == 0
            ) {
            return new int[]{-1, -1};
        }

        int row = 0, col = matrix[0].length - 1;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] > target){
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return new int[]{-1, -1};
            }
        }
        return new int[]{-1, -1};
    }
}