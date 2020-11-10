package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 用中序和后序遍历序列构建二叉树
 * [数组] [树] [DFS]
 *
 * 解法类似 {@link LeetCode105}，注意下标变化
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
        // 对于一棵二叉树：
        //       1
        //      / \
        //     2   4
        //        / \       后序遍历：2, 8, 16, 4, 1
        //       8   16     中序遍历：2, 1, 8, 4, 16

        // pre: 2, 8, 16, 4, [1]
        // in: (2), [1], (8, 4, 16)
        int rootInIdx = inPos.get(post[postEnd]), leftLen = rootInIdx - inStart;
        return new TreeNode(
            post[postEnd],
            buildTree(post, postStart, postStart + leftLen - 1, inStart, inPos),
            buildTree(post, postStart + leftLen, postEnd - 1, rootInIdx + 1, inPos)
        );
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
