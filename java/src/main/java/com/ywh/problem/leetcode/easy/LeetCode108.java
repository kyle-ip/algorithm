package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.OrderTreeNode;
import com.ywh.ds.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 将有序数组转换为二叉搜索树
 * [树] [深度优先搜索]
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 *      给定有序数组: [-10,-3,0,5,9],
 *      一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *            0
 *           / \
 *         -3   9
 *         /   /
 *       -10  5
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode108 {

    /**
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private TreeNode toBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        return new TreeNode(nums[mid], toBST(nums, start, mid - 1), toBST(nums, mid + 1, end));
    }


    /**
     * Time: O(n), Space: O(log(n))
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBSTRecursive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return toBST(nums, 0, nums.length - 1);
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBSTIterative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Deque<OrderTreeNode> stack = new LinkedList<>();
        TreeNode dummy = new TreeNode(0);
        stack.push(new OrderTreeNode(dummy, 0, nums.length - 1, true));

        while (!stack.isEmpty()) {
            OrderTreeNode node = stack.pop();
            if (node.start > node.end) {
                continue;
            }
            int mid = node.start + (node.end - node.start) / 2;
            TreeNode child = new TreeNode(nums[mid]);
            if (node.isLeft) {
                node.parent.left = child;
            } else {
                node.parent.right = child;
            }
            stack.push(new OrderTreeNode(child, node.start, mid - 1, true));
            stack.push(new OrderTreeNode(child, mid + 1, node.end, false));
        }
        return dummy.left;
    }
}
