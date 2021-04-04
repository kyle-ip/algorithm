package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序与后序遍历序列构造二叉树
 * [数组] [树] [深度优先搜索]
 * 
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 *      你可以假设树中没有重复的元素。
 * 例如给出
 *      中序遍历 inorder = [9,3,15,20,7]
 *      后序遍历 postorder = [9,15,7,20,3]
 *      返回如下的二叉树：
 *          3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode106 {

    /**
     *
     * @param post              后序遍历数组
     * @param postStartIdx      后序遍历起始位置
     * @param postEndIdx        后序遍历结束位置
     * @param inStartIdx        中序遍历起始位置
     * @param inPos             中序遍历下标缓存
     * @return
     */
    private TreeNode buildTree(int[] post, int postStartIdx, int postEndIdx, int inStartIdx, Map<Integer, Integer> inPos) {
        if (postStartIdx > postEndIdx) {
            return null;
        }
        // 对于一棵二叉树：
        //       1
        //      / \
        //     2   4
        //        / \       后序遍历：2, 8, 16, 4, 1
        //       8   16     中序遍历：2, 1, 8, 4, 16

        // post: 2, 8, 16, 4, [1]
        // in: (2), [1], (8, 4, 16)
        int rootInIdx = inPos.get(post[postEndIdx]), leftLen = rootInIdx - inStartIdx;
        return new TreeNode(
            post[postEndIdx],
            buildTree(post, postStartIdx, postStartIdx + leftLen - 1, inStartIdx, inPos),
            buildTree(post, postStartIdx + leftLen, postEndIdx - 1, rootInIdx + 1, inPos)
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
