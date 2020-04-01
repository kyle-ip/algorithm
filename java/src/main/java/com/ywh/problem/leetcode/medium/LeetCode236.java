package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中节点的最近公共祖先
 * [树]
 *
 * @author ywh
 * @since 12/11/2019
 */
public class LeetCode236 {

    /**
     * 在 root 上找到 node，并把路径记录在 path
     *
     * @param root
     * @param node
     * @param path
     * @return
     */
    private boolean search(TreeNode root, TreeNode node, List<TreeNode> path) {

        // root 为空，表示到叶子节点仍未找到，返回 false；否则把节点添加到 path
        if (root == null) {
            return false;
        }
        path.add(root);

        // 如果找到 node，返回 true
        if (root == node) {
            return true;
        }

        // 否则在 root 的左右子树继续查找
        boolean ret = search(root.left, node, path) || search(root.right, node, path);
        if (ret) {
            return true;
        }

        // 如果在左右子树都找不到，表示目标节点不在当前子树中，此时要移除当前节点再返回 false
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * 辅助路径解法
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lcaWithPath(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = new ArrayList<>(), qPath = new ArrayList<>();
        search(root, p, pPath);
        search(root, q, qPath);
        int i = 0, len = Math.min(pPath.size(), qPath.size());

        // 跳过相同节点
        while (i < len && pPath.get(i) == qPath.get(i)) {
            ++i;
        }

        // 已经遍历完较短路径，或两条路径从 i 开始已分叉，返回上一个节点即可
        return pPath.get(i - 1);

    }


    /**
     * 如果当前节点为空，或者等于其中某个目标节点，返回当前节点；
     * 否则递归到左右子树处理，返回值分别为 left 和 right；
     * 如果 left 和 right 非空，说明在左右子树上各找到一个节点，于是当前的根节点就是最近公共祖先；
     * 如果 left 和 right 其中一个为空，则返回非空的节点（表示两个节点都在这边）；
     * 如果 left 和 right 都为空，返回空指针（表示在该子树上找不到节点）。
     *
     * 如果 p 和 q 都在树中，则返回最近公共祖先；如果只有一个节点在树中，则返回该节点。
     *
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lcaExtend(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lcaExtend(root.left, p, q);
        TreeNode right = lcaExtend(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }

    }

}
