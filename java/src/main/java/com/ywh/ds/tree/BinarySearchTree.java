package com.ywh.ds.tree;

import lombok.Data;

/**
 * 二叉搜索树
 *
 * @author ywh
 * @since 2020/10/28/028
 */
@Data
public class BinarySearchTree {

    public TreeNode root;

    /**
     * 查找节点
     *
     * @param val
     * @return
     */
    public TreeNode find(int val) {
        TreeNode p = root;
        while (p != null && p.val != val) {
            p = p.val > val? p.left: p.right;
        }
        return p;
    }

    /**
     * 修改节点
     *
     * @param val
     * @param newVal
     */
    public void update(int val, int newVal) {
        if (root != null && root.val == val) {
            root.val = newVal;
        }
        TreeNode p = root;
        while (p != null && p.val != val) {
            p = p.val > val ? p.left : p.right;
        }
        if (p != null) {
            p.val = newVal;
        }
    }

    /**
     * 插入节点
     *
     * @param val
     */
    public void insert(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return;
        }
        TreeNode p = root;
        while (true) {
            if (p.val < val) {
                if (p.right == null) {
                    p.right = new TreeNode(val);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new TreeNode(val);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 删除节点
     *
     * @param val
     */
    public void delete(int val) {
        // p 为待删除节点，pP 为待删除节点的父节点。
        TreeNode p = root, pP = null;
        while (p != null && p.val != val) {
            pP = p;
            p = p.val > val ? p.left : p.right;
        }
        if (p == null) {
            return;
        }
        // 待删除节点 p 有两个子节点：
        // 将其右子树的最小节点 min 的值复制到待删除节点，由于 min 没有左节点，问题从删除 p 转变为删除只有一个子节点的 min。
        //          16 (pP)                  16                     16
        //            \                        \                      \
        //            [18] (p)                  19                     19
        //            /  \            =>       /  \           =>      /  \
        //          17    25 (minP)          17    25 (pP)          17    25 (pP)
        //               /  \                     /  \                   /  \
        //        (min) 19    27            (p) 19    27               26    27
        //                \                       \                  (child)
        //                 26                      26 (child)
        if (p.left != null && p.right != null) {
            // 指针从待删除节点的右子树向左出发，查找右子树中的最小节点 min 及其父节点 minP。
            TreeNode min = p.right, minP = p;
            while (min.left != null) {
                minP = min;
                min = min.left;
            }
            // 把 min 的值覆盖到 p 上（后续就转变成删除 min）。
            p.val = min.val;
            p = min;
            pP = minP;
        }
        // 待删除节点 p 是叶子节点或仅有一个子节点：
        // 取其子节点 child，挂载到 p 的父节点 pP 上。
        TreeNode child = p.left != null ? p.left : p.right;
        if (pP == null) {
            root = child;
        } else if (pP.left == p) {
            pP.left = child;
        } else {
            pP.right = child;
        }
    }

}
