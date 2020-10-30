package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.Stack;

/**
 * 判断二叉树是否对称
 * [树] [DFS] [BFS]
 *
 * @author ywh
 * @since 2/13/2019
 */
public class LeetCode101 {

    /**
     * 递归解法
     * 递归判断左与右、左左与右右、左右与右左值是否相等
     * 当左右节点至少一个为空时结束递归，此时如左右节点同时为空则对称，否则不对称；
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    boolean isSymmetricTreeRecursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    /**
     * @param left
     * @param right
     * @return
     */
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left != null && right != null) {
            return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }
        return left == null && right == null;
    }

    /**
     * 迭代解法
     * 使用栈暂存节点，先后把左右节点入栈，当栈非空时执行循环；
     * 循环中先后把右左节点出栈，当两者同时为空时跳过循环，当两者只有其一为空时返回错误
     * 否则（左右节点都不为空）先后把左左、右右、左右、右左入栈
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    boolean isSymmetricTreeIterative(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
            TreeNode r = stack.pop(), l = stack.pop();
            if (l == null && r == null) {
                continue;
            }
            if (l == null || r == null || l.val != r.val) {
                return false;
            }
            // 比较左左和右右。
            stack.push(l.left);
            stack.push(r.right);

            // 比较左右和右左。
            stack.push(l.right);
            stack.push(r.left);
        }
        return true;
    }
}
