package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和 II
 * [数组] [回溯]
 *
 * @author ywh
 * @since 06/09/2020
 */
public class LeetCode40 {

    /**
     *
     *
     * @param nums
     * @param target
     * @param start
     * @param elem
     * @param result
     */
    private void combSum(int[] nums, int target, int start, LinkedList<Integer> elem, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(elem));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 当前元素与上一个元素相同，跳过。
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            elem.add(nums[i]);
            combSum(nums, target - nums[i], i + 1, elem, result);
            elem.removeLast();
        }
    }

    /**
     * 类似 {@link LeetCode39}，需要先对数组排序、在每轮回溯中要对元素作判断（是否与上一个重复）。
     *
     * Time: O(n^(target/min)), Space: O(target/min)
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combSum(candidates, target, 0, new LinkedList<>(), result);
        return result;
    }

}
