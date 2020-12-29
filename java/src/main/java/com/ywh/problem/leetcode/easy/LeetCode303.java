package com.ywh.problem.leetcode.easy;

/**
 * 区域和检索 - 数组不可变
 * [数组] [动态规划]
 *
 * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 * 示例：
 *
 *      输入：
 *           ["NumArray", "sumRange", "sumRange", "sumRange"]
 *           [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 *      输出：
 *           [null, 1, -1, -3]
 * 解释：
 *      NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 *      numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 *      numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 *      numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 * 提示：
 *      0 <= nums.length <= 10^4
 *      -10^5 <= nums[i] <= 10^5
 *      0 <= i <= j < nums.length
 *      最多调用 104 次 sumRange 方法
 *
 * @author ywh
 * @since 04/04/2020
 */
public class LeetCode303 {

    public static class NumArrayImmutable {

        /**
         * prefixSum[i] 表示前 i 个元素之和。
         */
        private final int[] prefixSum;

        /**
         *
         * @param nums
         */
        public NumArrayImmutable(int[] nums) {
            // nums:        a1, a2, a3, ..., an
            // prefixSum:   0, a1, a1 + a2, ..., a1 + a2 + ... + an
            prefixSum = new int[nums.length + 1];
            for (int i = 1; i < prefixSum.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            }
        }

        /**
         * Time: O(1), Space: O(n)
         *
         * @param i
         * @param j
         * @return
         */
        public int sumRange(int i, int j) {
            // (a1 + a2 + a3 + ... + aj) - (a1 + a2 + a3 + ... a(i-1)) == ai + a(i+1) + ... + aj
            return prefixSum[j + 1] - prefixSum[i];
        }

    }
}
