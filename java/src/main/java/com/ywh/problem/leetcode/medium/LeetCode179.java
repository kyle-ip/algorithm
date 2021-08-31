package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 最大数
 * [排序]
 * 
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 示例 1：
 *      输入：nums = [10,2]
 *      输出："210"
 * 示例 2：
 *      输入：nums = [3,30,34,5,9]
 *      输出："9534330"
 * 示例 3：
 *      输入：nums = [1]
 *      输出："1"
 * 示例 4：
 *      输入：nums = [10]
 *      输出："10"
 * 提示：
 *      1 <= nums.length <= 100
 *      0 <= nums[i] <= 10^9
 *
 * @author ywh
 * @since 27/04/2020
 */
public class LeetCode179 {

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {

        // 整型数组转换为字符串数组。
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; strs[i] = String.valueOf(nums[i]), i++);

        // 对字符串数组按字典序排倒序：如比较 "12" 和 "8"，812 > 128，因此 8 排在 12 前面。
        Arrays.sort(strs, (o1, o2) -> {
            String o12 = o1 + o2, o21 = o2 + o1;

            // 在 compare 函数中，对比 o12 和 o21 时 o12 较大，如希望 o1 排在 o2 前面，则需要返回负数，即 o21.compareTo(o12)。
            return o21.compareTo(o12);
        });
        // 排序结果最大的是 "0"，表示数组中的元素全部都为 0。
        return "0".equals(strs[0])? "0": String.join("", strs);
    }

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public String largestNumberFast(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; strs[i] = String.valueOf(nums[i]), i++);
        Arrays.sort(strs, (o1, o2) -> {
            int n1 = o1.length(), n2 = o2.length();
            for (int i = 0; i < n1 + n2; i++) {
                char c1 = i < n1? o1.charAt(i): o2.charAt(i - n1);
                char c2 = i < n2? o2.charAt(i): o1.charAt(i - n2);
                if (c1 == c2) {
                    continue;
                }
                return c2 - c1;
            }
            return 0;
        });
        return "0".equals(strs[0])? "0": String.join("", strs);
    }
}
