package com.ywh.problem.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 最长重复子数组
 * [数组] [哈希表] [二分查找] [动态规划]
 *
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例：
 *      输入：
 *          A: [1,2,3,2,1]
 *          B: [3,2,1,4,7]
 *      输出：3
 *      解释：
 *          长度最长的公共子数组是 [3, 2, 1] 。
 * 提示：
 *      1 <= len(A), len(B) <= 1000
 *      0 <= A[i], B[i] < 100
 *
 * @author ywh
 * @since 4/13/2021
 */
public class LeetCode718 {

    /**
     * 动态规划
     * Time: O(N*M), Space: O(N*M)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthDP(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length, ret = 0;
        // dp[i][j] 表示 A[i:] 和 B[j:] 的最长公共前缀的长度。
        // 如果 A[i] == B[j]，则 dp[i][j] = dp[i+1][j+1] + 1，否则 dp[i][j] = 0。
        int[][] dp = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = nums1[i] == nums2[j] ? dp[i + 1][j + 1] + 1 : 0;
                ret = Math.max(ret, dp[i][j]);
            }
        }
        return ret;
    }

    /**
     * 滑动窗口
     * Time: O((N+M)*min(N,M)), Space: O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthSlidingWindow(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, ret = 0;
        // 遍历 A，B 与之对齐，即：
        // A = [3, 6, 1, 2, 4]
        // B =    [7, 1, 2, 9]
        //            ↑  ↑
        //            i
        for (int i = 0; i < m; i++) {
            ret = Math.max(ret, maxLength(nums1, nums2, i, 0));
        }
        // 同理，遍历 B，A 与之对齐。
        for (int j = 0; j < n; j++) {
            ret = Math.max(ret, maxLength(nums1, nums2, 0, j));
        }
        return ret;
    }

    /**
     * 比较数组 A[addA:addA+len] 与数组 B[addB:addB+len] 的相同部分长度的最大值。
     *
     * @param nums1
     * @param nums2
     * @param startA
     * @param startB
     * @return
     */
    public int maxLength(int[] nums1, int[] nums2, int startA, int startB) {
        int ret = 0, m = nums1.length, n = nums2.length;
        for (int i = 0, len = 0; startA + i < m && startB + i < n; i++) {
            len = nums1[startA + i] == nums2[startB + i]? len + 1: 0;
            ret = Math.max(ret, len);
        }
        return ret;
    }

    int mod = 1000000009;

    int base = 113;

    /**
     * 哈希 + 二分查找
     * Time: O((M+N)*log(min(M,N))), Space: O(N)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLengthBinarySearch(int[] nums1, int[] nums2) {
        // 在二分查找的每一步中，使用哈希表分别存储这两个数组的所有长度为 len 的子数组的哈希值，
        // 将它们的哈希值进行比对，如果两序列存在相同的哈希值，则认为两序列存在相同的子数组。
        // 为了防止哈希碰撞，也可以在发现两个子数组的哈希值相等时，进一步校验它们本身是否确实相同以确保正确性。
        // 但该方法在本题中很难发生哈希碰撞，因此略去进一步校验的部分。
        int left = 1, right = Math.min(nums1.length, nums2.length) + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(nums1, nums2, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    /**
     * Rabin-Karp 算法求 hash。
     *
     * @param nums1
     * @param nums2
     * @param len
     * @return
     */
    public boolean check(int[] nums1, int[] nums2, int len) {
        long hashA = 0;
        for (int i = 0; i < len; i++) {
            hashA = (hashA * base + nums1[i]) % mod;
        }
        Set<Long> bucketA = new HashSet<>();
        bucketA.add(hashA);
        long mult = qPow(base, len - 1);
        for (int i = len; i < nums1.length; i++) {
            hashA = ((hashA - nums1[i - len] * mult % mod + mod) % mod * base + nums1[i]) % mod;
            bucketA.add(hashA);
        }
        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + nums2[i]) % mod;
        }
        if (bucketA.contains(hashB)) {
            return true;
        }
        for (int i = len; i < nums2.length; i++) {
            hashB = ((hashB - nums2[i - len] * mult % mod + mod) % mod * base + nums2[i]) % mod;
            if (bucketA.contains(hashB)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用快速幂计算 x^n % mod 的值
     *
     * @param x
     * @param n
     * @return
     */
    public long qPow(long x, long n) {
        long ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }
}
