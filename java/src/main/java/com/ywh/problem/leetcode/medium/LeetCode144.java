package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.*;

/**
 * 二叉树的前序遍历
 * [栈] [树]
 * 
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 示例 1：
 *      输入：root = [1,null,2,3]
 *      输出：[1,2,3]
 * 示例 2：
 *      输入：root = []
 *      输出：[]
 * 示例 3：
 *      输入：root = [1]
 *      输出：[1]
 * 示例 4：
 *      输入：root = [1,2]
 *      输出：[1,2]
 * 示例 5：
 *      输入：root = [1,null,2]
 *      输出：[1,2]
 * 提示：
 *      树中节点数目在范围 [0, 100] 内
 *      -100 <= Node.val <= 100
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author ywh
 * @since 2/21/2019
 */
public class LeetCode144 {

    /**
     *
     * @param root
     * @param result
     */
    private void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalRecursive2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> left = preorderTraversalRecursive2(root.left), right = preorderTraversalIterative(root.right);
        left.add(0, root.val);
        left.addAll(right);
        return left;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            // 一直往左走，沿途添加节点的值到结果集，并把节点入栈，直到遇到空节点。
            if (root != null) {
                ret.add(root.val);
                stack.push(root);
                root = root.left;
            }
            // 当根、左都已遍历完时弹出时根节点，指向右节点。
            else {
                root = stack.pop().right;
            }
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalIterative2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ret.add(node.val);

            // 后进先出，先把右节点入栈，确保下次先处理左节点
            if (node.right!= null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return ret;
    }
}
