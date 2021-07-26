package com.ywh.problem.leetcode.easy;

/**
 * 旋转字符串
 * [字符串] [双指针]
 *
 * 给定两个字符串, A 和 B。A 的旋转操作就是将 A 最左边的字符移动到最右边。
 * 例如, 若 A = 'abcde'，在移动一次之后结果就是 'bcdea' 。如果在若干次旋转操作之后，A 能变成 B，那么返回 True。
 * 示例 1:
 *      输入: A = 'abcde', B = 'cdeab'
 *      输出: true
 * 示例 2:
 *      输入: A = 'abcde', B = 'abced'
 *      输出: false
 * 注意：
 *      A 和 B 长度不超过 100。
 *
 * @author ywh
 * @since 13/04/2020
 */
public class LeetCode796 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param A
     * @param B
     * @return
     */
    public boolean rotateStringBuiltinMethod(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }


    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param A
     * @param B
     * @return
     */
    public boolean rotateStringStrStr(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }

        // 转化为求 B 是否在 AA 中
        String AA = A + A;
        int len = A.length();
        for (int start = 0; start <= len; start++) {
            int idxA = start, idxB = 0;
            while (idxA < len * 2 && idxB < len && AA.charAt(idxA) == B.charAt(idxB)) {
                idxA++;
                idxB++;
            }
            if (idxA == idxB) {
                return true;
            }
        }
        return false;
    }
}
