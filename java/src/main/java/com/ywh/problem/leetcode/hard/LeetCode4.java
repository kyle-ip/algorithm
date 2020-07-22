package com.ywh.problem.leetcode.hard;

/**
 * 求两个有序数组的中位数
 * [数组] [二分搜索] [分治]
 *
 * @author ywh
 * @since 2019/10/28
 */
public class LeetCode4 {

    /**
     * TODO 暂时未理解
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;

        // 总数为奇数
        if ((total & 1) == 1) {
            return findKthSmallestInSortedArrays(nums1, nums2, total / 2 + 1);
        }
        // 总数为偶数
        else {
            return (
                findKthSmallestInSortedArrays(nums1, nums2, total / 2)
                    + findKthSmallestInSortedArrays(nums1, nums2, total / 2 + 1)
            ) / 2;
        }
    }

    /**
     * 求 nums1 + nums2 中第 k 小的数
     * 每一步排除 k/2 个数，直到满足条件：
     * 1. k 减至 1，即第 1 小的数就是两个数组头部元素中的较小者；
     * 2. 把其中一个数组排除完，即第 k 小的数直接从剩余数组中取出即可。
     * <p>
     * Time: O(log(k)) <= O(log(m+n)), Space: O(1)
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public double findKthSmallestInSortedArrays(int[] nums1, int[] nums2, int k) {

        // 每次排除 k/2 个数，即每轮操作中其中一个数组会丢弃 k/2 个数
        // 丢弃通过改变下标偏移量和长度实现，避免创建新数组
        int len1 = nums1.length, len2 = nums2.length, base1 = 0, base2 = 0;

        while (true) {
            // k 减至 1，即第 1 小的数就是两个数组头部元素中的较小者
            if (k == 1) {
                return Math.min(nums1[base1], nums2[base2]);
            }

            // 如果 len1 为 0，表示 nums1 的数已被排除完，直接返回 nums2 子数组的第 k-1 个，len2 同理
            if (len1 == 0) {
                return nums2[base2 + k - 1];
            }
            if (len2 == 0) {
                return nums1[base1 + k - 1];
            }

            // 否则从 nums1 取出 k/2 小的元素、从 nums2 取出 k - k/2 小的元素来判断
            int i = Math.min(k / 2, len1);
            int j = Math.min(k - i, len2);
            int a = nums1[base1 + i - 1], b = nums2[base2 + j - 1];

            // 如果从两个数组截取的长度之和等于 k，且取出的元素值相等，则该元素为 nums1 + nums2 中第 k 小的元素，直接返回
            if (i + j == k && a == b) {
                return a;
            }
            // 如果 a <= b，表示应该在 nums1 截取更多来判断（增大）
            // 此时要排除 nums1 前 i 个数：偏移量增加 i，长度减少 i 个，k 减少 i
            if (a <= b) {
                base1 += i;
                len1 -= i;
                k -= i;
            }
            if (a >= b) {
                base2 += j;
                len2 -= j;
                k -= j;
            }
        }
    }

    /**
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int i = 0, j = 0, k = 0, size = nums1.length + nums2.length, half = size / 2 + 1;
        int[] nums = new int[size];
        while (k < half) {
            int n = 0;
            if (i < nums1.length && j < nums2.length) {
                n = (nums1[i] < nums2[j]) ? nums1[i++] : nums2[j++];
            } else if (i < nums1.length) {
                n = nums1[i++];
            } else if (j < nums2.length) {
                n = nums2[j++];
            }
            nums[k++] = n;
        }
        if (size % 2 == 1) {
            return nums[k - 1];
        } else {
            return (nums[k - 1] + nums[k - 2]) / 2.0;
        }
    }
}
