package com.ywh.problem.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 平方数之和
 * [数学] [哈希表] [双指针]
 * 
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * 示例 1：
 *      输入：c = 5
 *      输出：true
 *      解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 *      输入：c = 3
 *      输出：false
 * 示例 3：
 *      输入：c = 4
 *      输出：true
 * 示例 4：
 *      输入：c = 2
 *      输出：true
 * 示例 5：
 *      输入：c = 1
 *      输出：true
 * 提示：
 *      0 <= c <= 2^31 - 1
 * 
 * @author ywh
 * @since 09/11/2019
 */
public class LeetCode633 {

    /**
     * 判断某个数开平方是否为整数
     *
     * @param num
     * @return
     */
    private boolean isSquare(int num) {
        int x = (int) Math.sqrt(num);
        return x * x == num;
    }

    /**
     * 逐个判断差值是否平方数
     *
     * Time: O(c^(1/2)), Space: O(1)
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int x = (int) Math.sqrt(c);
        for (int i = 0; i <= x; i++) {
            if (isSquare(c - i * i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 哈希表
     *
     * Time: O(c^(1/2)), Space: O(c^(1/2))
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSumHashSet(int c) {
        int x = (int) Math.sqrt(c);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= x; i++) {
            // 缓存平方数
            set.add(i * i);
            if (set.contains(c - i * i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 双指针
     *
     * Time: O(c^(1/2)), Space: O(1)
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSumTwoPointer(int c) {
        int i = 0, j = (int) Math.sqrt(c);
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum == c) {
                return true;
            }
            if (sum < c) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    /**
     * 平方和定理：
     * 对于一个大于 1 的整数，当且仅当它的质因数分解中，所有形如 4k + 3 的质因数都是偶数次幂，才可以表示成两个完全平方数的和
     *
     * Time: O(c^(1/2)), Space: O(1)
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSumMath(int c) {

        // 从最小的质数开始，遍历到 sqrt(c)
        for (int i = 2; i * i <= c; i++) {

            // 当前质因数出现的个数
            int cnt = 0;

            // 质因数分解
            while (c % i == 0) {
                ++cnt;
                c /= i;
            }

            // 可以写成 4k + 3，且当前质因数个数为奇数，表示奇数次幂，所以返回 false
            if (i % 4 == 3 && (cnt & 1) == 1) {
                return false;
            }
        }
        // 循环结束后可能存在一个大于 sqrt(c) 的质因数（被向下取整，比如 c == 14 时）
        // 最后一次判断时如果 c % 4 == 3，表示 1 次幂，返回 false
        return c % 4 != 3;
    }
}
