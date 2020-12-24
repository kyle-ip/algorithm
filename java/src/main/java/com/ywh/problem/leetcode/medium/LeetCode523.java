package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 子数组求和是否为 K 的整数倍
 * [数学]
 *
 * @author ywh
 * @since 20/04/2020
 */
public class LeetCode523 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumBruteForce(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < n; j++) {
                sum += nums[j];
                if ((k == 0 && sum == 0) || k != 0 && sum % k == 0) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 同余：当两个整数除以同一个数字，得到的余数相同：a % k == b % k；
     * 如果 a 与 b 同余，则 a - b == m * k
     * s[i] - s[j] 表示 nums[j+1] ~ nums[i] 之和
     *
     * Time: O(n), Space: O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumMod(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        // key：前缀和对 k 取模的结果
        // value：前缀和的右边界下标（由于数组长度至少为 2，因此需要判断两个右边界之差是否大于 1）
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = k == 0 ? sum : sum % k;
            Integer j = map.get(mod);
            if (j != null) {
                if (i - j > 1) {
                    return true;
                }
            } else {
                map.put(mod, i);
            }
        }
        return false;
    }
}
