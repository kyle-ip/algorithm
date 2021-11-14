package com.ywh.problem.leetcode.easy;

import java.util.*;

/**
 * 找到所有数组中消失的数字
 * [数组]
 *
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 示例:
 *      输入:
 *      [4,3,2,7,8,2,3,1]
 *
 *      输出:
 *      [5,6]
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