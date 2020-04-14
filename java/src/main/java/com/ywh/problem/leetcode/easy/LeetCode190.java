package com.ywh.problem.leetcode.easy;

/**
 * 翻转整数的二进制位
 * [位操作]
 *
 * @author ywh
 * @since 14/04/2020
 */
public class LeetCode190 {

    /**
     * Time: O(1), Space: O(1)
     *
     * @param n
     * @return
     */
    public int reverseBitsOneByOne(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {

            // 按位与 & 可取出 n 的最低位
            // ret 左移 1 位
            // 按位或 | 可把 n 的最低位放在 ret 上，ret 不断左移、把低位移动到高位
            ret = ret << 1 | (n & 1);
            // n 处理完后，右移 1 位，
            n >>= 1;
        }
        return ret;
    }

    /**
     * Time: O(1), Space: O(1)
     *
     * @param n
     * @return
     */
    public int reverseBitsInterchanging(int n) {

        // 以连续的 16 个二进制位为一组，交换两组二进制位
        // 再以连续的 8 个二进制位为一组，交换两组二进制位
        n = (n & 0x0000FFFF) << 16 | (n & 0xFFFF0000) >>> 16;
        n = (n & 0x00FF00FF) << 8 | (n & 0xFF00FF00) >>> 8;
        n = (n & 0x0F0F0F0F) << 4 | (n & 0xF0F0F0F0) >>> 4;
        n = (n & 0x33333333) << 2 | (n & 0xCCCCCCCC) >>> 2;
        n = (n & 0x55555555) << 1 | (n & 0xAAAAAAAA) >>> 1;
        return n;
    }
}
