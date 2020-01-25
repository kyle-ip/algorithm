package com.ywh.problem.leetcode.easy;

/**
 * 合并两个有序数组
 * [双指针] [数组]
 *
 * @author ywh
 * @since 2/14/2019
 */
public class LeetCode88 {

    /**
     * 合并两个有序数组（合并结果存放在 nums1）
     *
     * @param nums1
     * @param m         nums1 实际长度
     * @param nums2
     * @param n         nums2 实际长度
     */
    public void mergeTwoSortedArray(int[] nums1, int m, int[] nums2, int n) {

        // 分别表示 nums1 的实际末尾、nums2 的实际末尾，总元素个数
        int i = m - 1, j = n - 1, count = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[count--] = nums1[i--];
            } else {
                nums1[count--] = nums2[j--];
            }
        }

        // 如果遍历到最后，nums2 的元素还未填充完，则把 nums2 的元素逐个复制到 nums1；
        // 相反，nums1 还未遍历完，由于数据最终是存放在 nums1，所以不需要遍历剩下的
        while (j >= 0) {
            nums1[count--] = nums2[j--];
        }
    }
}
