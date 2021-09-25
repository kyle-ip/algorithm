package com.ywh.problem.leetcode.easy;

/**
 * 寻找数组的平衡点
 * [数组]
 * 
 * 给你一个整数数组 nums ，请计算数组的 中心下标 。
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
 * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
 * 示例 1：
 *      输入：nums = [1, 7, 3, 6, 5, 6]
 *      输出：3
 *      解释：
 *      中心下标是 3 。
 *      左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
 *      右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
 * 示例 2：
 *      输入：nums = [1, 2, 3]
 *      输出：-1
 *      解释：
 *      数组中不存在满足此条件的中心下标。
 * 示例 3：
 *      输入：nums = [2, 1, -1]
 *      输出：0
 *      解释：
 *      中心下标是 0 。
 *      左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
 *      右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
 * 提示：
 *      1 <= nums.length <= 10^4
 *      -1000 <= nums[i] <= 1000
 *
 * @author ywh
 * @since 01/05/2020
 */
public class LeetCode724 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        // preSum[i] 表示前 i 个元素之和（即 0 ~ i - 1）
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[n - 1];
        }
        for (int i = 0; i < n; i++) {
            if (preSum[i] == preSum[n] - preSum[i  + 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int pivotIndexO1Space(int[] nums) {
        // sum 表示从 0 到 i 的元素之和，total 为所有元素之和
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        for (int i = 0, sum = 0; i < nums.length; i++) {
            // i == 5
            // total: s(0 ~ 9)
            // sum: s(0 ~ 4)
            // total - sum - nums[5]: s(6 ~ 9)
            if (total - sum - nums[i] == sum) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}