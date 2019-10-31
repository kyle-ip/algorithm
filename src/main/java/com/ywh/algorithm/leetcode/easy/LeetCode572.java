package com.ywh.algorithm.leetcode.easy;

import com.ywh.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 树 t 是否等于树 s 的子树
 * [树]
 *
 * @author ywh
 * @since 2/20/2019
 */
public class LeetCode572 {

    /**
     * 判断是否相同的二叉树
     *
     * @param s
     * @param t
     * @return
     */
    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        return s.val == t.val
            && isSameTree(s.left, t.left)
            && isSameTree(s.right, t.right);
    }

    /**
     * Time: O(m*n), Space: O(h)
     * Time: O(m*n), Space: O(h)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) {
            return false;
        }
        return isSameTree(s, t)
            || isSubtree(s.left, t)
            || isSubtree(s.right, t);
    }

    private Map<TreeNode, Integer> map = new HashMap<>();

    /**
     * 计算二叉树 Hash 值
     *
     * @param t
     * @return
     */
    private String computeHash(TreeNode t) {
        if (t == null) {
            return "#";
        }
        String left = computeHash(t.left);
        String right = computeHash(t.right);
        String hash = left + t.val + right;
        map.put(t, hash.hashCode());
        return hash;
    }

    private boolean isSub(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) {
            return false;
        }
        return map.get(s).equals(map.get(t))
            || isSubtree(s.left, t)
            || isSubtree(s.right, t);
    }

    /**
     * Time: O(m+n), Space: O(m+n)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtreeHash(TreeNode s, TreeNode t) {
        computeHash(s);
        computeHash(t);
        return isSub(s, t);
    }
}
