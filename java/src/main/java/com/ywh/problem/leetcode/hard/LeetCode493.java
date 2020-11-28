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
     * @param left
     * @param right
     */
    private int mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) / 2;
        int ret = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

        // 统计下标对的数量。
        // [left, mid] 和 (mid, right] 分别是两个有序的数组。
        // 如果从前者中取出 i、后者取出 j，满足 nums[i] > 2 * nums[j]，则翻转对个数为 j - mid + 1。
        for (int l = left, r = mid + 1; l <= mid; l++) {
            while (r <= right && (long) nums[l] > 2 * (long) nums[r]) {
                r++;
            }
            ret += r - mid - 1;
        }

        // 合并两个排序数组。
        int[] sorted = new int[right - left + 1];
        int l = left, r = mid + 1, p = 0;
        while (l <= mid || r <= right) {
            if (l > mid) {
                sorted[p++] = nums[r++];
            } else if (r > right) {
                sorted[p++] = nums[l++];
            } else if(nums[l] < nums[r]) {
                sorted[p++] = nums[l++];
            } else {
                sorted[p++] = nums[r++];
            }
        }
        if (sorted.length >= 0) {
            System.arraycopy(sorted, 0, nums, left, sorted.length);
        }
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

    /**
     *
     * @param nums
     * @param low
     * @param mid
     * @param high
     * @param ret
     */
    private void merge(int[] nums, int low, int mid, int high, int[] ret) {
        int[] tmp = new int[nums.length];
        System.arraycopy(nums, low, tmp, low, high - low + 1);

        for (int i = low, j = mid + 1; i <= mid; i++) {
            while (j <= high && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
            }
            ret[0] += j - mid - 1;
        }

        // [low, mid] 和 [mid, high] 分别是两个有序的数组。
        // 如果从前者中取出 i、后者取出 j，满足 nums[i] > 2 * nums[j]。
        // 则对于 [i, mid] 内的任意值都满足 > 2 * nums[j]。
        for (int i = low, j = mid + 1, k = low; k <= high; k++) {
            if (i > mid) {
                nums[k] = tmp[j++];
            } else if (j > high) {
                nums[k] = tmp[i++];
            } else if (tmp[i] < tmp[j]) {
                nums[k] = tmp[i++];
            } else {
                nums[k] = tmp[j++];
            }
        }
    }

    /**
     *
     * @param nums
     * @param low
     * @param high
     */
    private void sort(int[] nums, int low, int high, int[] ret) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(nums, low, mid, ret);
        sort(nums, mid + 1, high, ret);
        merge(nums, low, mid, high, ret);
    }

    /**
     * TODO 归并排序解法 2（超时，待优化）
     *
     * @param nums
     * @return
     */
    public int reversePairsMergeSort2(int[] nums) {
        int[] ret = new int[1];
        sort(nums, 0, nums.length - 1, ret);
        return ret[0];
    }

    public static void main(String[] args) {
        LeetCode493 l = new LeetCode493();
        System.out.println(l.reversePairsMergeSort(new int[]{1,3,2,3,1}));
    }
}
