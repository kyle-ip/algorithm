package com.ywh.problem.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 求和为给定值的两个数
 * [数组] [哈希表] [双指针]
 *
 * @author ywh
 * @since 2/13/2019
 */
public class LeetCode1 {

    /**
     * 两层循环，注意内层循环与外层错开 1 位，避免得出相同元素组合。
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] getTwoSumToTarget1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] getTwoSumToTarget2(int[] nums, int target) {
        // 缓存 Map，每轮循环先判断 Map 中是否存在目标差值。
        Map<Integer, Integer> cache = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int num2 = target - nums[i];

            // 存在则从 Map 中以 目标差值为 Key 取出对应下标，与当前下标一并返回。
            if (cache.containsKey(num2)) {
                return new int[]{cache.get(num2), i};
            }
            // 不存在则以当前元素为 Key、下标为 Value 存入 Map。
            cache.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /**
     * 先排序，再用双指针法求解。
     * 这种解法会乱序，忽略。
     *
     * Time: O(nlog(n)), Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] getTwoSumToTarget3(int[] nums, int target) {

        // O(nlog(n))
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1, sum;

        // O(n)
        while (left < right) {
            sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

}
