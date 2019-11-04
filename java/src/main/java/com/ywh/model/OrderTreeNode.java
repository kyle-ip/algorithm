package com.ywh.model;

/**
 * 二叉搜索树节点
 *
 * @author ywh
 * @since 2/20/2019
 */
public class OrderTreeNode {

    public TreeNode parent;

    public int start, end;

    public boolean isLeft;

    public OrderTreeNode(TreeNode parent, int start, int end, boolean isLeft) {
        this.parent = parent;
        this.start = start;
        this.end = end;
        this.isLeft = isLeft;
    }

}
