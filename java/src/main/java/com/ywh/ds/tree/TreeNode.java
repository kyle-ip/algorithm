package com.ywh.ds.tree;

import java.util.*;

/**
 * 二叉树节点
 *
 * @author ywh
 * @since 2/13/2019
 */
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {}

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
        return val == node.val &&
            left.equals(node.left) &&
            right.equals(node.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    /**
     *
     * @return
     */
    public List<Integer> preorder() {
        List<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode root = this;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                ret.add(root.val);
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop().right;
            }
        }
        return ret;
    }
}
