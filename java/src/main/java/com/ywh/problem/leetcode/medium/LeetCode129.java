package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求根节点到叶节点数字之和
 * [树] [深度优先搜索] [广度优先搜索]
 * 
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 * 示例 1：
 *      输入：root = [1,2,3]
 *      输出：25
 *      解释：
 *          从根到叶子节点路径 1->2 代表数字 12
 *          从根到叶子节点路径 1->3 代表数字 13
 *          因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 *      输入：root = [4,9,0,5,1]
 *      输出：1026
 *      解释：
 *          从根到叶子节点路径 4->9->5 代表数字 495
 *          从根到叶子节点路径 4->9->1 代表数字 491
 *          从根到叶子节点路径 4->0 代表数字 40
 *          因此，数字总和 = 495 + 491 + 40 = 1026
 * 提示：
 *      树中节点的数目在范围 [1, 1000] 内
 *      0 <= Node.val <= 9
 *      树的深度不超过 10
 * 
 * @author ywh
 * @since 4/8/2021
 */
public class LeetCode129 {

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public int sumNumbersBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.val);
                }
            }
        }
        return sum;
    }

    /**
     * Time: O(n), Space: O(n)
     * @param root
     * @return
     */
    public int sumNumbersDFS(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     *
     * @param root
     * @param prevSum
     * @return
     */
    public int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}
