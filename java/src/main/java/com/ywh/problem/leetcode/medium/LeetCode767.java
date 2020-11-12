package com.ywh.problem.leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 重构字符串
 * [贪心] [堆] [字符串]
 *
 * @author ywh
 * @since 28/10/2020
 */
public class LeetCode767 {

    /**
     * TODO 暂时未理解
     *
     * 堆解法，分组填充，参考 {@link LeetCode621}
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
        for (int i = 0; i < S.length(); ++i) {
            ++freqs[S.charAt(i) - 'a'];
        }
        Queue<int[]> q = new PriorityQueue<>(
            (a, b) -> a[1] != b[1] ? b[1] - a[1] : a[0] - b[0]
        );

        for (int i = 0; i < freqs.length; ++i) {
            if (freqs[i] != 0) {
                q.add(new int[]{'a' + i, freqs[i]});
            }
        }

        StringBuilder sb = new StringBuilder();
        while (q.size() > 1) {
            int[] first = q.poll(), second = q.poll();
            sb.append((char) first[0]).append((char) second[0]);
            if (--first[1] > 0) {
                q.add(first);
            }
            if (--second[1] > 0) {
                q.add(second);
            }
        }
        if (q.isEmpty()) {
            return sb.toString();
        }
        int[] peek = q.poll();
        if (peek[1] != 1) {
            return "";
        }
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
