package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组的子集
 * [数组] [回溯] [位操作]
 *
 * @author ywh
 * @since 23/11/2019
 */
public class LeetCode78 {

    private void subsets(int[] nums, int start, List<Integer> elem, List<List<Integer>> result) {

        // 此处添加子集（其中第一次 elem 为空、添加的是空集）
        result.add(new ArrayList<>(elem));

        for (int i = start; i < nums.length; i++) {
            elem.add(nums[i]);
            subsets(nums, i + 1, elem, result);

            // 回溯，每次移除最后一个元素
            elem.remove(elem.size() - 1);
        }
    }


    /**
     * Time: O(2^n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsRecursive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        subsets(nums, 0, elem, result);
        return result;
    }

    /**
     * TODO 暂时未理解
     *
     * Time: O(n*2^n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsBit(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int N = (int) Math.pow(2, n);

        for (int i = 0; i < N; ++i) {
            List<Integer> elem = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if (((i >> j) & 1) == 1) {
                    elem.add(nums[j]);
                }
            }
            result.add(elem);
        }
        return result;
    }

}
