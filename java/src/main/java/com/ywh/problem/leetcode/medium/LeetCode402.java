package com.ywh.problem.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 移掉 K 位数字
 * [栈] [贪心]
 * 
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 *      num 的长度小于 10002 且 ≥ k。
 *      num 不会包含任何前导零。
 * 示例 1 :
 *      输入: num = "1432219", k = 3
 *      输出: "1219"
 *      解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *      输入: num = "10200", k = 1
 *      输出: "200"
 *      解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *      输入: num = "10", k = 2
 *      输出: "0"
 *      解释: 从原数字移除所有的数字，剩余为空就是 0。
 * @author ywh
 * @since 4/12/2021
 */
public class LeetCode402 {

    /**
     * 对于两个相同长度的数字序列，最左边不同的数字决定了这两个数字的大小.
     * 若要使得剩下的数字最小，需要保证靠前的数字尽可能小。
     * 贪心策略：从左往右找到第一个位置 i，使得 d[i] < d[i-1]，并删除 d[i-1]。如果不存在，则整个序列非递减，因此删除最后的数字。
     * 因此每次对整个数字序列进行一次该策略：
     *      删去一个字符后，剩下的 n−1 长度的数字序列就形成了新的子问题，可以继续使用同样的策略，直至删除 k 次。
     * 使用单调栈可使时间复杂度从 O(k*n) 降至 O(n)：
     *      用一个栈维护当前的答案序列，栈中的元素代表截止到当前位置，删除不超过 k 次个数字后所能得到的最小整数。
     *      在使用 k 个删除次数之前，栈中的序列从栈底到栈顶单调不降。
     * 因此对于每个数字，如果该数字小于栈顶元素，就不断地弹出栈顶元素，直到：
     *      栈为空，或新的栈顶元素不大于当前数字，或已经删除了 k 位数字。
     * 特殊情况：
     *      如果删除 m 个数字且 m<k，需要从序列尾部删除额外的 k−m 个数字。
     *      如果最终的数字序列存在前导零则要删去。
     *      如果最终数字序列为空应该返回 0。
     * Time: O(n), Space: O(n)
     *
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        Deque<Character> stack = new LinkedList<>();

        // 遍历每个数字。
        for (int i = 0; i < num.length(); ++i) {
            char digit = num.charAt(i);
            // 如果栈非空，且还没删够 k 个，且栈顶元素比当前数字大（出现递减），则把栈顶元素弹出。
            for (; !stack.isEmpty() && k > 0 && stack.peek() > digit; k--) {
                stack.pop();
            }
            // 把不递减的元素入栈。
            stack.push(digit);
        }
        // 删除额外的 k−num.length() 个数字：如果遍历到最后还没删够 k 个（递减的元素不够），则从栈中弹出。
        for (int i = 0; i < k; ++i) {
            stack.pop();
        }
        // 处理前导 0。
        for (; !stack.isEmpty() && stack.peekLast() == '0'; stack.pollLast()) { }
        StringBuilder ret = new StringBuilder();

        // 收集结果。
        while (!stack.isEmpty()) {
            ret.append(stack.pollLast());
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }
}
