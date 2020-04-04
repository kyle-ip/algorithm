package com.ywh.problem.leetcode.easy;

/**
 * 二进制字符串求和
 * [数学] [字符串]
 *
 * @author ywh
 * @since 04/04/2020
 */
public class LeetCode67 {

    /**
     * Time: O(max(m, n)), Space: O(max(m, n))
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int idxA = a.length() - 1, idxB = b.length() - 1, carry = 0, sum;
        while (idxA >= 0 || idxB >= 0 || carry != 0) {
            sum = carry;
            if (idxA >= 0) {
                sum += a.charAt(idxA--) - '0';
            }
            if (idxB >= 0) {
                sum += b.charAt(idxB--) - '0';
            }
            // sb.append(sum & 1);
            sb.append(sum % 2);
            // carry = sum >> 1;
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }
}
