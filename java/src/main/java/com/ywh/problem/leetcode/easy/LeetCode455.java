package com.ywh.problem.leetcode.easy;

import java.util.Arrays;

/**
 * 分发饼干
 * [贪心]
 *
 * 示例 2:
 *
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *  
 *
 * 提示：
 *
 * 1 <= g.length <= 3 * 10^4
 * 0 <= s.length <= 3 * 10^4
 * 1 <= g[i], s[j] <= 2^31 - 1
 *
 * @author ywh
 * @since 2020/12/25
 */
public class LeetCode455 {

    /**
     * Time: O(n*log(n)), Space: O(1)
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            // 饼干太小满足不了孩子。
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }
}
