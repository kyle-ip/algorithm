package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 相加等于目标值的四个数
 * [双指针] [数组]
 *
 * @author ywh
 * @since 30/11/2019
 */
public class LeetCode18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);

        // 第 1 个数
        for (int p = nums.length - 1; p >= 3; p--) {

            // 由于已排序，数字是从大到小遍历，当前元素的 4 倍小于 target，表示在更小的数中已无法找到四数之和为 target 的组合
            if ( 4 * nums[p] < target) {
                break;
            }

            // 第 2 个数
            for (int k = p - 1; k >= 2; k++) {

                // 与上同理，当前元素的 3 倍与第 1 个数之和小于 target，表示在更小的数中已无法找到四数之和为 target 的组合
                if (3 * nums[k] + nums[p] < target) {
                    break;
                }

                // 在最内层循环中使用双指针寻找第 3 和第 4 个数
                int left = 0, right = k - 1, newTarget = target - nums[k] - nums[p];
                while (left < right) {
                    if (nums[left] + nums[right] == newTarget) {
                        result.add(Arrays.asList(nums[left], nums[right], nums[k], nums[p]));
                        while (left < right && nums[left + 1] == nums[left]) {
                            left++;
                        }
                        while (left < right && nums[right - 1] == nums[right]) {
                            right--;
                        }
                        // 最后一次移动，使指针落在不相等元素上
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < newTarget) {
                        left++;
                    } else {
                        right--;
                    }

                }
                while (k >= 2 && nums[k - 1] == nums[k]) {
                    k--;
                }
                while (p >= 3 && nums[p - 1] == nums[p]) {
                    p--;
                }
            }
        }
        return result;
    }

}
