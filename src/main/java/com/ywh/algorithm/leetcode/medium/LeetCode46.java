package com.ywh.algorithm.leetcode.medium;

import java.util.*;

/**
 * 数组的全排列
 * [回溯]
 *
 * @author ywh
 * @since 27/10/2019
 */
public class LeetCode46 {

    /**
     *
     * @param nums      当前的一个排列
     * @param start     开始下标（划分子数组）
     * @param result    全排列结果
     */
    private void permuteRec(List<Integer> nums, int start, List<List<Integer>> result) {

        // 当起始位置 == 排列长度，表示无需再对子数组排列
        if (start == nums.size()) {
            result.add(new ArrayList<>(nums));
        } else {
            // 把子数组的元素依次和 start 的位置交换（即固定到子数组的第一个位置）
            for (int i = start; i < nums.size(); i++) {
                Collections.swap(nums, i, start);

                // 递归地求从 start 开始的子数组的全排列
                permuteRec(nums, start + 1, result);

                // 每次递归结束都交换回来，开始新一轮的元素交换
                Collections.swap(nums, i, start);

            }
        }
    }

    /**
     * Time: O(n*n!), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for (int num: nums) {
            list.add(num);
        }
        permuteRec(list, 0, result);
        return result;
    }

}
