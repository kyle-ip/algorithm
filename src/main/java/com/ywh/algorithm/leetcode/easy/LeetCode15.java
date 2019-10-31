package com.ywh.algorithm.leetcode.easy;

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
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeNumSumToZeroOn2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int k = nums.length - 1;

        while (k >= 2) {

            // nums[k] 表示另外两数之和的相反数
            // 外循环中只需要判断 nums[k] >= 0 的部分，毕竟三个正数之和不可能为0
            if (nums[k] < 0) {
                break;
            }

            // 每轮循环中左右指针在 0 ~ k-1 范围内取值
            int target = -nums[k], left = 0, right = k - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    result.add(Arrays.asList(nums[left], nums[right], nums[k]));

                    // 跳过重复值
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                } else if (sum < target){
                    left++;
                } else {
                    right--;
                }
            }

            // 跳过重复值
            while (k >= 2 && nums[k - 1] == nums[k]) {
                k--;
            }
            k--;
        }

        return result;

    }
}
