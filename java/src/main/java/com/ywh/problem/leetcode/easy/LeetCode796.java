package com.ywh.problem.leetcode.easy;

/**
 * 旋转字符串
 * [字符串] [双指针]
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
