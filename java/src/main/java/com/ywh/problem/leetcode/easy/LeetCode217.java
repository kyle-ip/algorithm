package com.ywh.problem.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素
 * [数组] [哈希表] [排序]
 *
 * @author ywh
 * @since 25/03/2020
 */
public class LeetCode217 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicateBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Time: O(n*log(n)), Space: O(1)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicateSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicateHashSet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
