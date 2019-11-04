package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 用中序和后序遍历序列构建二叉树
 * [数组] [树] [DFS]
 * <p>
 * 解法类似 {@link LeetCode105}，注意下标
 *
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode106 {

    /**
     *
     * @param post
     * @param postStart
     * @param postEnd
     * @param inStart
     * @param inPos
     * @return
     */
    private TreeNode buildTree(int[] post, int postStart, int postEnd, int inStart, Map<Integer, Integer> inPos) {
        if (postStart > postEnd) {
            return null;
        }
        int rootIdx = inPos.get(post[postEnd]), leftLen = rootIdx - inStart;
        TreeNode root = new TreeNode(post[postEnd]);

        root.left = buildTree(post, postStart, postStart + leftLen - 1, inStart, inPos);
        root.right = buildTree(post, postStart + leftLen, postEnd - 1, rootIdx + 1, inPos);
        return root;
    }


    /**
     * Time: O(n), Space: O(n)
     *
     * @param in
     * @param post
     * @return
     */
    public TreeNode buildTree(int[] in, int[] post) {
        Map<Integer, Integer> inPos = new HashMap<>(post.length);
        for (int i = 0; i < post.length; i++) {
            inPos.put(in[i], i);
        }
        return buildTree(post, 0, post.length - 1, 0, inPos);
    }
}
