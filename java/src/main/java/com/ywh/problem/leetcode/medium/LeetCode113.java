package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.*;

/**
 * 二叉树中和为给定值的路径
 * [树] [DFS] [回溯]
 *
 * @author ywh
 * @since 21/11/2019
 */
public class LeetCode113 {

    /**
     *
     * @param root
     * @param sum
     * @param elem
     * @param result
     */
    private void path(TreeNode root, int sum, List<Integer> elem, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        // 把当前节点加入当前路径
        elem.add(root.val);

        // 路径和符合要求
        if (root.left == null && root.right == null && root.val == sum) {
            result.add(new ArrayList<>(elem));
        }

        // 递归调用左右子树
        path(root.left, sum - root.val, elem, result);
        path(root.right, sum - root.val, elem, result);

        // 移除最后一个元素
        elem.remove(elem.size() - 1);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSumRecursive(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        path(root, sum, elem, result);
        return null;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSumIterative(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();

        int curSum = 0;
        while (root != null || !stack.isEmpty()) {

            // 从左到右、深度优先遍历：
            // 添加节点到当前路径、计算当前路径和，添加节点到已访问集合、节点入栈
            while (root != null) {
                elem.add(root.val);
                curSum += root.val;
                visited.add(root);
                stack.push(root);
                root = root.left;
            }
            TreeNode n = stack.peek();

            // 当前路径没有右节点，或右边路径已被访问过（用于回退时判断）
            if (n.right == null || visited.contains(n.right)) {
                if (n.left == null && n.right == null && curSum == sum) {
                    result.add(new ArrayList<>(elem));
                }
                stack.pop();
                elem.remove(elem.size() - 1);
                curSum -= n.val;
                root = null;
            } else {
                root = n.right;
            }
        }
        return result;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSumIterativePrev(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        int curSum = 0;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                elem.add(root.val);
                curSum += root.val;
                stack.push(root);
                root = root.left;
            }
            TreeNode n = stack.peek();
            if (n.right == null || n.right == prev) {
                if (n.left == null && n.right == null && curSum == sum) {
                    result.add(new ArrayList<>());
                }
                stack.pop();
                elem.remove(elem.size() - 1);
                curSum -= n.val;
                prev = n;
                root = null;
            } else {
                root = n.right;
            }
        }
        return result;
    }
}
