package com.ywh.problem.leetcode.medium;

/**
 * 字符串相乘
 * [数学] [字符串]
 *
 * @author ywh
 * @since 07/09/2020
 */
public class LeetCode43 {

    /**
     * 精简写法
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply2(String num1, String num2) {
        int n1 = num1.length() - 1, n2 = num2.length() - 1;
        if (n1 < 0 || n2 < 0) {
            return "";
        }
        int[] mul = new int[n1 + n2 + 2];
        for (int i = n1; i >= 0; --i) {
            for (int j = n2; j >= 0; --j) {
                int bitmul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // 先加低位判断是否有新的进位
                bitmul += mul[i + j + 1];
                mul[i + j] += bitmul / 10;
                mul[i + j + 1] = bitmul % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        // 去掉前导0
        while (i < mul.length - 1 && mul[i] == 0) {
            i++;
        }
        for (; i < mul.length; ++i) {
            sb.append(mul[i]);
        }
        return sb.toString();
    }

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
}
