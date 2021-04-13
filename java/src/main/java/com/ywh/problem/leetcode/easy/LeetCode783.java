package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉搜索树节点最小距离
 * [树] [深度优先搜索] [递归]
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 示例 1：
 *      输入：root = [4,2,6,1,3]
 *      输出：1
 * 示例 2：
 *      输入：root = [1,0,48,null,null,12,49]
 *      输出：1
 * 提示：
 *      树中节点数目在范围 [2, 100] 内
 *      0 <= Node.val <= 105
 *
 * @author ywh
 * @since 4/13/2021
 */
public class LeetCode783 {

    /**
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        int pre = -1, ret = Integer.MAX_VALUE;
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre == -1) {
                    pre = root.val;
                } else {
                    ret = Math.min(ret, root.val - pre);
                    pre = root.val;
                }
                root = root.right;
            }
        }
        return ret;
    }
}
