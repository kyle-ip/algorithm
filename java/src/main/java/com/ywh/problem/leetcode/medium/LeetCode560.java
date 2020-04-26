package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 求和等于 K 的子数组数量
 * [哈希表] [数组]
 *
 * 参考 {@link LeetCode437}
 *
 * @author ywh
 * @since 23/04/2020
 */
public class LeetCode560 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumBruteForce(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int cnt = 0, n = nums.length, sum;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * TODO 暂时未理解
     *
     * sum(i~j) == sum(0~j) - sum(0~i-1) == s(j) - s(i-1)，前缀和之差
     *
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumPrefixSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 使用哈希表存放满足条件的前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        // 累加和
        int sum = 0, cnt = 0, sumCnt;
        for (int num : nums) {
            sum += num;
            cnt += map.getOrDefault(sum - k, 0);
            sumCnt = map.getOrDefault(sum, 0);
            map.put(sum, sumCnt + 1);
        }
        return cnt;
    }
}
