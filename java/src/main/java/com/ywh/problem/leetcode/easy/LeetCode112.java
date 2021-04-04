package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.Stack;

/**
 * 路径和是否等于给定值
 * [树] [深度优先搜索]
 * 
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。叶子节点 是指没有子节点的节点。
 * 示例 1：
 *      输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 *      输出：true
 * 示例 2：
 *      输入：root = [1,2,3], targetSum = 5
 *      输出：false
 * 示例 3：
 *      输入：root = [1,2], targetSum = 0
 *      输出：false
 * 提示：
 *      树中节点的数目在范围 [0, 5000] 内
 *      -1000 <= Node.val <= 1000
 *      -1000 <= targetSum <= 1000
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode112 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumRecursive(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSumRecursive(root.left, sum - root.val) || hasPathSumRecursive(root.right, sum - root.val);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumIterative(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        // 使用两个栈，一个用于缓存节点，另一个用于缓存从根节点到当前节点的差值（给定值 - 路径和）。
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        stack.push(root);
        sumStack.push(sum);

        while (!stack.isEmpty()) {
            int s = sumStack.pop();
            TreeNode n = stack.pop();

            // 到达叶子节点，且和正好等于给定值
            if (n.left == null && n.right == null && n.val == s) {
                return true;
            }
            if (n.left != null) {
                stack.push(n.left);
                sumStack.push(s - n.val);
            }
            if (n.right != null) {
                stack.push(n.right);
                sumStack.push(s - n.val);
            }
        }
        return false;
    }
}
