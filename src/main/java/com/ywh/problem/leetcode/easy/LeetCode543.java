package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 二叉树的直径
 * [树]
 *
 * @author ywh
 * @since 2019/11/4/004
 */
public class LeetCode543 {

    private int maxDepth(TreeNode root, int[] d) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left, d);
        int right = maxDepth(root.right, d);
        d[0] = Math.max(d[0], left + right);
        return Math.max(left, right) + 1;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTreeRecursive(TreeNode root) {
        // 使用数组可把计算结果保留在内存中
        int[] d = new int[1];
        maxDepth(root, d);
        return d[0];
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTreeIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int diameter = 0;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        // 记录节点子树及其深度
        Map<TreeNode, Integer> depthMap = new HashMap<>();

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            // 优先把左节点入栈，再把右节点入栈，已记录深度的节点跳过
            if (node.left != null && !depthMap.containsKey(node.left)) {
                stack.push(node.left);
            } else if (node.right != null && !depthMap.containsKey(node.right)) {
                stack.push(node.right);
            }
            // 如果栈顶元素左右节点为空，则弹出
            else {
                stack.pop();
                int left = depthMap.getOrDefault(node.left, 0);
                int right = depthMap.getOrDefault(node.right, 0);

                // 左右节点深度之和为直径，求最大值
                diameter = Math.max(diameter, left + right);

                // 取左右节点直径中的较大者，+1 缓存为当前节点深度
                depthMap.put(node, Math.max(left, right) + 1);
            }
        }
        return diameter;
    }
}
