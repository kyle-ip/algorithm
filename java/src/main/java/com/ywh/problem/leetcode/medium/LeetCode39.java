package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和
 * [数组] [回溯]
 *
 * @author ywh
 * @since 2019/11/29/029
 */
public class LeetCode39 {

    /**
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
            elem.add(nums[i]);
            combSum(nums, target - nums[i], i, elem, result);
            elem.removeLast();
        }
    }

    /**
     * Time: O(n^(target/min)), Space: O(target/min)
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combSum(candidates, target, 0, new LinkedList<>(), result);
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

    /**
     * Time: O(n^(target/min)), Space: O(target/min)
     * 
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumSort(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        Arrays.sort(candidates);
        combSumSort(candidates, target, 0, elem, result);
        return result;
    }
}
