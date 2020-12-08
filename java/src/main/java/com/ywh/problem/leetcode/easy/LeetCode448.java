package com.ywh.problem.leetcode.easy;

import java.util.*;

/**
 * 缺失的所有数字
 * [数组]
 *
 * @author ywh
 * @since 2019/11/4/004
 */
public class LeetCode448 {

    /**
     * 下标 -> 值：+1
     * 值 -> 下标：-1
     *
     * 比如 [1, 2, 3, 4, 5]，a[0] == 1
     *
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<Integer> ret = new ArrayList<>();
        // boolean[] existed = new boolean[nums.length];
        Set<Integer> existed = new HashSet<>();

        for (int num: nums) {
            // existed[num - 1] = true;
            existed.add(num);
        }
//        for (int i = 0; i < existed.length; i++) {
//            if (!existed[i]) {
//                ret.add(i + 1);
//            }
//        }
        for (int i = 0; i < nums.length; i++) {
            if (!existed.contains(i + 1)) {
                ret.add(i + 1);
            }
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbersO1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<Integer> ret = new ArrayList<>();

        // 1, 1, 4, 2, 4, 6
        for (int i = 0; i < nums.length; i++) {

            // 以数组值为下标（-1 得到记录本次访问状态的值的位置）再访问数组。
            // 由于数值被置为负数表示已被访问，因此获取下标时需要 abs。
            int idx = Math.abs(nums[i]) - 1;

            // 如果该位置的值为正，则置为负数，表示该位置的数值是已被访问。
            if (nums[idx] > 0) {
                nums[idx] = -nums[idx];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}