package com.ywh.problem.leetcode.medium;

import java.util.*;

/**
 * 字典序排数
 * [深度优先搜索] [树]
 * 
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 * 例如，
 *      给定 n = 13，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 *
 * @author ywh
 * @since 2019/12/6/006
 */
public class LeetCode386 {

    /**
     *
     * @param ret
     * @param num
     * @param max
     */
    private List<Integer> lexicalOrderRecursive(List<Integer> ret, int num, int max) {
        if (num > 0) {
            ret.add(num);
        }
        // 循环中递归，相当于把当前循环执行到底再执行下一轮循环（比如当前循环为 1，则不断 n * 10 + 1 到底再返回）
        for (int i = 0; i <= 9 && num * 10 + i <= max; i++) {
            // 跳过 0 * 10 + 0，即至少从 1、10 ... 开始。
            if (num != 0 || i != 0) {
                lexicalOrderRecursive(ret, num * 10 + i, max);
            }
        }
        return  ret;
    }

    /**
     * 递归解法
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrderRecursive(int n) {
        return lexicalOrderRecursive(new ArrayList<>(), 0, n);
    }

    /**
     * 前序遍历十叉树解法
     *
     * Time: O(n), Space: O(n)
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder10WayTreePreorder(int n) {
        List<Integer> ret = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();

        // 倒序填充十叉树的第一层（[1, n] 或 [1, 9]）
        for (int i = Math.min(n, 9); i > 0; i--) {
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int num = stack.pop();
            ret.add(num);

            // 字典序：如处理 1 后，继续处理 10 ~ 19
            num *= 10;
            // 倒序入栈：如 n 与 cur 差距超出 9，需要处理 [cur, cur + 9]，否则 [cur, cur + n]
            for (int i = Math.min(n - num, 9); i >= 0 && num <= n; i--) {
                if (num + i <= n) {
                    stack.push(num + i);
                }
            }
        }
        return ret;
    }
}
