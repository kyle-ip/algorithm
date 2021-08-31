package com.ywh.problem.leetcode.hard;

import com.ywh.problem.leetcode.medium.LeetCode139;

import java.util.*;

/**
 * 单词拆分 II
 *
 * [动态规划] [回溯]
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 *      分隔时可以重复使用字典中的单词。
 *      你可以假设字典中没有重复的单词。
 * 示例 1：
 *      输入:
 *          s = "catsanddog"
 *          wordDict = ["cat", "cats", "and", "sand", "dog"]
 *      输出:
 *          [
 *            "cats and dog",
 *            "cat sand dog"
 *          ]
 * 示例 2：
 *      输入:
 *          s = "pineapplepenapple"
 *          wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 *      输出:
 *          [
 *            "pine apple pen apple",
 *            "pineapple pen apple",
 *            "pine applepen apple"
 *          ]
 *      解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *      输入:
 *          s = "catsandog"
 *          wordDict = ["cats", "dog", "sand", "and", "cat"]
 *      输出:
 *          []
 * 
 * @author ywh
 * @since 2020/12/22/022
 */
public class LeetCode140 {

    /**
     * 参考 {@link LeetCode139}，利用 dp 剪枝。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 0; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n]? backtracking(s, 0, set, dp, new LinkedList<>(), new ArrayList<>()): Collections.emptyList();
    }

    /**
     *
     * @param s
     * @param start
     * @param set
     * @param dp
     * @param elem
     * @param ret
     */
    private List<String> backtracking(String s, int start, Set<String> set, boolean[] dp, LinkedList<String> elem, List<String> ret) {
        if (start == s.length()) {
            ret.add(String.join( " ", elem));
        } else {
            for (int i = start; i <= s.length(); i++) {
                String substring = s.substring(start, i);
                if (set.contains(substring) && dp[i]) {
                    elem.addLast(substring);
                    backtracking(s, i, set, dp, elem, ret);
                    elem.removeLast();
                }
            }
        }
        return ret;
    }
}
