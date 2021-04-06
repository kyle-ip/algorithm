package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 三数之和
 * [数组] [双指针]
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 *      给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *      满足要求的三元组集合为：
 *          [
 *            [-1, 0, 1],
 *            [-1, -1, 2]
 *          ]
 *
 * @author ywh
 * @since 2019/2/21
 */
public class LeetCode15 {

    /**
     * 三层循环
     *
     * Time: O(n^3), Space: O(n)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeNumSumToZeroOn3(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> elem = Arrays.asList(nums[i], nums[j], nums[k]);
                        if (!set.contains(elem)) {
                            ret.add(elem);
                        }
                        set.add(elem);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 排序 + 双指针（注意每次成功判断后都要跳过重复值）
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeNumSumToZeroOn(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = nums.length - 1; k >= 2; k--) {
            // 如果 nums[k] 已经小于 0，则其左边都是负数，三个负数之和不可能为 0。
            if (nums[k] < 0) {
                break;
            }
            for (int l = 0, r = k - 1; l < r; ) {
                if (nums[l] + nums[r] + nums[k] < 0) {
                    l++;
                } else if (nums[l] + nums[r] + nums[k] > 0) {
                    r--;
                } else {
                    ret.add(Arrays.asList(nums[l], nums[r], nums[k]));
                    // 跳过重复值。
                    for (; l < r && nums[l] == nums[l + 1]; l++) ;
                    // 1 2 [3] 3 4 5 5 6 [6]
                    //         l       r
                    l++;
                    for (; l < r && nums[r] == nums[r - 1]; r--) ;
                    // 1 2 3 3 4 5 5 6 6
                    //         l   r
                    r--;
                }
            }

            // 跳过重复值。
            for (; k >= 2 && nums[k] == nums[k - 1]; k--) ;
        }
        return ret;
    }
}
