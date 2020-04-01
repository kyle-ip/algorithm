package com.ywh.problem.leetcode.easy;

/**
 * 三维形体的表面积
 * [几何] [数学]
 *
 * @author ywh
 * @since 01/12/2019
 */
public class LeetCode892 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param grid
     * @return
     */
    public int surfaceArea(int[][] grid) {
        int ret = 0, n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // (i, j) 上叠放了正方体，第一个正方体 6 个面，之后每叠加一个正方体增加 4 个面
                if (grid[i][j] > 0) {
                    ret += 6 + (grid[i][j] - 1) * 4;
                }

                // 之后每个邻接重叠部分都要减去（较矮那栋的邻接面数）
                // 比如一栋两层的和一栋三层的相邻，则要减去两层的两个邻接面
                if (i - 1 >= 0 && grid[i - 1][j] > 0){
                    ret -= Math.min(grid[i][j], grid[i - 1][j]);
                }
                if (i + 1 < n && grid[i + 1][j] > 0) {
                    ret -= Math.min(grid[i][j], grid[i + 1][j]);
                }

                if (j - 1 >= 0 && grid[i][j - 1] > 0) {
                    ret -= Math.min(grid[i][j], grid[i][j - 1]);
                }
                if (j + 1 < n && grid[i][j + 1] > 0) {
                    ret -= Math.min(grid[i][j], grid[i][j + 1]);
                }
            }
        }
        return ret;
    }
}
