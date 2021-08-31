package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * [双指针] [数组]
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 * 示例 1：
 *      输入：nums = [1,0,-1,0,-2,2], target = 0
 *      输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 *      输入：nums = [], target = 0
 *      输出：[]
 * 提示：
 *      0 <= nums.length <= 200
 *      -10^9 <= nums[i] <= 10^9
 *      -10^9 <= target <= 10^9
 *
 * @author ywh
 * @since 30/11/2019
 */
public class LeetCode18 {

    /**
     * Time: O(n^3), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ret;
        }
        Arrays.sort(nums);

        // 第 1 个数
        for (int p = nums.length - 1; p >= 3; p--) {

            // 由于已排序，数字是从大到小遍历，当前元素的 4 倍小于 target，表示在更小的数中已无法找到四数之和为 target 的组合
            if (4 * nums[p] < target) {
                break;
            }

            // 第 2 个数
            for (int k = p - 1; k >= 2; k--) {

                // 与上同理，当前元素的 3 倍与第 1 个数之和小于 target，表示在更小的数中已无法找到四数之和为 target 的组合
                if (3 * nums[k] + nums[p] < target) {
                    break;
                }

                // 在最内层循环中使用双指针寻找第 3 和第 4 个数
                int l = 0, r = k - 1, newTarget = target - nums[k] - nums[p];
                while (l < r) {
                    if (nums[l] + nums[r] == newTarget) {
                        ret.add(Arrays.asList(nums[l], nums[r], nums[k], nums[p]));
                        for (; l < r && nums[l + 1] == nums[l]; l++);
                        l++;
                        for (; l < r && nums[r - 1] == nums[r]; r--);
                        r--;
                    } else if (nums[l] + nums[r] < newTarget) {
                        l++;
                    } else {
                        r--;
                    }
                }
                for (; k >= 2 && nums[k - 1] == nums[k]; k--);
            }
            for (; p >= 3 && nums[p - 1] == nums[p]; p--);
        }
        return ret;
    }

}
