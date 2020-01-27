package com.ywh.problem.leetcode.easy;

/**
 * 验证完全平方数
 * [数学] [二分搜索]
 *
 * @author ywh
 * @since 19/11/2019
 */
public class LeetCode367 {

    /**
     * Time: O(log(n)), Space: O(1)
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquareBinarySearch(int num) {
        long low = 0, high = num, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            long mid2 = mid * mid;
            if (mid2 == num) {
                return true;
            }
            if (mid2 < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public boolean isPerfectSquareNewton(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }
}
