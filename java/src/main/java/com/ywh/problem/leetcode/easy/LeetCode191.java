package com.ywh.problem.leetcode.easy;

/**
 * 二进制中 1 的个数
 * [位操作]
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode191 {

    /**
     * Time: O(m), Space: O(1)
     *
     * @param n
     * @return
     */
    public int numberOfOneWithMask(int n) {

        // 使用掩码（初始化为1），做按位与运算，结果不为0则计数器 +1
        int mask = 1, count = 0;

        // 循环，直到掩码为0
        while (mask != 0) {
            if ((n & mask) != 0) {
                ++count;
            }

            // 掩码左移、继续检查下一个二进制位
            mask <<= 1;
        }
        return count;

    }

    /**
     * Time: O(k), Space: O(1)
     *
     * @param n
     * @return
     */
    public int numberOfOne(int n) {

        // n & (n - 1) 的效果是把 n 的二进制表示中最低位的1消除：
        // 10：                  1010
        // 9：                   1001
        // 10 & 9 == 8：         1000
        int count = 0;
        while (n != 0) {
            ++count;
            n &= (n - 1);
        }
        return count;
    }
}
