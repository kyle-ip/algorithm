package com.ywh.algorithm.leetcode.medium;

import java.util.*;

/**
 * 相加等于 0 的三个数
 * [数组] [双指针]
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

        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[j] == 0) {
                        List<Integer> elem = Arrays.asList(nums[i], nums[j], nums[k]);
                        if (set.contains(elem)) {
                            set.add(elem);
                            result.add(elem);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 排序 + 双指针（注意每次成功判断后都要跳过重复值）
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeNumSumToZeroOn2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = nums.length - 1; k >= 2; k++) {
            int target = -nums[k], left = 0, right = k - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    left++;
                } else if (nums[left] + nums[right] > target) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[left], nums[right], nums[k]));

                    // 跳过重复值
                    for (; left < right && nums[left] == nums[left + 1]; left++);
                    for (; left < right && nums[right] == nums[right - 1]; right--);
                }
            }

            // 跳过重复值
            while (k >= 2 && nums[k] == nums[k - 1]) {
                k--;
            }
        }
        return result;
    }
}
