package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径
 * [树]
 *
 * @author ywh
 * @since 09/01/2020
 */
public class LeetCode257 {

    /**
     *
     * @param root
     * @param cur
     * @param list
     */
    private void dfs(TreeNode root, String cur, List<String> list) {
        if (root == null) {
            return;
        }
        cur += root.val;
        if (root.left == null && root.right == null) {
            list.add(cur);
        }
        dfs(root.left, cur + "->", list);
        dfs(root.right, cur + "->", list);

    }

    /**
     * Time: O(n^2), Space: O(n^2)
     *
     * @param root
     * @return
     */
    public List<String> dfs(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        dfs(root, "", list);
        return list;
    }

    /**
     *
     * @param root
     * @param path
     * @param result
     */
    private void dfs2(TreeNode root, StringBuilder path, List<String> result) {
        if (root == null) {
            return;
        }
        int len = path.length();
        path.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(path.toString());
        } else {
            path.append("->");
            dfs2(root.left, path, result);
            dfs2(root.right, path, result);
        }
        path.setLength(len);
    }

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs2(root, new StringBuilder(), result);
        return result;
    }
}
