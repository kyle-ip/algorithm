package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和 III
 * [数组] [回溯]
 * 
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 *      所有数字都是正整数。
 *      解集不能包含重复的组合。
 * 示例 1:
 *      输入: k = 3, n = 7
 *      输出: [[1,2,4]]
 * 示例 2:
 *      输入: k = 3, n = 9
 *      输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author ywh
 * @since 4/22/2021
 */
public class LeetCode216 {


    List<Integer> temp = new ArrayList<>();

    List<List<Integer>> ret = new ArrayList<>();

    /**
     * 二进制（子集）枚举
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3Binary(int k, int n) {
        for (int mask = 0; mask < (1 << 9); ++mask) {
            if (check(mask, k, n)) {
                ret.add(new ArrayList<>(temp));
            }
        }
        return ret;
    }

    public boolean check(int mask, int k, int n) {
        temp.clear();
        for (int i = 0; i < 9; ++i) {
            if (((1 << i) & mask) != 0) {
                temp.add(i + 1);
            }
        }
        if (temp.size() != k) {
            return false;
        }
        int sum = 0;
        for (int num : temp) {
            sum += num;
        }
        return sum == n;
    }

    /**
     * 组合枚举
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        return dfs(1, 9, k, n, new LinkedList<>(), new ArrayList<>());
    }

    /**
     *
     * @param cur
     * @param n
     * @param k
     * @param sum
     * @param elem
     * @param ret
     * @return
     */
    public List<List<Integer>> dfs(int cur, int n, int k, int sum, LinkedList<Integer> elem, List<List<Integer>> ret) {
        if (elem.size() + (n - cur + 1) < k || elem.size() > k) {
            return ret;
        }
        if (elem.size() == k) {
            int tempSum = 0;
            for (int num : elem) {
                tempSum += num;
            }
            if (tempSum == sum) {
                ret.add(new ArrayList<>(elem));
                return ret;
            }
        }
        elem.add(cur);
        dfs(cur + 1, n, k, sum, elem, ret);
        elem.removeLast();
        dfs(cur + 1, n, k, sum, elem, ret);
        return ret;
    }
}
