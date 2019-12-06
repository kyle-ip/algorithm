package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 字典序排数
 * [DFS] [树]
 *
 * @author ywh
 * @since 2019/12/6/006
 */
public class LeetCode386 {

    /**
     *
     * @param res
     * @param val
     * @param max
     */
    private void lexicalOrderRecursive(List<Integer> res, int val, int max) {
        if (val != 0) {
            res.add(val);
        }
        // 循环中递归，相当于把当前循环执行到底再执行下一轮循环（比如当前循环为 1，则不断 n * 10 + 1 到底再返回）
        for (int i = 0; i <= 9 && val * 10 + i <= max; i++) {
            if (val == 0 && i == 0) {
                continue;
            }
            lexicalOrderRecursive(res, val * 10 + i, max);
        }
    }

    /**
     * 递归解法
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrderRecursive(int n) {
        List<Integer> result = new ArrayList<>();
        lexicalOrderRecursive(result, 0, n);
        return result;
    }

    /**
     * 前序遍历十叉树解法
     * <p>
     * Time: O(n), Space: O(n)
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder10WayTreePreorder(int n) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> tree = new Stack<>();

        // 填充十叉树的第一层（[1, n] 或 [1, 9]）
        for (int i = Math.min(n, 9); i > 0; i--) {
            tree.push(i);
        }

        while (!tree.empty()) {
            int cur = tree.pop();
            res.add(cur);

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
        return res;
    }
}
