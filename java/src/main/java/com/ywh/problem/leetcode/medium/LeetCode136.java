package com.ywh.problem.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 只出现一次的数字
 * [哈希表] [位操作]
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 *      你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1：
 *      输入: [2,2,1]
 *      输出: 1
 * 示例 2：
 *      输入: [4,1,2,1,2]
 *      输出: 4
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode136 {

    /**
     * 利用两数相同异或为 0、两数不同异或为 1（二进制位）的特性：
     * 只要把 ret 初值设为 0，与数组中的每个数求异或，相同的数异或结果为 0，因此最终留下的就是单身数字。
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumberWithXOR(int[] nums) {
        // 异或运算的性质：
        // 任何数和 0 做异或运算，结果仍然是原来的数，即 a ^ 0 = a
        // 任何数和其自身做异或运算，结果是 0，即 a ^ a = 0
        // 异或运算满足交换律和结合律，即 a ^ b ^ a = b ^ a ^ a = b ^ (a ^ a) = b ^ 0 = b。

        // 初始为 0，与任意数 a ^ 0 可得 a。
        // 如果 nums 中还存在 a，则下次 a ^ a 时就得到 0，相当于把出现的两个 a 消去。
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }
        return ret;
    }

    /**
     * 利用集合互异性求和，再与所有元素之和对比即可得出单身数字
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int singleNumberWithSet(int[] nums) {
        Set<Integer> set = new HashSet<>();

        // sum 为所有元素之和，uniqSum 为元素去重后的和。
        int sum = 0, uniqSum = 0;
        for (int num : nums) {
            if (!set.contains(num)) {
                uniqSum += sum;
                set.add(num);
            }
            sum += num;
        }
        return 2 * uniqSum - sum;
    }
}
