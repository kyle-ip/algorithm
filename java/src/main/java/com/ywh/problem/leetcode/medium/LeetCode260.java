package com.ywh.problem.leetcode.medium;

/**
 * 只出现一次的两个数字
 * TODO 暂时未理解
 *
 * @author ywh
 * @since 19/02/2020
 */
public class LeetCode260 {

    /**
     * 参考 {@link LeetCode540}
     * 相同数字异或为 0，
     * 把二进制表示最低位为 0 的数字分到一组，为 1 的分到另一组，
     * 由于相同的数字必然在同一组，所以每堆数字都只有一个数字只出现一次
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int[] singleNumberXOR(int[] nums) {
        int xor = 0, mask = 1;
        for (int num: nums) {
            xor ^= num;
        }
        while ((xor & mask) == 0) {
            mask <<= 1;
        }
        int x = 0, y = 0;
        for (int num: nums) {
            if ((num & mask) == 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int[] singleNumberXOR2(int[] nums) {
        int xor = 0;
        for (int num: nums) {
            xor ^= num;
        }
        int mask = xor & (-xor);
        int x = 0, y = 0;
        for (int num: nums) {
            if ((num & mask) == 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }

}
