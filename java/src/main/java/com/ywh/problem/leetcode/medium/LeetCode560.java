package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为 K 的子数组
 * [哈希表] [数组]
 * 
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 示例 1 :
 *      输入:nums = [1,1,1], k = 2
 *      输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *      数组的长度为 [1, 20,000]。
 *      数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
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
     * 参考 {@link LeetCode437}
     *
     * 子数组之和转化为前缀和的差：
     *      sum(i~j) == sum(0~j) - sum(0~i-1) == k
     * 降维表示：  s[j]       s[i-1]
     *      长度为 k 的子数组之和可表示为：k == s[j] - s[i-1]
     *      因为 s[j] - k == s[i-1]，可以 s[j] 为 key，其出现次数 cnt 为 value 存放在哈希表。
     * 边界条件：当 i 取 0 时，sum(0~-1) == 0，s[-1] 前缀和为 0，因此需要把 key == 0，value == 1 事先存入哈希表。
     *
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumPrefixSum(int[] nums, int k) {

        // key 为前缀和，value 为前缀和出现的次数。
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        map.put(0, 1);

        // sum 为前缀和（子数组之和），cnt 为满足条件的子数组数量。
        int sum = 0, cnt = 0;
        for (int num : nums) {
            sum += num;

            // cnt 加上 sum-k 在哈希表中出现的次数：
            // 如果 sum-k 在哈希表中，表示之前已添加过前缀和，满足 sum新 - sum旧 表示的子数组之和等于 k。其出现次数为 value，添加到 cnt 上。
            //
            // [     ...     ]  num     sum
            // |<-             ->|
            // |<-     ->|<-   ->|
            //    sum-k      k
            cnt += map.getOrDefault(sum - k, 0);

            // 更新 sum 在哈希表中出现的次数：当前前缀和为 sum 要存放在哈希表中，如果已存在则数量 +1。
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}
