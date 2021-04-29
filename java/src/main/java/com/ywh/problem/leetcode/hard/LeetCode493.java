package com.ywh.problem.leetcode.hard;

import com.ywh.ds.tree.BinaryIndexedTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 翻转对
 * [排序] [树状数组] [线段树] [二分搜素]
 * 
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 * 示例 1:
 *      输入: [1,3,2,3,1]
 *      输出: 2
 * 示例 2:
 *      输入: [2,4,3,5,1]
 *      输出: 3
 * 注意:
 *      给定数组的长度不会超过50000。
 *      输入数组中的所有数字都在32位整数的表示范围内。
 * 
 * @author ywh
 * @since 2020/11/28/028
 */
public class LeetCode493 {

    /**
     * @param nums
     * @return
     */
    public int reversePairsBruteForce(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((float) nums[i] / 2 > nums[j]) {
                    ret++;
                }
            }
        }
        return ret;
    }

    /**
     *
     * @param nums
     * @param low
     * @param high
     */
    private int mergeSort(int[] nums, int low, int high) {
        if (low == high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int ret = mergeSort(nums, low, mid) + mergeSort(nums, mid + 1, high);

        // 统计下标对的数量。
        // [low, mid] 和 (mid, high] 分别是两个有序的数组。
        // 如果从前者中取出 i、后者取出 j，满足 nums[i] > 2 * nums[j]，则翻转对个数为 j-mid+1。
        for (int l = low, r = mid + 1; l <= mid; l++) {
            while (r <= high && (long) nums[l] > 2 * (long) nums[r]) {
                r++;
            }
            ret += r - mid - 1;
        }

        // 合并两个排序数组。
        int[] sorted = new int[high - low + 1];
        int l = low, r = mid + 1, p = 0;
        for (; l <= mid || r <= high; p++) {
            if (l > mid) {
                sorted[p] = nums[r++];
            } else if (r > high) {
                sorted[p] = nums[l++];
            } else if(nums[l] < nums[r]) {
                sorted[p] = nums[l++];
            } else {
                sorted[p] = nums[r++];
            }
        }
        System.arraycopy(sorted, 0, nums, low, sorted.length);
        return ret;
    }

    /**
     * 归并排序解法
     *
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int reversePairsMergeSort(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }

    /**
     * 树状数组解法
     * TODO 暂时未理解
     *
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int reversePairsBit(int[] nums) {
        Set<Long> allNumbers = new TreeSet<>();
        for (int x : nums) {
            allNumbers.add((long) x);
            allNumbers.add((long) x * 2);
        }
        // 利用哈希表进行离散化。
        Map<Long, Integer> values = new HashMap<>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        BinaryIndexedTree bit = new BinaryIndexedTree(values.size());
        for (int num : nums) {
            int left = values.get((long) num * 2), right = values.size() - 1;
            ret += bit.query(right + 1) - bit.query(left + 1);
            bit.update(values.get((long) num) + 1, 1);
        }
        return ret;
    }

}
