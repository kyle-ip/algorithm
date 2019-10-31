package com.ywh.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 单身数字
 * [哈希表] [位操作]
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode136 {

    /**
     * 利用两数相同异或为0、两数不同异或为1的特性：
     * 只要把 result 初值设为0，与数组中的每个数求异或，相同的数异或结果为0，因此最终留下的就是单身数字
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumberWithXOR(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
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

        // sum 为所有元素之和，uniqSum 为元素去重后的和
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
