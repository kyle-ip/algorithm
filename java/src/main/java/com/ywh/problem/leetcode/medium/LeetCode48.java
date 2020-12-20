package com.ywh.problem.leetcode.medium;

/**
 * 旋转图像
 * [数组]
 * 
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 *      你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 示例 1:
 *      给定 matrix =
 *      [
 *        [1,2,3],
 *        [4,5,6],
 *        [7,8,9]
 *      ],
 *      原地旋转输入矩阵，使其变为:
 *      [
 *        [7,4,1],
 *        [8,5,2],
 *        [9,6,3]
 *      ]
 * 示例 2:
 *      给定 matrix =
 *      [
 *        [ 5, 1, 9,11],
 *        [ 2, 4, 8,10],
 *        [13, 3, 6, 7],
 *        [15,14,12,16]
 *      ],
 *      原地旋转输入矩阵，使其变为:
 *      [
 *        [15,13, 2, 5],
 *        [14, 3, 4, 1],
 *        [12, 6, 8, 9],
 *        [16, 7,10,11]
 *      ]
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode48 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        // a[0][0], a[0][1],     ...   a[0][n-1], a[0][n]
        // a[1][0], a[1][1],     ...   a[1][n-1], a[1][n]
        // ...
        // a[n-1][0], a[n-1][1]  ...   a[n-1]a[n-1], a[n-1][n]
        // a[n][0], a[n][1]      ...   a[n][n-1], a[n][n]

        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            int start = i, end = n - i - 1;
            for (int j = 0; j < end - start; j++) {
                int temp = matrix[start][start + j];
                matrix[start][start + j] = matrix[end - j][start];
                matrix[end - j][start] = matrix[end][end - j];
                matrix[end][end - j] = matrix[start + j][end];
                matrix[start + j][end] = temp;
            }
        }

    }

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int n = matrix.length;
        // 以主对角线（左上到右下）为对称轴交换右边的元素
        // 1,   [2],  [3]      1, 4, 7
        // (4),  5,   [6]  =>  2, 5, 8
        // (7), (8),   9       3, 6, 9
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 以中线为对称轴，交换左右对称列的元素
        // [1], 4, (7)      7, 4, 1
        // [2], 5, (8)  =>  8, 5, 2
        // [3], 6, (9)      9, 6, 3
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }

    }
}
