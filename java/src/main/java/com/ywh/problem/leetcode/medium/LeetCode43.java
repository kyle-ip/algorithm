package com.ywh.problem.leetcode.medium;

/**
 * 字符串相乘
 * [数学] [字符串]
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1：
 *      输入: num1 = "2", num2 = "3"
 *      输出: "6"
 * 示例 2：
 *      输入: num1 = "123", num2 = "456"
 *      输出: "56088"
 * 说明：
 *      num1 和 num2 的长度小于110。
 *      num1 和 num2 只包含数字 0-9。
 *      num1 和 num2 均不以零开头，除非是数字 0 本身。
 *      不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author ywh
 * @since 07/09/2020
 */
public class LeetCode43 {


    /**
     * 大数乘法（很慢，慎用）
     *
     * @param num1
     * @param num2
     * @return
     */
    private static String mul(String num1, String num2) {
        String sum = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int k = num2.length() - 1 - i, p = 1;
            while (k > 0) {
                p *= 10;
                k--;
            }
            // 必然溢出，三者相乘需要改用大数加法。
            // sum += (num2.charAt(i) - '0') * Integer.parseInt(num1) * p;

            String cur = "0";
            for (int j = 0; j < p; j++) {
                cur = add(cur, num1);
            }
            String tmp = cur;
            for (int j = 0; j < (num2.charAt(i) - '0') - 1; j++) {
                cur = add(cur, tmp);
            }
            sum = add(sum, cur);
        }
        return sum;
    }

    /**
     * 大数加法
     *
     * @param num1
     * @param num2
     * @return
     */
    private static String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1, j = num2.length() - 1, carry = 0; i >= 0 || j >= 0 || carry > 0;) {
            int sum = carry;
            if (i >= 0) {
                sum += num1.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += num2.charAt(j--) - '0';
            }
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
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
            for (int j = num1.length() - 1, k = 1, carry = 0;
                 j >= 0 || carry != 0;
                 j--, k *= 10
            ) {
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
        int[] mul = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + mul[i + j + 1];
                mul[i + j + 1] = sum % 10;

                // mul[i + j] 即 carry.
                mul[i + j] += sum / 10;
            }
        }
        // 去掉前面多余的 0。
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < mul.length - 1 && mul[i] == 0; i++);
        for (; i < mul.length; sb.append(mul[i++]));
        return sb.toString();
    }

}
