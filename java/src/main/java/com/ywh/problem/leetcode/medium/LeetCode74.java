package com.ywh.problem.leetcode.medium;

/**
 * 搜索二维矩阵
 * [数组] [二分查找]
 * 
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *      每行中的整数从左到右按升序排列。
 *      每行的第一个整数大于前一行的最后一个整数。
 * 示例 1：
 *      输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 *      输出：true
 * 示例 2：
 *      输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 *      输出：false
 * 提示：
 *      m == matrix.length
 *      n == matrix[i].length
 *      1 <= m, n <= 100
 *      -10^4 <= matrix[i][j], target <= 10^4
 *
 * @author ywh
 * @since 2/22/2019
 */
public class LeetCode74 {

    /**
     * 对比 {@link LeetCode240}
     *
     * 二维的二分查找：把矩阵看作一行划分区间，
     * 通过对“列数”取商和取模求得中点所在的行数和列数，
     * 并根据坐标与目标值比较大小
     * Time: O(log(m*n)), Space: O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public int[] binarySearchIn2DArray(int[][] matrix, int target) {
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