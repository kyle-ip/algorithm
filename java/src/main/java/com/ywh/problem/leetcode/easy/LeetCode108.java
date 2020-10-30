package com.ywh.problem.leetcode.easy;

import com.ywh.model.OrderTreeNode;
import com.ywh.model.TreeNode;

import java.util.Stack;

/**
 * 有序数组转换二叉搜索树
 * [树] [DFS]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode108 {

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (start - end) >> 1;
        return new TreeNode(nums[mid],
            sortedArrayToBST(nums, start, mid - 1),
            sortedArrayToBST(nums, mid + 1, end)
        );
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
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    /**
     *
     * Time: O(n), Space: O(n)
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBSTIterative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Stack<OrderTreeNode> stack = new Stack<>();
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
