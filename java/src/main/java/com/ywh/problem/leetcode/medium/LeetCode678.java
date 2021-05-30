package com.ywh.problem.leetcode.medium;

/**
 * 有效的括号字符串
 * [字符串]
 *
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *      任何左括号 ( 必须有相应的右括号 )。
 *      任何右括号 ) 必须有相应的左括号 ( 。
 *      左括号 ( 必须在对应的右括号之前 )。
 *      * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 *      一个空字符串也被视为有效字符串。
 * 示例 1:
 *      输入: "()"
 *      输出: True
 * 示例 2:
 *      输入: "(*)"
 *      输出: True
 * 示例 3:
 *      输入: "(*))"
 *      输出: True
 * 注意:
 *      字符串大小将在 [1，100] 范围内。
 * 
 * @author ywh
 * @since 5/4/2021
 */
public class LeetCode678 {

    /**
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        // low、high 分别表示可能多余的左括号数下界和上界，即：当前状态下，剩余未匹配的左括号最小值为 low，最大值为 high。
        // 其中每完成一个配对，low 即 -1。同时 low 要保持不小于 0，比如 (**)(。
        int low = 0, high = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                // 左括号，增加一个待配对。
                case '(': {
                    low++;
                    high++;
                    break;
                }
                // 右括号，完成一个配对。
                case ')': {
                    if (low > 0) {
                        low--;
                    }
                    high--;
                    break;
                }
                // 星号，减少一个配对，同时上界向右扩张（表示多了一个 * 可以转变为左括号）。
                default: {
                    // * 转换为右括号。
                    if (low > 0) {
                        low--;
                    }
                    high++;
                    break;
                }
            }

            // 如果 high 小于 0，则右括号太多了，* 全部变成左括号都不够用，因此返回 false。
            if (high < 0) {
                return false;
            }
        }

        // 最后如果 low > 0，说明末尾有多余的左括号，返回 false。
        return low == 0;
    }

}
