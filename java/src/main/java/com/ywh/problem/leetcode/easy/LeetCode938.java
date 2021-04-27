package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉搜索树的范围和
 * [树] [深度优先搜索] [递归]
 * 
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * 示例 1：
 *      输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 *      输出：32
 * 示例 2：
 *      输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 *      输出：23
 * 提示：
 *      树中节点数目在范围 [1, 2 * 104] 内
 *      1 <= Node.val <= 105
 *      1 <= low <= high <= 105
 *      所有 Node.val 互不相同
 *
 * @author ywh
 * @since 18/04/2020
 */
public class LeetCode938 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTRecursive(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        // 当前节点的值小/大于左/右边界，不在求和范围中，因此向右/左递归
        if (root.val < low) {
            return rangeSumBSTRecursive(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBSTRecursive(root.left, low, high);
        }
        return root.val + rangeSumBSTRecursive(root.left, low, high) + rangeSumBSTRecursive(root.right, low, high);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTIterative(TreeNode root, int low, int high) {
        int sum = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            if (node.val < low) {
                stack.push(node.right);
            } else if (node.val > high) {
                stack.push(node.left);
            } else {
                sum += node.val;
                stack.push(node.left);
                stack.push(node.right);
            }
        }
        return sum;
    }


}
