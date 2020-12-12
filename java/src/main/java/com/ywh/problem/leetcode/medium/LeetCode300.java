package com.ywh.problem.leetcode.medium;

/**
 * 最长上升子序列
 * [二分搜索] [动态规划]
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 *      输入: [10,9,2,5,3,7,101,18]
 *      输出: 4
 *      解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *      可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 *      你算法的时间复杂度应该为 O(n^2) 。
 * 进阶:
 *      你能将算法的时间复杂度降低到 O(nlog(n)) 吗?
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
        int[] dp = new int[n];
        dp[0] = 1;

        // 遍历同时在嵌套循环中划分子数组
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                // 对于子数组，逐个元素 nums[j] 判断：
                //      如果小于上界 nums[i]，表示从 0 到 d[i] 有 cur = dp[j] + 1 个元素递增，与 dp[i] 取较大者；
                //      如果大于上界 nums[i]，表示子数组本轮遍历不递增，设为 1。
                dp[i] = Math.max(dp[i], nums[j] < nums[i]? dp[j] + 1: 1);
            }
            max = Math.max(max, dp[i]);
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
