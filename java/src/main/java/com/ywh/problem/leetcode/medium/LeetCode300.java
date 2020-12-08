package com.ywh.problem.leetcode.medium;

/**
 * 最长递增子序列的长度
 * [二分搜索] [动态规划]
 *
 * @author ywh
 * @since 2019/11/8/008
 */
public class LeetCode300 {

    /**
     * 动态规划解法
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLISDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, max = 1;

        // d[i] 表示从 0 到 i 递增元素递增的个数。
        int[] d = new int[n];
        d[0] = 1;

        // 遍历同时在嵌套循环中划分子数组
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                // 如果子数组当前元素小于子数组边界值，设为子数组当前元素位置的递增值 + 1，否则设为 1。

                // 对于子数组，逐个元素判断：
                //      如果小于上界，表示从 0 到 d[i] 有 cur = d[j] + 1 个元素递增，与 d[i] 取较大者；
                //      如果大于上界，表示子数组本轮遍历不递增，设为 1。
                int cur = nums[j] < nums[i]? d[j] + 1: 1;
                d[i] = Math.max(d[i], cur);
            }
            max = Math.max(max, d[i]);
        }
        return max;
    }

    /**
     * 二分搜索
     *
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLISBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length, len = 0;
        // 递增顺序数组。
        int[] d = new int[n];

        for (int num: nums) {
            int low = 0, high = len - 1, pos;
            // 二分搜索，在数组 d 的 [low, high] 之间找到 num 应该插入的位置，其中 [low, high] 表示最长递增子数组的范围。
            while (true) {
                if (low > high) {
                    pos = low;
                    break;
                }
                int mid = low + (high - low) / 2;
                if (num == d[mid]) {
                    pos = mid;
                    break;
                }
                if (num < d[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 插入 num，如果插入的位置为当前顺序数组长度（即从数组末尾插入），表示该元素是递增顺序插入的，长度 + 1。
            d[pos] = num;
            if (pos == len) {
                len++;
            }
        }
        return len;
    }
}
