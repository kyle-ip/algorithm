package com.ywh.problem.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * [数组] [分治] [位操作]
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊n/2⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 示例 1：
 *      输入: [3,2,3]
 *      输出: 3
 * 示例 2：
 *      输入: [2,2,1,1,1,2,2]
 *      输出: 2
 * 进阶：
 *      尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * @author ywh
 * @since 2/17/2019
 */
public class LeetCode169 {

    /**
     * 使用一个 Map 记录每个元素出现的次数
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int getMajorityWithHashMap(int[] nums) {
        Map<Integer, Integer> cache = new HashMap<>(nums.length);
        int maxNum = 0, maxCount = 0;
        for (int num: nums) {
            int newCount = cache.getOrDefault(num, 0) + 1;
            cache.put(num, newCount);
            if (newCount > maxCount) {
                maxCount = newCount;
                maxNum = num;
            }
        }
        return maxNum;
    }


    /**
     * 使用变量 cur 记录当前出现次数最多的元素、count 记录其出现的次数；
     * 判断每次出现的元素：
     *      如果与 cur 相同，则 count + 1；
     *      如果与 cur 不同，则 count - 1；
     * 由于 count 必须大于等于 0，当 count 减至 0 时，后面出现其他元素的个数已超过 cur 累计个数，
     * 即此时 cur 出现的个数不可能超过总数一半，因此把 cur 重置。
     * （题设必然存在出现次数过半的元素）
     *
     * Time: O(n), Space: O(1)
     *
     * @param nums
     * @return
     */
    public int getMajority(int[] nums) {
        int cur = nums[0], cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            // 如果与 cur 相同，则 cnt++。
            if (nums[i] == cur) {
                cnt++;
            }
            // 否则 cnt--，减到 0 则重置。
            else if (--cnt == 0) {
                cnt = 1;
                cur = nums[i];
            }
        }
        return cur;
    }

}
