package com.ywh.ds.tree;

import lombok.Data;

import java.util.Objects;

/**
 * 二叉树节点
 *
 * @author ywh
 * @since 2/13/2019
 */
@Data
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TreeNode)) {
            return false;
        }
        TreeNode node = (TreeNode) o;
        return getVal() == node.getVal() &&
            getLeft().equals(node.getLeft()) &&
            getRight().equals(node.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVal(), getLeft(), getRight());
    }
}
