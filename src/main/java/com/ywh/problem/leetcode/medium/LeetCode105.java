package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 用前序和中序遍历序列构建二叉树
 * [数组] [树] [DFS]
 *
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode105 {

    /**
     *
     * @param pre       前序遍历数组
     * @param preStart  前序遍历起始位置
     * @param preEnd    前序遍历结束位置
     * @param inStart   中序遍历起始位置
     * @param inPos     中序遍历数字下标缓存
     * @return
     */
    private TreeNode buildTree(int[] pre, int preStart, int preEnd, int inStart, Map<Integer, Integer> inPos) {
        if (preStart > preEnd) {
            return null;
        }
        // 在哈希表中找到根节点在中序遍历数组中的位置，以及中序遍历数组左子树的节点数（左半边长度）
        int rootIdx = inPos.get(pre[preStart]), leftLen = rootIdx - inStart;
        TreeNode root = new TreeNode(pre[preStart]);

        root.left = buildTree(pre, preStart + 1, preStart + leftLen, inStart, inPos);
        root.right = buildTree(pre, preStart + leftLen + 1, preEnd, rootIdx + 1, inPos);
        return root;
    }

    /**
     * 先通过前序遍历确定树的根节点；
     * pre: [1], 2, 4, 8, 16
     *
     * 再在中序遍历找到根节点，这步避免每次都做线性查找，可使用哈希表记录中序遍历中每个数字的下标；
     * in: (2), [1], (8, 4, 16)
     *
     * 此时可确定根节点的左右子树的前序、中序遍历；
     * left: pre: 2
     * left: in: 2
     * right: pre: 4, 8, 16
     * right: in: 8, 4, 16
     *
     * 然后递归调用构建方法即可
     *
     * Time: O(n), Space: O(n)
     *
     * @param pre
     * @param in
     * @return
     */
    public TreeNode buildTree(int[] pre, int[] in) {
        Map<Integer, Integer> inPos = new HashMap<>(in.length);
        for (int i = 0; i < in.length; i++) {
            inPos.put(in[i], i);
        }
        return buildTree(pre, 0, pre.length - 1, 0, inPos);
    }
}
