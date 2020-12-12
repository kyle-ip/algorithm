package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的最近公共祖先
 * [树]
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *              3
 *            /   \
 *          5       1
 *        /   \   /   \
 *       6     2 0     8
 *            / \
 *           7   4
 * 示例 1：
 *      输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *      输出: 3
 *      解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2：
 *      输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *      输出: 5
 *      解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * 说明：
 *      所有节点的值都是唯一的。
 *      p、q 为不同节点且均存在于给定的二叉树中。
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
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lcaExtend(TreeNode root, TreeNode p, TreeNode q) {
        // 如果当前节点为空，或者等于其中某个目标节点，返回当前节点。
        if (root == null || root == p || root == q) {
            return root;
        }
        // 否则递归到左右子树处理，返回值分别为 left 和 right。
        TreeNode left = lcaExtend(root.left, p, q), right = lcaExtend(root.right, p, q);

        // 如果 left 和 right 非空，说明在左右子树上各找到一个节点。
        // 即都不可能为对方的祖先，于是当前的根节点就是最近公共祖先。
        if (left != null && right != null) {
            return root;
        }
        // 如果 left 和 right 其中一个为空，则返回非空的节点（表示两个节点都在同一边）。
        // 如果 left 和 right 都为空，返回空指针（表示在该子树上找不到节点）。
        return left != null? left: right;
    }

}
