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

    /**
     *
     * @param o
     * @return
     */
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

    /**
     * 
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    /**
     * 前序遍历
     *
     * @return
     */
    public List<Integer> preOrder() {
        List<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = this;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                ret.add(node.val);
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop();
                node = node.right;
            }
        }
        return ret;
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public List<Integer> inOrder() {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> ret = new ArrayList<>();
        TreeNode node = this;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                ret.add(node.val);
                node = node.right;
            }
        }
        return ret;
    }

    /**
     * 后序遍历
     *
     * @return
     */
    public List<Integer> postOrder() {
        LinkedList<Integer> ret = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(this);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 使用链表插入元素到头部
            ret.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return ret;
    }

    /**
     * 层序遍历
     *
     * @return
     */
    public List<Integer> levelOrder() {
        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                ret.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return ret;
    }

    /**
     * 最大深度
     *
     * @return
     */
    public int maxDepth() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return depth;
//        if (root == null) {
//            return 0;
//        }
//        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 最小深度
     *
     * @return
     */
    public int minDepth() {
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return depth;
//        // 空树为 0。
//        if (root == null) {
//            return 0;
//        }
//
//        // 无子树最小深度为 1。
//        if (root.left == null && root.right == null) {
//            return 1;
//        }
//        // 只有左/右子树，则对左/右子树求深度。
//        if (root.left == null || root.right == null) {
//            return root.left == null? minDepthRecursive(root.right) + 1: minDepthRecursive(root.left) + 1;
//        }
//
//        // 左右子树都存在，取较小者。
//        return Math.min(minDepthRecursive(root.left), minDepthRecursive(root.right)) + 1;
    }

    /**
     * 直径
     *
     * @return
     */
    public int diameter() {
        int[] ret = new int[1];
        diameter(this, ret);
        return ret[0];
    }

    /**
     *
     * @param root
     * @param ret
     * @return
     */
    private int diameter(TreeNode root, int[] ret) {
        if (root == null) {
            return 0;
        }
        int left = diameter(root.left, ret), right = diameter(root.right, ret);
        ret[0] = Math.max(ret[0], left + right);

        // 取其中一条路径的值（再加上当前节点的 1），返回上一层。
        return Math.max(left, right) + 1;
    }
}
