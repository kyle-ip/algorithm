package com.ywh.problem.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组的度
 * [数组]
 * 
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * 示例 1：
 *      输入：[1, 2, 2, 3, 1]
 *      输出：2
 *      解释：
 *          输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 *          连续子数组里面拥有相同度的有如下所示:
 *          [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 *          最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 *      输入：[1,2,2,3,1,4,2]
 *      输出：6
 * 提示：
 *      nums.length 在1到 50,000 区间范围内。
 *      nums[i] 是一个在 0 到 49,999 范围内的整数。
 *
 * @author ywh
 * @since 20/02/2021
 */
public class LeetCode697 {

    /**
     * 度即众数距离。
     *
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {

        // value 记录 num 出现次数、最早出现下标、最后出现下标
        Map<Integer, int[]> freqs = new HashMap<>();
        int maxCnt = 0, minLen = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (freqs.containsKey(nums[i])) {
                freqs.get(nums[i])[0]++;
                freqs.get(nums[i])[2] = i;
            } else {
                freqs.put(nums[i], new int[]{1, i, i});
            }
            // 更新众数。
            if (maxCnt < freqs.get(nums[i])[0]) {
                maxCnt = freqs.get(nums[i])[0];
            }
        }
        for (Map.Entry<Integer, int[]> entry : freqs.entrySet()) {
            int[] arr = entry.getValue();
            // 出现相同的众数，且众数距离更小：更新众数距离。
            if (maxCnt == arr[0] && minLen > arr[2] - arr[1] + 1) {
                minLen = arr[2] - arr[1] + 1;
            }
        }
        return minLen;
    }
}
