package com.ywh.problem.leetcode.medium;

import java.util.Arrays;

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
        boolean[] hash = new boolean[256];
        int left = 0, right = 0, maxLen = 0;
        while (right < s.length()) {
            // 如果右指针指向的值存在于 hash 中，表示字符已经出现重复，此时需要重置最长子串的统计：
            // 不断把左指针到“重复元素首次出现的位置”之间的值全部剔除，移动左指针到新的起始位置。
            while (hash[s.charAt(right)]) {
                hash[s.charAt(left++)] = false;
            }
            // 此时左指针与右指针之间的字符都没有重复，计算最大长度，并缓存右指针指向的值。
            maxLen = Math.max(maxLen, right - left + 1);
            hash[s.charAt(right++)] = true;
        }

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
        return maxLen;
    }

    /**
     * Time: O(n), Space: O(m), m 是字符集大小
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1N(String s) {
        // 哈希表，key 为 s 中的字符，value 为右指针所指元素上次出现的下标。
        int[] index = new int[256];
        Arrays.fill(index, -1);
        int maxLen = 0, left = 0, right = 0;
        for (; right < s.length(); right++) {
            // 每轮更新左指针：取当前位置与“右指针所指字符上次出现的位置 + 1”的较大者，表示必要时重置最长子串的统计。
            // 即假设 right 现在所指的字符 c 此前上次出现的位置是 2，则把 left 定在 3，再与当前的 right 计算距离。
            left = Math.max(left, index[s.charAt(right)] + 1);
            // 最大长度取“当前最大长度”与“左右指针相隔距离”中的较大者。
            maxLen = Math.max(maxLen, right - left + 1);
            index[s.charAt(right)] = right;
        }
        return maxLen;
    }
}
