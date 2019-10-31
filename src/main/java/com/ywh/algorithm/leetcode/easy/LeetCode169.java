package com.ywh.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中超过一半的数字
 * [数组] [分治] [位操作]
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
        if (nums.length < 2) {
            return -1;
        }
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
     * 如果与 cur 相同，则 count + 1；
     * 如果与 cur 不同，则 count - 1；
     * 由于 count 必须大于等于 0，因此当 count 减至 0 时，
     * 表示后面出现其他元素的个数已超过 cur 的累计个数，
     * 即此时 cur 出现的个数不可能超过总数一半，因此把 cur 重置；
     * （题设必然存在出现次数过半的元素）
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public int getMajority(int[] nums) {
        if (nums.length < 2) {
            return -1;
        }
        int cur = nums[0], count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == cur) {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    count = 1;
                    cur = nums[i];
                }
            }
        }
        return cur;
    }

}
