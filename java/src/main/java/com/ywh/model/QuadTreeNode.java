package com.ywh.model;

/**
 * 四叉树节点
 *
 * @author ywh
 * @since 2019/12/4/004
 */
public class QuadTreeNode {

    /**
     * 非叶子节点的值都为 false
     */
    public boolean val;

    public boolean isLeaf;

    public QuadTreeNode topLeft;

    public QuadTreeNode topRight;

    public QuadTreeNode bottomLeft;

    public QuadTreeNode bottomRight;

    public QuadTreeNode() {
    }

    public QuadTreeNode(boolean val,
                        boolean isLeaf,
                        QuadTreeNode topLeft,
                        QuadTreeNode topRight,
                        QuadTreeNode bottomLeft,
                        QuadTreeNode bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
