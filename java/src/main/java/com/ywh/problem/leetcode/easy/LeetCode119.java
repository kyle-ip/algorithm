package com.ywh.problem.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 帕斯卡三角形 2
 * [数组]
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
        int result = 1;
        // 循环m次,如A(6,2)需要循环2次，6*5
        for (int i = m; i > 0; i--)
        {
            result *= n;
            n--;// 下一次减一
        }
        return result;
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
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            result.add(c(rowIndex, i));
        }
        return result;
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
        List<List<Integer>> result = new ArrayList<>(rowIndex + 1);

        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> list = Arrays.asList(new Integer[i + 1]);
            list.set(0, 1);
            list.set(i, 1);
            for (int j = 1; j < i; j++) {
                list.set(j, result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
            }
            result.add(list);
        }

        return result.get(rowIndex);
    }
}
