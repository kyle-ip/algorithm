package com.ywh.model;

import lombok.Data;

/**
 * 二叉树节点类
 *
 * @author ywh
 * @since 2/13/2019
 */
@Data
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(TreeNode left, TreeNode right, int val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

}
