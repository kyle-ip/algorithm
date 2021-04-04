package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.Stack;

/**
 * 对称二叉树
 * [树] [深度优先搜索] [广度优先搜索]
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 进阶：
 *      你可以运用递归和迭代两种方法解决这个问题吗？
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
