package com.ywh.problem.leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串分解
 * [动态规划]
 *
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 *      拆分时可以重复使用字典中的单词。
 *      你可以假设字典中没有重复的单词。
 * 示例 1：
 *      输入: s = "leetcode", wordDict = ["leet", "code"]
 *      输出: true
 *      解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *      输入: s = "applepenapple", wordDict = ["apple", "pen"]
 *      输出: true
 *      解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *           注意你可以重复使用字典中的单词。
 * 示例 3：
 *      输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 *      输出: false
 *
 * @author ywh
 * @since 02/11/2019
 */
public class LeetCode139 {

    /**
     * Time: O(n^2), Space: O(n+m)
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean stringBreak(String s, List<String> wordDict) {
        int n = s.length();

        // dp[i] 表示从 0 到 i - 1 的子串是否能分解成若干个子串且存在于字符串列表。
        // 比如“a p p l e p e n”，dp[8] 即整个字符串，可以分解为“apple” 和 “pen”，且都存在于 wordDict 中，因此 dp[8] == true。
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // 把词典转换为集合。
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 如果 [j, i) 的子串存在于集合，且 [0, j) 的子串可完成分解，则认为 [0, i) 可完成分解。
                // 而且从 0 到 i 已经找到一个合法的划分，因此还可以 break 内部循环。
                //      a p p l e p e n a p p l e
                //                j     i
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
