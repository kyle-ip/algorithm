package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Z 字形变换
 * [字符串]
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *      L   C   I   R
 *      E T O E S I I G
 *      E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：string convert(string s, int numRows);
 * 示例 1:
 *      输入: s = "LEETCODEISHIRING", numRows = 3
 *      输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *      输入: s = "LEETCODEISHIRING", numRows = 4
 *      输出: "LDREOEIIECIHNTSG"
 *      解释:
 *      L     D     R
 *      E   O E   I I
 *      E C   I H   N
 *      T     S     G
 * 示例 3：
 *      输入：s = "A", numRows = 1
 *      输出："A"
 * 提示：
 *      1 <= s.length <= 1000
 *      s 由英文字母（小写和大写）、',' 和 '.' 组成
 *      1 <= numRows <= 1000
 *
 * @author ywh
 * @since 2020/12/8/008
 */
public class LeetCode6 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        // 创建每行的列表，个数为行数和 s 长度中的较小者。
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            buckets.add(new ArrayList<>());
        }

        // 遍历 s 每个字符，对于第 0 行或最后一行，切换方向：
        // 比如对于：
        //      L     D     R
        //      E   O E   I I
        //      E C   I H   N
        //      T     S     G
        // 从上至下、再从下至上循环填充列表：
        // [
        //      [L, D, R],
        //      [E, O, E, I, I],
        //      [E, C, I, H, N],
        //      [T, S, G]
        // ]
        // 最后压缩为一行："LDR EOEII ECIHN TSG"
        int curRow = 0, dir = -1;
        for (char c : s.toCharArray()) {
            buckets.get(curRow).add(c);
            if (curRow == 0 || curRow == numRows - 1) {
                dir = -dir;
            }
            curRow += dir;
        }
        StringBuilder ret = new StringBuilder();
        for (List<Character> bucket : buckets) {
            for (char c : bucket) {
                ret.append(c);
            }
        }
        return ret.toString();

    }

}
