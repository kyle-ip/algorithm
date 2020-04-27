package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

/**
 * 拼接的最大数字
 * [排序]
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
        if (nums == null || nums.length == 0) {
            return "";
        }

        // 整型数组转换为字符串数组
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // 对字符串数组排序，规则为字典序的倒序：如比较 "12" 和 "8"，812 > 128，因此 8 排在 12 前面
        Arrays.sort(strs, (o1, o2) -> {
            String o12 = o1 + o2, o21 = o2 + o1;

            // 在 compare 函数中，对比 o12 和 o21 时 o12 较大，如希望 o1 排在 o2 前面，则需要返回负数，即 o21.compareTo(o12)
            return o21.compareTo(o12);
        });
        // 排序结果最大的是 "0"，表示数组中的元素全部都为 0
        if (strs[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param nums
     * @return
     */
    public String largestNumberFast(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
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
        if (strs[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String str: strs) {
            sb.append(str);
        }
        return sb.toString();
    }
}
