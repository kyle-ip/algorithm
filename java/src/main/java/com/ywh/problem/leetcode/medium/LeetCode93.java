package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 复原 IP 地址
 * [字符串] [回溯]
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
    private static boolean valid(String segment) {
        int m = segment.length();
        if (m > 3) {
            return false;
        }
        return (segment.charAt(0) != '0') ? (Integer.parseInt(segment) <= 255) : (m == 1);
    }

    /**
     *
     * @param s
     * @param prevPos
     * @param dots
     * @param segments
     * @param output
     */
    public static void backtrack(String s, int prevPos, int dots, List<String> segments, List<String> output) {
        int maxPos = Math.min(s.length() - 1, prevPos + 4);
        for (int currPos = prevPos + 1; currPos < maxPos; currPos++) {
            String segment = s.substring(prevPos + 1, currPos + 1);
            if (valid(segment)) {
                segments.add(segment);
                if (dots - 1 == 0) {
                    String outputSegment = s.substring(currPos + 1);
                    if (valid(outputSegment)) {
                        segments.add(outputSegment);
                        output.add(String.join(".", segments));
                        segments.remove(segments.size() - 1);
                    }
                } else {
                    backtrack(s, currPos, dots - 1, segments, output);
                }
                // removeLast
                segments.remove(segments.size() - 1);
            }
        }
    }

    /**
     * 回溯解法
     * TODO 暂时未理解
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        ArrayList<String> output = new ArrayList<>();
        backtrack(s, -1, 3, new LinkedList<>(), output);
        return output;
    }

    /**
     * @param s
     * @return
     */
    public static List<String> restoreIpAddressesBruteForce(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() > 12 || s.length() < 4) {
            return res;
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

                        res.add(ip);
                    }
                }
            }
        }
        return res;
    }
}
