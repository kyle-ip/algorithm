package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.Stack;

/**
 * 将二叉搜索树转为较大树
 * [树]
 *
 * @author ywh
 * @since 04/11/2019
 */
public class LeetCode538 {

    /**
     *
     * @param root
     * @param sum
     * @return
     */
    private int dfs(TreeNode root, int sum) {
        // 如果节点为空，返回累加和
        if (root == null) {
            return sum;
        }

        // 递归处理右子树，返回的累加和添加到当前节点值
        // 再处理左子树，输入累加和为当前节点值
        int cur = dfs(root.right, sum);
        root.val += cur;
        return dfs(root.left, root.val);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public TreeNode convertBSTRecursive(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public TreeNode convertBSTIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int sum = 0;
        while (cur != null || !stack.empty()) {
            // 不断把右节点入栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            // 从栈弹出节点（最初是最右节点），节点值加上累加和，累加和为该节点值
            cur = stack.pop();
            cur.val += sum;
            sum = cur.val;

            // 处理左节点
            cur = cur.left;
        }
        return root;
    }
}
