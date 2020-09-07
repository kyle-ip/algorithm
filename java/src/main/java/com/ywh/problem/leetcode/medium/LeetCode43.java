package com.ywh.problem.leetcode.medium;

/**
 * 字符串相乘
 *
 * @author ywh
 * @since 07/09/2020
 */
public class LeetCode43 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        String ret = "0";
        if ("0".equals(num1) || "0".equals(num2)) {
            return ret;
        }
        for (int i = num2.length() - 1; i >= 0; i--) {

            // 大数乘法
            int a = num2.charAt(i) - '0';
            StringBuilder sb = new StringBuilder();
            for (int j = num1.length() - 1, k = 1, carry = 0; j >= 0 || carry != 0; j--, k *= 10) {
                int s = carry;
                if (j >= 0) {
                    s += a * (num1.charAt(j) - '0');
                }
                sb.append(s % 10);
                carry = s / 10;
            }
            sb.reverse();
            for (int k = i; k < num2.length() - 1; k++) {
                sb.append("0");
            }
            String cur = sb.toString();
            sb.delete(0, sb.length());

            // 大数加法
            for (int p1 = ret.length() - 1, p2 = cur.length() - 1, carry = 0; p1 >= 0 || p2 >= 0 || carry != 0; ) {
                int sum = carry;
                if (p1 >= 0) {
                    sum += ret.charAt(p1) - '0';
                    p1--;
                }
                if (p2 >= 0) {
                    sum += cur.charAt(p2) - '0';
                    p2--;
                }
                sb.append(sum % 10);
                carry = sum / 10;
            }
            ret = sb.reverse().toString();
        }
        return ret;
    }

    public static void main(String[] args) {
        LeetCode43 l = new LeetCode43();
        System.out.println(l.multiply("9123", "0"));
    }
}
