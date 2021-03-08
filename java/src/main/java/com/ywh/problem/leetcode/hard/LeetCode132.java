package com.ywh.problem.leetcode.hard;

/**
 * 分割回文串 II
 * [动态规划]
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的最少分割次数。
 * 示例:
 *      输入: "aab"
 *      输出: 1
 *      解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * @author ywh
 * @since 2020/10/21/021
 */
public class LeetCode132 {

    /**
     *
     * @param s
     * @param i
     * @param j
     * @param cut
     */
    private void expand(String s, int i, int j, int[] cut) {
        // 比如 s 为 a a b a c，入参 i、j 都是 2。
        // a b a 是回文串，因此有两种分割方法：
        //      a | a [b] a c     在前面分割，表示要在前面遍历（[b] 前）已经处理过的分割中再补充“一刀”，即 cut[i]+1。
        //          i     j
        //      a a [b] a | c     在后面分割，表示在“下刀”的位置之前不需要做额外处理，因此是 cut[j+1]。
        //        i     j  j+1
        // 在两种方法中取次数较少的一种。
        for (; i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j); i--, j++) {
            cut[j + 1] = Math.min(cut[i] + 1, cut[j + 1]);
        }
    }

    /**
     * Time: O(n^2), Space: O(n)
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        // cut[i] 表示 s[0:i] 的子串分割成回文子串所需要的最少分割次数（长度为 i）。
        int[] cut = new int[n + 1];

        // 把 cut[i] 初始化为最差情况：假定每个字符都不同，长度为 i 的字符串需要切割 i-1 次。
        // a: 0
        // a | b: 1
        // a | b | c: 2
        // ...
        for (int i = 0; i <= n; i++) {
            cut[i] = i - 1;
        }
        for (int i = 0; i < n; i++) {
            // 回文串的两种情况：长度为奇数/偶数。
            expand(s, i, i, cut);
            expand(s, i, i + 1, cut);
        }
        return cut[n];
    }

}
