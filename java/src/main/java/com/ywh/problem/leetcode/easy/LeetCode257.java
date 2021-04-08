package com.ywh.problem.leetcode.easy;

import com.ywh.ds.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径
 * [树]
 * 
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 *      输入:
 *              1
 *            /   \
 *           2     3
 *            \
 *             5
 *
 *      输出: ["1->2->5", "1->3"]
 *      解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * 
 * @author ywh
 * @since 09/01/2020
 */
public class LeetCode257 {

    /**
     *
     * @param root
     * @param cur
     * @param ret
     */
    private List<String> dfs(TreeNode root, String cur, List<String> ret) {
        if (root == null) {
            return ret;
        }
        cur += root.val;
        if (root.left == null && root.right == null) {
            ret.add(cur);
        }
        dfs(root.left, cur + "->", ret);
        dfs(root.right, cur + "->", ret);
        return ret;
    }

    /**
     * Time: O(n^2), Space: O(n^2)
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        return dfs(root, "", new ArrayList<>());
    }

    /**
     *
     * @param root
     * @param path
     * @param ret
     */
    private void dfs2(TreeNode root, StringBuilder path, List<String> ret) {
        if (root == null) {
            return;
        }
        // 记录执行添加当前节点以及后续处理前的长度，以便回溯。
        int len = path.length();
        path.append(root.val);
        if (root.left == null && root.right == null) {
            ret.add(path.toString());
        } else {
            path.append("->");
            dfs2(root.left, path, ret);
            dfs2(root.right, path, ret);
        }
        // 回溯，确保在其他分支中以上步骤（->...）没有被重复执行。
        path.setLength(len);
    }

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> ret = new ArrayList<>();
        dfs2(root, new StringBuilder(), ret);
        return ret;
    }
}
