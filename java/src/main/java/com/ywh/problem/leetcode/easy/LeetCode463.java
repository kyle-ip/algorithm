package com.ywh.problem.leetcode.easy;

/**
 * 岛屿的周长
 * [哈希表]
 *
 * @author ywh
 * @since 2019/12/4/004
 */
public class LeetCode463 {

    /**
     * 由于岛屿内没有湖,所以只需要求出上/下其一 + 左/右其一的长度最后再乘2即可
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param grid
     * @return
     */
    public static int islandPerimeter2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length, perimeter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (i == 0 || grid[i - 1][j] == 0) {
                    perimeter += 1;
                }

                if (j == 0 || grid[i][j - 1] == 0) {
                    perimeter += 1;
                }
            }
        }
        return perimeter * 2;
    }

    public static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length, perimeter = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                perimeter += 4;
                if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                    perimeter -= 1;
                }
                if (i + 1 < n && grid[i + 1][j] == 1) {
                    perimeter -= 1;
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                    perimeter -= 1;
                }
                if (j + 1 < m && grid[i][j + 1] == 1) {
                    perimeter -= 1;
                }
            }
        }

        return perimeter;
    }
}
