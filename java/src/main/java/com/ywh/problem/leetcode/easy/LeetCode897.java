package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 递增顺序搜索树
 * [树] [深度优先搜索] [递归]
 * 
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * 示例 1：
 *      输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 *      输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 示例 2：
 *      输入：root = [5,1,7]
 *      输出：[1,null,5,null,7]
 * 提示：
 *      树中节点数的取值范围是 [1, 100]
 *      0 <= Node.val <= 1000
 * 
 * @author ywh
 * @since 4/29/2021
 */
public class LeetCode897 {

    /**
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode dummy = new TreeNode(), p = dummy;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();

                p.right = root;
                p = root;
                p.left = null;

                root = root.right;
            }
        }
        return dummy.right;
    }

}
