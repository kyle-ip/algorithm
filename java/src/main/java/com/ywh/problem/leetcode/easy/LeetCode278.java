package com.ywh.problem.leetcode.easy;

/**
 * 第一个出错的版本
 * [二分查找]
 *
 * @author ywh
 * @since 22/04/2020
 */
public class LeetCode278 {

    /**
     * @return
     */
    public boolean isBadVersion(int n) {
        return true;
    }

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int low = 1, high = n, mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
