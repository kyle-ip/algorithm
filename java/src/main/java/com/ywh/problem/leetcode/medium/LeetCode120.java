package com.ywh.problem.leetcode.medium;

import java.util.List;

/**
 * 三角形中的最小路径和
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 02/04/2020
 */
public class LeetCode120 {

    /**
     * 从上至下
     * (i-1, j-1)    (i-1, j)
     *         (i, j)
     * d[i][j] = min(d[i-1][j-1], d[i-1][j]) + a[i][j]      状态转移方程（到达 (i, j) 的最小路径和）
     * d[0][0] = a[0][0]                                    原点
     * d[i][0] = d[i-1][0] + a[i][0]                        左边
     * d[i][i] = d[i-1][i-1] + a[i][i]                      右边
     *
     * 最后处理最下面一层，n 个终点的 n 个最小路径和的最小值
     *
     * Time: O(n^2), Space: O(n^2)
     *
     * @param a
     * @return
     */
    public int minimumTotalTopDown(List<List<Integer>> a) {
        int n = a.size();
        int[][] d = new int[n][n];
        d[0][0] = a.get(0).get(0);

        for (int i = 1; i < n; ++i) {
            // 处理第 i 行首尾两个状态值
            d[i][0] = d[i - 1][0] + a.get(i).get(0);
            d[i][i] = d[i - 1][i - 1] + a.get(i).get(i);
            // 处理第 i 行中间的值
            for (int j = 1; j < i; ++j) {
                d[i][j] = Math.min(d[i - 1][j - 1], d[i - 1][j]) + a.get(i).get(j);
            }
        }

        //
        int min = d[n - 1][0];
        for (int i = 1; i < n; ++i) {
            min = Math.min(min, d[n - 1][i]);
        }
        return min;
    }

    /**
     * Time: O(n^2), Space: O(n)
     *
     * @param a
     * @return
     */
    public int minimumTotalTopDownOn(List<List<Integer>> a) {
        int n = a.size();
        int[] d = new int[n];
        d[0] = a.get(0).get(0);

        for (int i = 1; i < n; ++i) {
            int pre = d[0];
            d[i] = d[i - 1] + a.get(i).get(i);
            d[0] = d[0] + a.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                int tmp = d[j];
                d[j] = Math.min(pre, d[j]) + a.get(i).get(j);
                pre = tmp;
            }
        }
        int min = d[0];
        for (int i = 1; i < n; ++i) {
            min = Math.min(min, d[i]);
        }
        return min;
    }

    /**
     * 从下至上
     * d[i][j] = min(d[i+1][j], d[i+1][j+1]) + a[i][j]      状态转移方程（到达 (i, j) 的最小路径和）
     * ...
     * 最终返回 d[0][0] 即可
     *
     * Time: O(n^2), Space: O(n^2)
     *
     * @param a
     * @return
     */
    public int minimumTotalBottomUp(List<List<Integer>> a) {
        int n = a.size();
        int[][] d = new int[n][n];
        for (int j = 0; j < n; j++) {
            d[n - 1][j] = a.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                d[i][j] = Math.min(d[i + 1][j], d[i + 1][j + 1]) + a.get(i).get(j);
            }
        }
        return d[0][0];
    }

    /**
     * Time: O(n^2), Space: O(n)
     *
     * @param a
     * @return
     */
    public int minimumTotalBottomUpOn(List<List<Integer>> a) {
        int n = a.size();
        int[] d = new int[n];
        for (int j = 0; j < n; ++j) {
            d[j] = a.get(n - 1).get(j);
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                d[j] = Math.min(d[j], d[j + 1]) + a.get(i).get(j);
            }
        }
        return d[0];
    }
}
