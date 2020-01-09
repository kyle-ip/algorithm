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
    private void binaryTreePaths(TreeNode root, String cur, List<String> list) {
        if (root == null) {
            return;
        }
        cur += root.val;
        if (root.left == null && root.right == null) {
            list.add(cur);
        }
        binaryTreePaths(root.left, cur + "->", list);
        binaryTreePaths(root.right, cur + "->", list);

    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        binaryTreePaths(root, "", list);
        return list;
    }

}
