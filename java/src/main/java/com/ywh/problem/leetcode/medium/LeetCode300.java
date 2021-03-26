package com.ywh.problem.leetcode.medium;

import java.util.List;

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
 * 思路：
 *      使用动态规划方法求解，根据问题类型（线性/区间/树形，此处是区间）把问题规模减小：
 *      1. 每次减少一半：
 *          比如分为 [10,9,2,5] 和 [3,7,101,18]，最优解分别为 [2,5] 和 [3,7,101]，
 *          但没有好的组合方式将两个子问题最优解组合为原问题最优解 [2,5,7,101]。
 *      2. 每次减少一个：
 *         令 f[n] 表示以第 n 个数结尾的最长子序列，原问题分解为 f[n-1], f[n-2], ... f[1]：
 *             f[7]: [10, 9, 2, 5, 3, 7, 101] -> [2, 5, 7, 101]
 *             f[6]: [10, 9, 2, 5, 3, 7] -> [2, 5, 7]
 *             f[5]: [10, 9, 2, 5, 3] -> [2, 3]
 *             f[4]: [10, 9, 2, 5] -> [2, 5]
 *             f[3]: [10, 9, 2] -> [2]
 *             f[2]: [10, 9] -> [9]
 *             f[1]: [10] -> [10]
 *             f[7] 长度为 4，但末尾元素大于 18；f[6] 长度为 3，末尾元素小于 18。
 *         以上组合方式可得出状态转移方程：f[n] = max(f[i]) + 1，其中 i<n 且 a[i] < a[n]。
 * @author ywh
 * @since 2019/11/8/008
 */
public class LeetCode300 {

    /**
     * 采用“每次减少一个”的方式将原问题规模减小。
     * 每个子问题的答案可表示为上一个子问题的长度 +1。
     * 其中最大值更新的判断条件：当前位置元素不能大于上界：
     * 比如长度为 7 的子问题 f[7]，其中任意一个元素都不能大于上界 f[8]。
     *
     * @param nums      输入数组
     * @param upper     上界
     * @param dp        记忆化数组
     * @return          最长上升子序列的长度
     */
    public int recursive(int[] nums, int upper, int[] dp) {
        if (dp[upper] != 0) {
            return dp[upper];
        }
        int ret = 1;
        for (int j = 0; j < upper; j++) {
            if (nums[j] < nums[upper]) {
                ret = Math.max(ret, recursive(nums, j, dp) + 1);
            }
        }
        dp[upper] = ret;
        return ret;
    }

    /**
     * FIXME
     *
     * @param nums
     * @return
     */
    public int lengthOfLISRecursive(int[] nums) {
        return recursive(nums, nums.length - 1, new int[nums.length]);
    }

    /**
     * 动态规划解法
     *
     * Time: O(n^2), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int lengthOfLISDP(int[] nums) {
        int n = nums.length, max = 1;

        // dp[i] 表示从 0 到 i 递增元素递增的个数。
        int[] dp = new int[n];
        dp[0] = 1;

        // 遍历同时在嵌套循环中划分子数组
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 对于子数组，逐个元素 nums[j] 判断：
                //     小于上界 nums[i]，表示从 0 到 i 中有 dp[j]+1 个元素递增，与 dp[i] 取较大者；
                //     大于上界 nums[i]，表示子数组本轮遍历不递增，设为 1。
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
        int n = nums.length, len = 0;

        // FIXME
//        // 递增顺序数组。
//
//        List<Integer> f = new ArrayList<>();
//        f.add(nums[0]);
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] > f.get(f.size() - 1)) {
//                f.add(nums[i]);
//            } else {
//                binarySearch(f, nums[i]);
//            }
//        }
//        return f.size();


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

    /**
     *
     * @param f
     * @param target
     * @return
     */
    private int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1, mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (f.get(mid) < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
