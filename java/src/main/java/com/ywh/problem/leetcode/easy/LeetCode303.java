package com.ywh.problem.leetcode.easy;

/**
 * 不可变数组的区间和查询
 * [数组] [动态规划]
 *
 * @author ywh
 * @since 04/04/2020
 */
public class LeetCode303 {

    public static class NumArrayImmutable {

        /**
         * prefixSum[i] 表示从 0 到 i - 1 的元素之和
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

    public static void main(String[] args) {
        NumArrayImmutable NAI = new NumArrayImmutable(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(NAI.sumRange(2, 5));
    }
}
