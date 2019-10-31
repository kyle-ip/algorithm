package com.ywh.algorithm.leetcode.easy;

/**
 * 缺失的数字
 * [数组] [数学] [位操作]
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode268 {

    /**
     * 把 0~n 的数字异或，再与数组中的所有数字异或，得到的就是缺失的数字
     * TODO 暂时未理解
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        int n = nums.length, result = n;
        for (int i = 0; i < n; ++i) {
            result = result ^ i ^ nums[i];
        }
        return result;

    }
}
