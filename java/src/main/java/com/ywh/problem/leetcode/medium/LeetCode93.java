package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 复原 IP 地址
 * [字符串] [回溯]
 *
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 示例 1：
 *      输入：s = "25525511135"
 *      输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *      输入：s = "0000"
 *      输出：["0.0.0.0"]
 * 示例 3：
 *      输入：s = "1111"
 *      输出：["1.1.1.1"]
 * 示例 4：
 *      输入：s = "010010"
 *      输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *      输入：s = "101023"
 *      输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 提示：
 *      0 <= s.length <= 3000
 *      s 仅由数字组成
 *
 * @author ywh
 * @since 14/12/2019
 */
public class LeetCode93 {

    /**
     *
     * @param segment
     * @return
     */
    private boolean isValid(String segment) {
        int len = segment.length();
        if (len > 3) {
            return false;
        }
        return segment.charAt(0) != '0' ? Integer.parseInt(segment) < 256 : len == 1;
    }

    /**
     * 1. 每次截取起始下标到遍历下标之间的子串为一段，其中遍历下标不能超过原字符串长度 +3。
     * 2. 每截取一段即判断是否合法，合法则添加到当前结果集。
     * 3. 区分第二第三段与最后一段的处理。
     *
     * @param s
     * @param start
     * @param segments
     * @param ret
     */
    private List<String> backtracking(String s, int start, LinkedList<String> segments, List<String> ret) {
        // start + 3 不超过 s 的长度（三个“.”分隔符）。
        for (int i = start; i < Math.min(s.length() - 1, start + 3); i++) {
            String segment = s.substring(start, i + 1);
            if (!isValid(segment)) {
                continue;
            }
            segments.add(segment);

            // 单独处理最后一段。
            if (segments.size() == 3) {
                String lastSegment = s.substring(i + 1);
                if (isValid(lastSegment)) {
                    segments.add(lastSegment);
                    ret.add(String.join(".", segments));
                    segments.removeLast();
                }
            }
            // 处理第二第三段。
            else {
                backtracking(s, i + 1, segments, ret);
            }
            segments.removeLast();
        }
        return ret;
    }

    /**
     * 回溯解法
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        return backtracking(s, 0, new LinkedList<>(), new ArrayList<>());
    }

    /**
     * Time: O(n^4), Space: O(1)
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddressesBruteForce(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() > 12 || s.length() < 4) {
            return ret;
        }

        // 四层循环，每层循环的数字表示 ip 地址每截的长度（不含分隔符，长度之和为 s 的长度，最大值为 12）
        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        if (a + b + c + d != s.length()) {
                            continue;
                        }

                        // 判断四段是否都符合要求
                        int n1 = Integer.parseInt(s.substring(0, a));
                        int n2 = Integer.parseInt(s.substring(a, a + b));
                        int n3 = Integer.parseInt(s.substring(a + b, a + b + c));
                        int n4 = Integer.parseInt(s.substring(a + b + c));
                        if (n1 > 255 || n2 > 255 || n3 > 255 || n4 > 255) {
                            continue;
                        }
                        String ip = String.format("%d.%d.%d.%d", n1, n2, n3, n4);
                        if (ip.length() != s.length() + 3) {
                            continue;
                        }
                        ret.add(ip);
                    }
                }
            }
        }
        return ret;
    }
}
