package com.ywh.problem.leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串分解
 * [动态规划]
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
                // 如果从 j 到 i 的子串存在于集合，且从 0 到 j 的子串可完成分解，则认为从 0 到 i 的子串可完成分解。
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
