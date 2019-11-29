package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 求和为给定值的组合
 * [数组] [回溯]
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode39 {

    private void combSum(int[] nums, int target, int start, List<Integer> elem, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(elem));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            elem.add(nums[i]);
            combSum(nums, target - nums[i], i, elem, result);
            elem.remove(elem.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        combSum(candidates, target, 0, elem, result);
        return result;
    }

    private void combSumSort(int[] nums, int target, int start, List<Integer> elem, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(elem));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < nums.length; i++) {

            // 由于已排序，当前元素大于剩余目标值，剩余元素必然大于目标值，不需要继续拼凑
            if (nums[i] > target) {
                break;
            }
            elem.add(nums[i]);
            combSumSort(nums, target - nums[i], i, elem, result);
            elem.remove(elem.size() - 1);
        }
    }

    public List<List<Integer>> combinationSumSort(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        Arrays.sort(candidates);
        combSumSort(candidates, target, 0, elem, result);
        return result;
    }
}
