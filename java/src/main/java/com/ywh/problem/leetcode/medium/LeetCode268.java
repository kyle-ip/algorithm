package com.ywh.problem.leetcode.medium;

/**
 * 丢失的数字
 * [数组] [数学] [位操作]
 * 
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 进阶：
 *      你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 * 示例 1：
 *      输入：nums = [3,0,1]
 *      输出：2
 *      解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 *      输入：nums = [0,1]
 *      输出：2
 *      解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 *      输入：nums = [9,6,4,2,3,5,7,0,1]
 *      输出：8
 *      解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 *      输入：nums = [0]
 *      输出：1
 *      解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 * 提示：
 *      n == nums.length
 *      1 <= n <= 10^4
 *      0 <= nums[i] <= n
 *      nums 中的所有数字都独一无二
 *
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode268 {

    /**
     * 把 0~n 的数字异或，再与数组中的所有数字异或，得到的就是缺失的数字
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        int n = nums.length, ret = n;
        for (int i = 0; i < n; ++i) {
            ret = ret ^ i ^ nums[i];
        }
        return ret;

    }
}
