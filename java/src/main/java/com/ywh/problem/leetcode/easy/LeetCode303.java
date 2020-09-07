package com.ywh.problem.leetcode.easy;

/**
 * 不可变数组的区间和查询
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 04/04/2020
 */
public class LeetCode303 {

    public class NumArrayImmutable {

        /**
         * prefixSum[i] 表示从 0 到 i - 1 的元素之和
         */
        private final int[] prefixSum;

        /**
         *
         * @param nums
         */
        public NumArrayImmutable(int[] nums) {
            prefixSum = new int[nums.length + 1];
            for (int i = 1; i < prefixSum.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            }
        }

        /**
         *
         * @param i
         * @param j
         * @return
         */
        public int sumRange(int i, int j) {
            return prefixSum[j + 1] - prefixSum[i];
        }

    }
}
