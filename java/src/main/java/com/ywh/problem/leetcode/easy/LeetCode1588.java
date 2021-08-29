package com.ywh.problem.leetcode.easy;

/**
 * 所有奇数长度子数组的和
 * [数组] [前缀和] [数学]
 * 
 * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
 * 子数组 定义为原数组中的一个连续子序列。
 * 请你返回 arr 中 所有奇数长度子数组的和 。
 * 示例 1：
 *      输入：arr = [1,4,2,5,3]
 *      输出：58
 *      解释：所有奇数长度子数组和它们的和为：
 *      [1] = 1
 *      [4] = 4
 *      [2] = 2
 *      [5] = 5
 *      [3] = 3
 *      [1,4,2] = 7
 *      [4,2,5] = 11
 *      [2,5,3] = 10
 *      [1,4,2,5,3] = 15
 *      我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 * 示例 2：
 *      输入：arr = [1,2]
 *      输出：3
 *      解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
 * 示例 3：
 *      输入：arr = [10,11,12]
 *      输出：66
 * 提示：
 *      1 <= arr.length <= 100
 *      1 <= arr[i] <= 1000
 * 
 * @author ywh
 * @since 8/29/2021
 */
public class LeetCode1588 {

    /**
     * Time: O(n^3), Space: O(1)
     *
     * @param arr
     * @return
     */
    public int sumOddLengthSubarraysBruteForce(int[] arr) {
        int sum = 0, n = arr.length;
        for (int start = 0; start < n; start++) {
            for (int length = 1; start + length <= n; length += 2) {
                for (int i = start; i < start + length; sum += arr[i], i++);
            }
        }
        return sum;
    }

    /**
     * Time: O(n^2), Space: O(n)
     *
     * @param arr
     * @return
     */
    public int sumOddLengthSubarraysPrefixSum(int[] arr) {
        int n = arr.length, sum = 0;
        int[] prefixSums = new int[n + 1];
        // prefixSums[i] 表示前 i 个元素之和。
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + arr[i];
        }
        for (int start = 0; start < n; start++) {
            for (int length = 1; start + length <= n; length += 2) {
                sum += prefixSums[start + length] - prefixSums[start];
            }
        }
        return sum;
    }


    /**
     * 每个元素都会在一或多个奇数长度的子数组中出现，计算出每个元素在多少个长度为奇数的子数组中出现，即可得到所有奇数长度子数组的和。
     *
     * Time: O(n), Space: O(1)
     *
     * @param arr
     * @return
     */
    public int sumOddLengthSubarraysMath(int[] arr) {
        int sum = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            // leftCount/rightCount 表示 arr[i] 左/右边的元素个数，再求出左/右边奇数、偶数的元素个数。
            int leftCount = i, rightCount = n - i - 1;
            int leftOdd = (leftCount + 1) / 2, rightOdd = (rightCount + 1) / 2;
            int leftEven = leftCount / 2 + 1, rightEven = rightCount / 2 + 1;
            sum += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
        }
        return sum;
    }
}
