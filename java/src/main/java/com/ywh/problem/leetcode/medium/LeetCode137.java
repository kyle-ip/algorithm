package com.ywh.problem.leetcode.medium;

/**
 * 只出现一次的数字 II
 * [位操作]
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 *      你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 *      输入: [2,2,3,2]
 *      输出: 3
 * 示例 2:
 *      输入: [0,1,0,1,0,1,99]
 *      输出: 99
 *
 * @author ywh
 * @since 2021/1/18/018
 */
public class LeetCode137 {

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumberSum(int[] nums) {
        int result = 0;
        for (int bit = 0; bit < 32; ++bit) {
            int sum = 0;
            for (int num: nums) {
                sum += (num >> bit) & 1;
            }
            if (sum % 3 == 1) {
                result |= (1 << bit);
            }
        }
        return result;
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumberBit(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int num: nums) {
            twos |= ones & num;
            ones ^= num;
            threes = ones & twos;
            // ones &= ~threes;
            ones -= threes;
            // twos &= ~threes;
            twos -= threes;
        }
        return ones;
    }
}
