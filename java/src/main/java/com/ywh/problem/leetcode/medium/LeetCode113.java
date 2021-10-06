package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;
import com.ywh.problem.leetcode.easy.LeetCode112;

import java.util.*;

/**
 * 路径总和 II
 * [树] [深度优先搜索] [回溯]
 * 
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 *      给定如下二叉树，以及目标和 sum = 22，
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *      [
 *         [5,4,11,2],
 *         [5,8,4,5]
 *      ]
 * 
 * @author ywh
 * @since 21/11/2019
 */
public class LeetCode113 {

    /**
     *
     * @param root
     * @param targetSum
     * @param path
     * @param ret
     */
    private List<List<Integer>> pathSum(TreeNode root, int targetSum, LinkedList<Integer> path, List<List<Integer>> ret) {
        if (root != null) {
            // 把当前节点加入当前路径。
            path.add(root.val);

            // 路径和符合要求。
            if (root.left == null && root.right == null && root.val == targetSum) {
                ret.add(new ArrayList<>(path));
            }

            // 递归调用左右子树。
            pathSum(root.left, targetSum - root.val, path, ret);
            pathSum(root.right, targetSum - root.val, path, ret);

            // 移除最后一个元素。
            path.removeLast();
        }
        return ret;
    }

    /**
     * 参考 {@link LeetCode112}，由于要记录路径，需要用回溯。
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSumRecursive(TreeNode root, int targetSum) {
        return pathSum(root, targetSum, new LinkedList<>(), new ArrayList<>());
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSumIterative(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
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
                if (n.left == null && n.right == null && curSum == targetSum) {
                    ret.add(new ArrayList<>(elem));
                }
                stack.pop();
                elem.remove(elem.size() - 1);
                curSum -= n.val;
                root = null;
            } else {
                root = n.right;
            }
        }
        return ret;
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSumIterativePrev(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> elem = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
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
                if (n.left == null && n.right == null && curSum == targetSum) {
                    ret.add(new ArrayList<>());
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
        return ret;
    }
}
