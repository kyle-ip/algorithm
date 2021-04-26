package com.ywh.problem.leetcode.medium;

/**
 * 长度最小的子数组
 * [数组] [双指针] [二分查找]
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 * 示例：
 *      输入：s = 7, nums = [2,3,1,2,4,3]
 *      输出：2
 *      解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 进阶：
 *      如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n*log(n)) 时间复杂度的解法。
 * 提示：
 *      1 <= target <= 10^9
 *      1 <= nums.length <= 10^5
 *      1 <= nums[i] <= 10^5
 * 进阶：
 *      如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(nlog(n)) 时间复杂度的解法。
 *
 * @author ywh
 * @since 2020/9/14/014
 */
public class LeetCode209 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int cnt = Integer.MAX_VALUE ,n = nums.length;
        for (int l = 0, r = 0, sum = 0; r < n; r++) {
            // 每轮循环把最右边的元素添加到 sum 中。
            sum += nums[r];

            // 判断是否已满足题目中 sum 大于等于 s 的条件，是则计算一次 cnt，并减去最左边的值（表示计算下一个连续子数组的和）。
            // 由于存在最右边的元素过大、导致算入 nums[r] 后超出 s 很多的情况（即减去 nums[l] 后仍然大于 s），因此要使用 while。
            while (sum >= target) {
                cnt = Math.min(cnt, r - l + 1);
                sum -= nums[l++];
            }
        }

        // 如果 cnt 一次都未被计算（仍为初始化的值），即所有元素之和小于 s，依题目要求不满足条件时返回 0。
        return cnt == Integer.MAX_VALUE? 0: cnt;
    }

    /**
     * 二分查找，如果找不到 target，则返回待插入的位置。
     *
     * @param nums
     * @param target
     * @return
     */
    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        int ret = Integer.MAX_VALUE;
        int[] prefixSum = new int[n + 1];
        // sum[i] 表示前 i 个元素之和。
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + prefixSum[i - 1];
            int bound = binarySearch(prefixSum, target);
            if (bound <= n) {
                ret = Math.min(ret, bound - (i - 1));
            }
        }
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
}
