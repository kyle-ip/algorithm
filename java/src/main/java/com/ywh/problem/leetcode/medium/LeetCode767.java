package com.ywh.problem.leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 重构字符串
 * [贪心] [堆] [字符串]
 *
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * 示例 1:
 *      输入: S = "aab"
 *      输出: "aba"
 * 示例 2:
 *      输入: S = "aaab"
 *      输出: ""
 * 注意:
 *      S 只包含小写字母并且长度在[1, 500]区间内。
 *
 * @author ywh
 * @since 28/10/2020
 */
public class LeetCode767 {

    /**
     * 优先级队列解法，分组填充，参考 {@link LeetCode621}
     * 为了使相邻的字符不同，每次以两个位置为一组，选取两个不同的字符进行填充。
     * 选取规则：优先取数量较多的字符，免得堆积到最后导致相同的字符只能排在一起；
     * 而如果字符的数量相同，优先选取字典序较小的字符。
     *
     * Time: O(n), Space: O(n)
     *
     * @param S
     * @return
     */
    public String reorganizeStringMaxHeap(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        int[] freqs = new int[26];

        // 统计每个字符的数量。
        for (char c : S.toCharArray()) {
            ++freqs[c - 'a'];
        }

        // 优先级队列：数量较多的排在前面，数量相等时字典序较小的排在前面。
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[1] != b[1] ? b[1] - a[1] : a[0] - b[0]);

        // 把字符及其数量入队。
        for (int i = 0; i < freqs.length; ++i) {
            if (freqs[i] != 0) {
                q.add(new int[]{'a' + i, freqs[i]});
            }
        }

        StringBuilder sb = new StringBuilder();
        while (q.size() > 1) {
            // 取出数量最多的两个字母，拼接在一起。
            int[] first = q.poll(), second = q.poll();
            sb.append((char) first[0]).append((char) second[0]);

            // 如果该字母还未用完，重新添加到队列中。
            if (--first[1] > 0) {
                q.add(first);
            }
            if (--second[1] > 0) {
                q.add(second);
            }
        }

        // 如果队空则直接返回。
        if (q.isEmpty()) {
            return sb.toString();
        }

        // 判断剩余的字母，如果数量大于 1，表示这两个字母最后必定相邻，因此无法凑成目标字符串，返回空串。
        int[] peek = q.poll();
        if (peek[1] != 1) {
            return "";
        }
        // 否则拼接最后一个字符后返回。
        return sb.append((char) peek[0]).toString();
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param S
     * @return
     */
    public String reorganizeStringSkipFill(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        int[] freqs = new int[26];
        for (int i = 0; i < S.length(); ++i) {
            ++freqs[S.charAt(i) - 'a'];
        }
        int maxFreqId = 0;
        for (int i = 1; i < freqs.length; ++i) {
            if (freqs[i] > freqs[maxFreqId]) {
                maxFreqId = i;
            }
        }
        int n = S.length();
        if (freqs[maxFreqId] > (n + 1) / 2) {
            return "";
        }

        char[] result = new char[n];
        char maxFreqChar = (char) ('a' + maxFreqId);
        int p = 0;
        while (freqs[maxFreqId] > 0) {
            result[p] = maxFreqChar;
            p += 2;
            --freqs[maxFreqId];
        }
        for (int i = 0; i < freqs.length; ++i) {
            char c = (char) ('a' + i);
            while (freqs[i] > 0) {
                if (p >= n) {
                    p = 1;
                }
                result[p] = c;
                p += 2;
                --freqs[i];
            }
        }
        return new String(result);
    }

}
