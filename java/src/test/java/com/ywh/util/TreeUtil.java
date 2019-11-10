package com.ywh.util;

import com.ywh.model.TreeNode;

import java.util.List;

/**
 * @author ywh
 * @since 2019/11/8/008
 */
public class TreeUtil {

    /**
     * 根据树的层序遍历数组，构建一棵二叉树。例如：
     * 输入：[3,2,3,null,3,null,1]
     *
     * 构建的二叉树是：
     *     3
     *    / \
     *   2   3
     *    \   \
     *     3   1
     *
     * 注意：如果某个位置上的节点为 null，那么它左右子树的 null 不需要写出来。
     * 比如对于下面这棵树：
     *     1
     *      \
     *       2
     *        \
     *         4
     * 应该表示为：[1,null,2,null,4]，而不是：[1,null,2,null,null,null,4]
     *
     * @param nums
     * @return
     */
    public static TreeNode buildTree(Integer... nums) {
        if (nums == null || nums.length == 0)  {
            return null;
        }
        TreeNode[] trees = new TreeNode[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            trees[i] = nums[i] == null ? null : new TreeNode(nums[i]);
        }
        return buildTree(trees);
    }

    public static TreeNode buildTree(List<Integer> nums) {
        if (nums == null || nums.size() == 0)  {
            return null;
        }
        TreeNode[] trees = new TreeNode[nums.size()];
        for (int i = 0; i < nums.size(); ++i) {
            trees[i] = nums.get(i) == null ? null : new TreeNode(nums.get(i));
        }
        return buildTree(trees);
    }

    private static TreeNode buildTree(TreeNode[] trees) {
        int p = 1;
        for (TreeNode tree : trees) {
            if (tree != null) {
                if (p < trees.length) {
                    tree.left = trees[p++];
                }
                if (p < trees.length) {
                    tree.right = trees[p++];
                }
            }
        }
        return trees[0];
    }
}
