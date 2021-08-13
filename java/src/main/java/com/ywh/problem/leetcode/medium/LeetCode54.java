package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * [数组]
 *
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1：
 *      输入:
 *      [
 *          [ 1, 2, 3 ],
 *          [ 4, 5, 6 ],
 *          [ 7, 8, 9 ]
 *      ]
 *      输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *      输入:
 *      [
 *          [1, 2, 3, 4],
 *          [5, 6, 7, 8],
 *          [9,10,11,12]
 *      ]
 *      输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * @author ywh
 * @since 24/11/2019
 */
public class LeetCode54 {

    /**
     * Time: O(m*n), Space: O(1)
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        int t = 0, b = matrix.length - 1, l = 0, r = matrix[0].length - 1;
        while (true) {
            // top: left -> right
            for (int i = l; i <= r; i++) {
                ret.add(matrix[t][i]);
            }
            if (++t > b) {
                break;
            }
            // right: top -> bottom
            for (int i = t; i <= b; i++) {
                ret.add(matrix[i][r]);
            }
            if (--r < l) {
                break;
            }
            // bottom: right -> left
            for (int i = r; i >= l; i--) {
                ret.add(matrix[b][i]);
            }
            if (--b < t) {
                break;
            }
            // left: bottom -> top
            for (int i = b; i >= t; --i) {
                ret.add(matrix[i][l]);
            }
            if (++l > r) {
                break;
            }
        }
        return ret;
    }
}
