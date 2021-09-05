package com.ywh.problem.leetcode.hard;

/**
 * 求两个有序数组的中位数
 * [数组] [二分查找] [分治]
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * 示例 1：
 *      输入：nums1 = [1,3], nums2 = [2]
 *      输出：2.00000
 *      解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *      输入：nums1 = [1,2], nums2 = [3,4]
 *      输出：2.50000
 *      解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *      输入：nums1 = [0,0], nums2 = [0,0]
 *      输出：0.00000
 * 示例 4：
 *      输入：nums1 = [], nums2 = [1]
 *      输出：1.00000
 * 示例 5：
 *      输入：nums1 = [2], nums2 = []
 *      输出：2.00000
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
                findKthSmallestInSortedArrays(nums1, nums2, total / 2) + findKthSmallestInSortedArrays(nums1, nums2, total / 2 + 1)
            ) / 2;
        }
    }

    /**
     * 求 nums1 + nums2 中第 k 小的数
     * 每一步排除 k/2 个数，直到满足条件：
     * 1. k 减至 1，即第 1 小的数就是两个数组头部元素中的较小者；
     * 2. 把其中一个数组排除完，即第 k 小的数直接从剩余数组中取出即可。
     *
     * Time: O(log(k)) <= O(log(m+n)), Space: O(1)
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public double findKthSmallestInSortedArrays(int[] nums1, int[] nums2, int k) {

        // 每次排除 k/2 个数，即每轮操作中其中一个数组会丢弃 k/2 个数（通过改变下标偏移量和长度实现，避免创建新数组）。
        int len1 = nums1.length, len2 = nums2.length, base1 = 0, base2 = 0;

        // nums1: [2, 6, 8, 10], nums2: [1, 3, 5, 7, 9], k = 5
        while (true) {

            // len1/len2 为 0，表示 nums1/nums2 的数已被排除完，直接返回 nums2/nums1 子数组的第 k-1 个。
            if (len1 == 0) {
                return nums2[base2 + k - 1];
            }
            if (len2 == 0) {
                return nums1[base1 + k - 1];
            }
            // k 减至 1，即第 1 小的数就是两个数组头部元素中的较小者。
            if (k == 1) {
                return Math.min(nums1[base1], nums2[base2]);
            }

            // 从 nums1 取出 k/2 小的元素、从 nums2 取出 k - k/2 小的元素来判断。
            // k = 5, i = 2, j = 3
            // nums1: [2, 6, 8, 10], nums2: [1, 3, 5, 7, 9]
            //            a                           b
            int i = Math.min(k / 2, len1), j = Math.min(k - i, len2);
            int a = nums1[base1 + i - 1], b = nums2[base2 + j - 1];

            // 如果从两个数组截取的长度之和等于 k，且取出的元素值相等，则该元素为 nums1 + nums2 中第 k 小的元素，直接返回。
            if (i + j == k && a == b) {
                return a;
            }
            // 如果 a <= b，表示应该在 nums1 截取更多来判断（增大），此时要排除 nums1 前 i 个数：偏移量增加 i，长度减少 i 个，k 减少 i。
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
     * Time: O(m+n) Space: O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 填充最近两个值：两个指针都没有走到尽头，则较小者指针向后移动；其中一个指针已走到尽头，则取另一个指针并后移。
        int cur = 0, prev = 0, m = nums1.length, n = nums2.length;
        for (int i = 0, j = 0; i + j < (m + n) / 2 + 1; ) {
            prev = cur;
            if (i < m && j < n) {
                cur = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
            } else if (i < m) {
                cur = nums1[i++];
            } else if (j < n) {
                cur = nums2[j++];
            }
        }
        // 两数组之和长度如果为奇数，返回最后一个元素；如果为偶数，则返回最后两个元素的平均数。
        return (m + n) % 2 == 1? cur: (cur + prev) / 2.0;
    }

    /**
     * 划分数组：
     *
     * 在位置 i 为把长度为 m 的数组 A 分成两个部分，共有 m + 1 种划分方法。
     *      leftA: A[0:i-1], rightA: A[i:m-1]
     *
     * 同理可以划分长度为 n 的数组 B 为 leftB 和 rightB，把 leftA、leftB 放在一起，rightA、rightB 放在一起。
     *           left_part          |         right_part
     *     A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
     *     B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
     *
     * 如果 (n + m) % 2 == 0，则可以把 left_part 和 right_part 等分成两部分，而且有 max(left_part) <= min(right_part)
     * 中位数即 (max(left_part) + min(right_part)) / 2
     * 如果 (n + m) % 2 == 1，假设 len(left_part) == len(right_part) + 1，而且有 max(left_part) <= min(right_part)
     * 中位数即 max(left_part)
     *
     * 已知：
     *      i + j == m - i + n - j           m + n 为偶数
     *      i + j == m -j + n - j + 1        m + n 为奇数
     * 奇数和偶数的情况何以合并，移项可得：
     *      i + j == (m + n + 1) / 2
     * 假设 m <= n，则对于任意 i 属于 [0, m]，有 j == (m + n + 1) / 2 - i 属于 [0, n]；
     * 即在 [0, m] 范围内枚举 i 并得到 j，不需要额外的性质（如果 m > n，交换 A、B 即可）。
     * 另外还可以得知：
     *      B[j-1] <= A[i] 和 A[i-1] <= B[j]。
     *
     * 目的是：
     *      在 [0, m] 中找到 i，使得 B[j-1] <= A[i] 且 A[i-1] <= B[j]，其中 j == (m + n + 1) / 2 - i。
     * 可等价于：
     *      在 [0, m] 中找到最大的 i，使得 A[i-1] <= B[j]，其中 j == (m + n + 1) / 2 - i。
     *
     * 因此可以对 i 在 [0, m] 上进行二分查找，找到满足 A[i-1] <= B[j] 的最大的 i；
     * 此时划分前一部分元素中的最大值以及划分后一部分元素中的最小值，才可能作为就是这两个数组的中位数。
     *
     * TODO 暂时未理解
     *
     * Time: O(log(min(m, n))) Space: O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {

        // 如果 nums1 长度大于 nums2，则交换调用。
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays3(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length, left = 0, right = m;

        // median1 是前一部分的最大值，median2 是后一部分的最小值。
        int leftMax = 0, rightMin = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0:i-1] 和 nums2[0:j-1]，后一部分包含 nums1[i:m-1] 和 nums2[j:n-1]。
            int i = (left + right) / 2, j = (m + n + 1) / 2 - i;

            // numsIm1, numsI, numsJm1, numsJ 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]，对于越界的下标，置为正无穷和负无穷。
            int numsIm1 = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int numsI = i == m ? Integer.MAX_VALUE : nums1[i];
            int numsJm1 = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int numsJ = j == n ? Integer.MAX_VALUE : nums2[j];

            if (numsIm1 <= numsJ) {
                leftMax = Math.max(numsIm1, numsJm1);
                rightMin = Math.min(numsI, numsJ);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (leftMax + rightMin) / 2.0 : leftMax;
    }
}
