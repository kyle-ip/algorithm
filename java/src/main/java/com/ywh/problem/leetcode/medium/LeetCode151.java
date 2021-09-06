package com.ywh.problem.leetcode.medium;

/**
 * 翻转字符串里的单词
 * [字符串]
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 说明：
 *      无空格字符构成一个 单词 。
 *      输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *      如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 1：
 *      输入："the sky is blue"
 *      输出："blue is sky the"
 * 示例 2：
 *      输入：" hello world! "
 *      输出："world! hello"
 *      解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *      输入："a good example"
 *      输出："example good a"
 *      解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 *      输入：s = "  Bob    Loves  Alice   "
 *      输出："Alice Loves Bob"
 * 示例 5：
 *      输入：s = "Alice does not even like bob"
 *      输出："bob like even not does Alice"
 * 提示：
 *
 *      1 <= s.length <= 104
 *      s 包含英文大小写字母、数字和空格 ' '
 *      s 中 至少存在一个 单词
 * 进阶：
 *      请尝试使用 O(1) 额外空间复杂度的原地解法。
 *
 * @author ywh
 * @since 04/01/2020
 */
public class LeetCode151 {

    /**
     * 翻转从字符串 i 到 j 的部分
     *
     * @param str
     * @param i
     * @param j
     */
    private void reverse(char[] str, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;
        }
    }

    /**
     * 先逐个翻转所有单词，再翻转整个字符串
     * 处理空格：翻转后逐个单词向前拷贝
     *
     * Time: O(n), Space: O(n)
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] str = s.toCharArray();
        int p = 0, q = 0, r = str.length - 1;

        // "  good example     "
        //              end
        for (; r >= 0 && str[r] == ' '; r--);
        while (q <= r) {

            // 跳过空格，取单词的开始位置。
            // "  good example     "
            //    q          l
            for (; q <= r && str[q] == ' '; q++);

            // 取换位后单词应插入的开始位置 p（可能是空格），把一个单词从 q 复制到 p 并翻转它。
            // "  good example     "
            //  p q          l
            int l = p;
            for (; q <= r && str[q] != ' '; str[p++] = str[q++]);
            reverse(str, l, p - 1);

            // 单词间补上空格。
            if (q <= r) {
                str[p++] = ' ';
            }
        }
        // 翻转整个字符串（有单词的部分）。
        reverse(str, 0, p - 1);
        return new String(str, 0, p);
    }
}
