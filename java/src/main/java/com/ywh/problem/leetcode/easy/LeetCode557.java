package com.ywh.problem.leetcode.easy;

/**
 * 反转字符串中的单词 III 
 * [字符串]
 * 
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 *      输入："Let's take LeetCode contest"
 *      输出："s'teL ekat edoCteeL tsetnoc"
 * 提示：
 *      在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 * 
 * @author ywh
 * @since 2/16/2019
 */
public class LeetCode557 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] c = s.toCharArray();

        // start、end 分别定位到每个单词的起始和结束（end 定位单词后的空格，每轮翻转都把 end 与 start 置于空格后第一位）

        for (int l = 0, r = 0; l < c.length; ) {
            for (; r < c.length && c[r] != ' '; r++);

            // 与单指针的写法相比更推荐这种
            for (int i = l, j = r - 1; i < j; i++, j--) {
                char tmp = c[i];
                c[i] = c[j];
                c[j] = tmp;
            }

            // 跳过空格
            l = r + 1;
            r = l;
        }
        return new String(c);
    }

}
