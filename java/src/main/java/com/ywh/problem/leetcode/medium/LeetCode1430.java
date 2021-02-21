package com.ywh.problem.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 绝对差不超过限制的最长连续子数组
 * [数组] [滑动窗口]
 *
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。如果不存在满足条件的子数组，则返回 0 。
 * 示例 1：
 *      输入：nums = [8,2,4,7], limit = 4
 *      输出：2
 *      解释：所有子数组如下：
 *          [8] 最大绝对差 |8-8| = 0 <= 4.
 *          [8,2] 最大绝对差 |8-2| = 6 > 4.
 *          [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 *          [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 *          [2] 最大绝对差 |2-2| = 0 <= 4.
 *          [2,4] 最大绝对差 |2-4| = 2 <= 4.
 *          [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 *          [4] 最大绝对差 |4-4| = 0 <= 4.
 *          [4,7] 最大绝对差 |4-7| = 3 <= 4.
 *          [7] 最大绝对差 |7-7| = 0 <= 4.
 *          因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 *      输入：nums = [10,1,2,4,7,2], limit = 5
 *      输出：4
 *      解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 *      输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 *      输出：3
 * 提示：
 *      1 <= nums.length <= 10^5
 *      1 <= nums[i] <= 10^9
 *      0 <= limit <= 10^9
 *
 * TODO 暂时未理解
 *
 * @author ywh
 * @since 21/02/2021
 */
public class LeetCode1430 {

    /**
     * 滑动窗口 + 有序集合
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray1(int[] nums, int limit) {
        // 右端点枚举每一个位置，找到其对应的最靠左的左端点，满足区间中最大值与最小值之差不超过 limit。
        int n = nums.length, l = 0, r = 0, ret = 0;
        // 使用红黑树（按从小到大排序）统计当前窗口内的最大值与最小值。
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (; r < n; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            // 树中最大值与最小值之差大于 limit，移动 l。
            for (; map.lastKey() - map.firstKey() > limit; l++) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }

    /**
     * 滑动窗口 + 单调队列
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray2(int[] nums, int limit) {
        // 统计窗口中的最大值和最小值。
        Deque<Integer> queMax = new LinkedList<>(), queMin = new LinkedList<>();
        int n = nums.length, l = 0, r = 0, ret = 0;
        for (; r < n; r++) {
            // 如果 queMax 队尾值小于当前元素，弹出；queMin 队尾大于当前元素，弹出。
            for (; !queMax.isEmpty() && queMax.peekLast() < nums[r]; queMax.pollLast());
            for (; !queMin.isEmpty() && queMin.peekLast() > nums[r]; queMin.pollLast());
            // 两个队列分别添加当前元素。
            queMax.offerLast(nums[r]);
            queMin.offerLast(nums[r]);
            for (; !queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit; l++) {
                if (nums[l] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[l] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
            }
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }
}
