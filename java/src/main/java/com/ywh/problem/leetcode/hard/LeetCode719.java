package com.ywh.problem.leetcode.hard;

import java.util.Arrays;

/**
 * 找出第 k 小的距离对
 * [堆] [数组] [二分搜索]
 *
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * 示例 1:
 *      输入：
 *          nums = [1,3,1]
 *          k = 1
 *      输出：0
 *      解释：
 *          所有数对如下：
 *          (1,3) -> 2
 *          (1,1) -> 0
 *          (3,1) -> 2
 *          因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 提示:
 *      2 <= len(nums) <= 10000.
 *      0 <= nums[i] < 1000000.
 *      1 <= k <= len(nums) * (len(nums) - 1) / 2.
 *
 * https://leetcode-cn.com/problems/find-k-th-smallest-pair-distance/solution/hei-ming-dan-zhong-de-sui-ji-shu-by-leetcode/
 *
 * @author ywh
 * @since 4/7/2021
 */
public class LeetCode719 {

    /**
     * TODO 未完全理解
     *
     * Time: O(N*log(W)+N*log(N)), Space: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, low = 0, high = nums[n - 1] - nums[0];
        while (low < high) {
            int mid = low + (high - low) / 2, count = 0;
            for (int r = 0, l = 0; r < n; r++) {
                for (; nums[r] - nums[l] > mid; l++);
                // 此时 r、l 距离小于等于 mid，因此 l 与 r 之间元素个数可作为满足条件的数对个数。
                count += r - l;
            }
            // count = number of pairs with distance <= mi
            // 如果当前循环中数对数量大于等于 k，表示 mid 的左边数对数量偏多，向左折半，否则向右折半。
            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
