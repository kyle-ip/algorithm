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
    public int thirdMax(int[] nums) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;

        // 处理数组中存在 Integer.MIN_VALUE 的情况
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

}
