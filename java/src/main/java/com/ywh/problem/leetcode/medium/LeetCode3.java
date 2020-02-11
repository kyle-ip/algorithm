package com.ywh.problem.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 没有重复字符的最长子串长度
 * [哈希表] [双指针] [字符串]
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode3 {

    /**
     * 滑动窗口
     * Time: O(n), Space: O(m), m 是字符集大小
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2N(String s) {
        Set<Character> set = new HashSet<>();
        int maxLen = 0, left = 0, right = 0;
        while (left < s.length() && right < s.length()) {
            if (!set.contains(s.charAt(right))){
                set.add(s.charAt(right++));
                maxLen = Math.max(maxLen, right - left);
            }
            else {
                set.remove(s.charAt(left++));
            }
        }
        return maxLen;

//        int left = 0, right = 0, maxLen = 0;
//
//        // 记录出现字符的次数的 Hash
//        int[] counts = new int[256];
//        for (; left < s.length(); left++) {
//            for (; right < s.length(); right++) {
//
//                // 区间中的字符重复出现在右端点，则退出内层循环；
//                if (counts[s.charAt(right)] != 0) {
//                    break;
//                }
//                counts[s.charAt(right)]++;
//            }
//            maxLen = Math.max(maxLen, right - left);
//
//            // 区间向前滑动，最左边的字符出现次数 - 1
//            counts[s.charAt(left)] -= 1;
//        }
//        return maxLen;
    }

    /**
     * 方法1的优化版本
     * TODO 暂时未理解
     * Time: O(n), Space: O(m), m 是字符集大小
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1N(String s) {
        int[] index = new int[256];
        Arrays.fill(index, -1);
        int maxLen = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            left = Math.max(index[s.charAt(right)] + 1, left);
            maxLen = Math.max(maxLen, right - left + 1);
            index[s.charAt(right)] = right;
        }
        return maxLen;
    }
}
