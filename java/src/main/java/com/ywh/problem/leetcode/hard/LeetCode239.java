package com.ywh.problem.leetcode.hard;

import java.util.TreeMap;

/**
 * 滑动窗口中的最大值
 * [堆]
 *
 * @author ywh
 * @since 18/11/2019
 */
public class LeetCode239 {

    /**
     * 暴力解法，每 k 个元素算依次最大值，填充到结果数组
     *
     * Time: O(k*n), Space: O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxNumInSlidingWindowBruteForce(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] result = new int [n - k + 1];
        for (int left = 0; left < n - k + 1; left++) {
            int max = nums[left];
            for (int i = left; i < left + k; i++) {
                max = Math.max(max, nums[i]);
            }
            result[left] = max;
        }
        return result;
    }

    /**
     * 使用大小为 k 的红黑树保存滑动窗口内的数字：
     * 取最大值、移除窗口最左边的值、加入新值的时间复杂度都为 O(log(k))
     *
     * Time: O(n*log(k)), Space: O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxNumInSlidingWindowTreeMap(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length, p = 0;
        // key 为数组中的值，value 为下标，把前 k 个数字加到 map
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            map.put(nums[i], i);
        }
        int [] result = new int[n - k + 1];

        // map 默认从小到大排序，因此取最后一个
        result[p++] = map.lastKey();

        for (int i = k; i < n; i++) {
            // 由于数组中存在相等的值，在取出窗口最左边的数字对应下标时，要判断是否等于 i - k（即 ）
            // 如果相等，表示当前窗口最左边的数字，移除；如果不相等，表示已经被后来出现的相同数字覆盖，忽略
            if (map.get(nums[i - k]) == i - k) {
                map.remove(nums[i - k]);
            }

            // 把当前位置元素及下标加入窗口
            map.put(nums[i], i);

            // 取出最大值，填充结果数组
            result[p++] = map.lastKey();
        }
        return result;
    }

    /**
     * 使用两个分组辅助数组
     *
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxNumInSlidingWindowOn(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;

        // 结果数组，从左到右分组最大值，从右到左分组最大值
        int[] result = new int[n - k + 1], maxFromLeft = new int[n], maxFromRight = new int[n];

        // [0, 4, 2], [1, 0, 8], 2

        // [0, 4, 4], [1, 1, 8], 2
        maxFromLeft[0] = nums[0];

        // [4, 4, 2], [8, 8, 8], 2
        maxFromRight[n - 1] = nums[n - 1];

        // 从左到右、从右到左求分组长度为 k 的阶段最大值
        for (int i = 1, j = n - 2; i < n; i++, j--) {
            // 可以整除 k，表示分组内的第一个值，最大值为自身；否则与上一个最大值对比
            maxFromLeft[i] = i % k == 0? nums[i]: Math.max(maxFromLeft[i - 1], nums[i]);

            // 除以 k 余 k - 1，表示分组内最后一个值，最大值为自身；否则与上一个最大值对比
            maxFromRight[j] = j % k == k - 1? nums[j]: Math.max(maxFromRight[j + 1], nums[j]);
        }

        for (int i = 0; i < n - k + 1; i++) {
            // TODO 暂时未理解
            result[i] = Math.max(maxFromRight[i], maxFromLeft[i + k - 1]);
        }
        return result;
    }
}
