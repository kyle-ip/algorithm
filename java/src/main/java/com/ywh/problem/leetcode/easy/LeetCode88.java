package com.ywh.problem.leetcode.easy;

/**
 * 合并两个有序数组
 * [双指针] [数组]
 * 
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明：
 *      初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 *      你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例：
 *      输入：
 *      nums1 = [1,2,3,0,0,0], m = 3
 *      nums2 = [2,5,6],       n = 3
 *      输出：[1,2,2,3,5,6]
 * 提示：
 *      -10^9 <= nums1[i], nums2[i] <= 10^9
 *      nums1.length == m + n
 *      nums2.length == n
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
        // 分别表示 nums1 的实际末尾、nums2 的实际末尾，总元素个数。
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] > nums2[j]? nums1[i--]: nums2[j--];
        }
        // 如果遍历到最后，nums2 的元素还未填充完，则把 nums2 的元素逐个复制到 nums1；
        // 否则是 nums1 还未遍历完，由于数据最终存放在 nums1，不需要遍历剩下的。
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
