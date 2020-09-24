package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集 II
 * [回溯] [数组]
 *
 * @author ywh
 * @since 2020/9/24/024
 */
public class LeetCode90 {

    /**
     * Time: O(2^n), Space: O(n)
     *
     * @param nums
     * @param start
     * @param elem
     * @param ret
     */
    private void subsets(int[] nums, int start, LinkedList<Integer> elem, List<List<Integer>> ret) {
        ret.add(new ArrayList<>(elem));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            elem.add(nums[i]);
            subsets(nums, i + 1, elem, ret);
            elem.removeLast();
        }
    }

    /**
     * Time: O(2^n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        LinkedList<Integer> elem = new LinkedList<>();
        subsets(nums, 0, elem, ret);
        return ret;
    }
}
