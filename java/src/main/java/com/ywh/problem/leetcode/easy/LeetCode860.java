package com.ywh.problem.leetcode.easy;

/**
 * 柠檬水找零
 * [贪心]
 *
 * @author ywh
 * @since 2020/12/10/010
 */
public class LeetCode860 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0) {
            return true;
        }
        int cnt5 = 0, cnt10 = 0;

        for (int bill: bills) {
            if (bill == 5) {
                cnt5++;
            } else if (bill == 10) {
                if (cnt5-- == 0) {
                    return false;
                }
                cnt10++;
            } else {
                // 收到 20 元，找零 15 元，至少要有 1 张 5 元。
                if (cnt5-- == 0) {
                    return false;
                }
                // 如果剩余 10 元不足 1、5 元不足 2。
                if (cnt10 < 1 && cnt5 < 2) {
                    return false;
                }
                // 有 10 元优先用 10 元，否则用两张 5 元。
                if (cnt10 > 0) {
                    cnt10--;
                } else {
                    cnt5 -= 2;
                }
            }
        }
        return true;
    }

}
