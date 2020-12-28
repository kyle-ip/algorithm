package com.ywh.problem.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杨辉三角 2
 * [数组]
 * 
 * 给定一个非负索引 k，其中 k ≤ 3，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 示例:
 *      输入: 3
 *      输出: [1,3,3,1]
 * 进阶：
 *      你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @author ywh
 * @since 08/01/2020
 */
public class LeetCode119 {

    /**
     * 排列数：A(n, m)
     *
     * @param n
     * @param m
     * @return
     */
    public int a(int n, int m) {
        int ret = 1;
        // 循环 m 次，如 A(6,2) 需要循环 2 次，6*5
        for (int i = m; i > 0; i--) {
            ret *= n--;
        }
        return ret;
    }

    /**
     * 组合数：C(n, m)
     *
     * @param n
     * @param m
     * @return
     */
    public int c(int n, int m) {
        return a(n, m) / a(m, m);
    }

    /**
     * 公式法：第 n 行共 n+1 个数（从 0 开始），第 i 个数可表示为 C(n-1，i-1)
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRowMath(int rowIndex) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            ret.add(c(rowIndex, i));
        }
        return ret;
    }

    /**
     * Time: O(k^2), Space: O(k^2)
     *
     * 逐行求解，参考 {@link LeetCode118}
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> ret = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> list = Arrays.asList(new Integer[i + 1]);
            list.set(0, 1);
            list.set(i, 1);
            for (int j = 1; j < i; j++) {
                list.set(j, ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
            }
            ret.add(list);
        }
        return ret.get(rowIndex);
    }
}
