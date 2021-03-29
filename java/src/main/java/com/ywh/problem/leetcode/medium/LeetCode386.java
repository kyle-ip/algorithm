package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 字典序排数
 * [DFS] [树]
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
     * @param val
     * @param max
     */
    private List<Integer> lexicalOrderRecursive(List<Integer> ret, int val, int max) {
        if (val != 0) {
            ret.add(val);
        }
        // 循环中递归，相当于把当前循环执行到底再执行下一轮循环（比如当前循环为 1，则不断 n * 10 + 1 到底再返回）
        for (int i = 0; i <= 9 && val * 10 + i <= max; i++) {
            // 跳过 0 * 10 + 0，即至少从 1、10 ... 开始。
            if (val == 0 && i == 0) {
                continue;
            }
            lexicalOrderRecursive(ret, val * 10 + i, max);
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
        Stack<Integer> tree = new Stack<>();

        // 填充十叉树的第一层（[1, n] 或 [1, 9]）
        for (int i = Math.min(n, 9); i > 0; i--) {
            tree.push(i);
        }

        while (!tree.empty()) {
            int cur = tree.pop();
            ret.add(cur);

            // 字典序：如处理 1 后，继续处理 10 ~ 19
            cur *= 10;
            if (cur > n) {
                continue;
            }
            // 倒序入栈：如 n 与 cur 差距超出 9，需要处理 [cur, cur + 9]，否则 [cur, cur + n]
            for (int i = Math.min(n - cur, 9); i >= 0; i--) {
                if (cur + i <= n) {
                    tree.push(cur + i);
                }
            }
        }
        return ret;
    }
}
