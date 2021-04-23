package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 有效三角形的个数
 * [数组]
 *
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * 示例 1:
 *      输入: [2,2,3,4]
 *      输出: 3
 *      解释:
 *          有效的组合是:
 *          2,3,4 (使用第一个 2)
 *          2,3,4 (使用第二个 2)
 *          2,2,3
 * 注意:
 *      数组长度不超过 1000。
 *      数组里整数的范围为 [0, 1000]。
 *
 * @author ywh
 * @since 4/23/2021
 */
public class LeetCode611 {

    /**
     * 排序 + 双指针
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        int ret = 0;
        Arrays.sort(nums);
        // 枚举第一条边 nums[i]。
        for (int i = 0; i < nums.length - 2; i++) {
            // 边长非 0。
            if (nums[i] == 0) {
                continue;
            }

            // 枚举第二条边 nums[j]。
            for (int j = i + 1, k = i + 2; j < nums.length - 1; j++) {

                // 第三条边初值设为 nums[i+2]，如果满足前两边之和大于第三边，移动 k。
                for (; k < nums.length && nums[i] + nums[j] > nums[k]; k++);

                // 由于数组有序，此时 nums[i]、nums[j] 之和正好小于等于 nums[k]（i ... j ...... k）。
                // [j, k-1] 的长度即为第三条边可取值的个数。
                ret += k - 1 - j;
            }
        }
        return ret;
    }
}
