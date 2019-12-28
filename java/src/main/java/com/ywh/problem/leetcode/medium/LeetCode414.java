package com.ywh.problem.leetcode.medium;

/**
 * 第三大的数
 * [数组]
 *
 * @author ywh
 * @since 28/12/2019
 */
public class LeetCode414 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public static int thirdMax(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;
        boolean flag = true;
        int changeCount = 0;
        for (int num : nums) {
            if (num == Integer.MIN_VALUE && flag) {
                changeCount++;
                flag = false;
            }
            if (num > first) {
                changeCount++;
                third = second;
                second = first;
                first = num;
            } else if (num > second && num < first) {
                changeCount++;
                third = second;
                second = num;
            } else if (num > third && num < second) {
                changeCount++;
                third = num;
            }
        }
        return changeCount >= 3 ? third : first;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(thirdMax(nums));
        nums = new int[]{1, 2, Integer.MIN_VALUE};
        System.out.println(thirdMax(nums));

    }

}
