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
     * @param pre      前序遍历数组
     * @param preStart 前序遍历起始位置
     * @param preEnd   前序遍历结束位置
     * @param inStart  中序遍历起始位置
     * @param inPos    中序遍历数字下标缓存
     * @return
     */
    private TreeNode buildTree(int[] pre, int preStart, int preEnd, int inStart, Map<Integer, Integer> inPos) {

        // 对于一棵二叉树：
        //       1
        //      / \
        //     2   4
        //        / \       前序遍历：1, 2, 4, 8, 16
        //       8   16     中序遍历：2, 1, 8, 4, 16

        if (preStart > preEnd) {
            return null;
        }

        // 通过前序遍历确定树的根节点的值，rootVal = pre[0]。
        // 再根据根节点的值在中序遍历找到下标，rootIdx = inPos.get(rootVal)。
        // pre: [1], 2, 4, 8, 16
        // in: (2), [1], (8, 4, 16)
        int rootVal = pre[preStart], rootInIdx = inPos.get(rootVal);

        // 由中序遍历中根节点的下标可以得出应该划分给左子树和右子树的节点个数，左子树 rootInIdx - inStart 个，剩余给右子树。
        // 因此在前序遍历中划分左右子树范围：左 [preStart+1:preStart+leftLen]，右 [preStart+leftLen:preEnd]。据此递归构造二叉树。
        // pre: [1], (2), (4, 8, 16)
        //           left   right
        int leftLen = rootInIdx - inStart;
        return new TreeNode(rootVal,
            buildTree(pre, preStart + 1, preStart + leftLen, inStart, inPos),
            buildTree(pre, preStart + leftLen + 1, preEnd, rootInIdx + 1, inPos)
        );
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param pre
     * @param in
     * @return
     */
    public TreeNode buildTree(int[] pre, int[] in) {
        // 在哈希表中缓存中序遍历中值（key）与下标的对应关系（value），可避免每次都做线性查找。
        Map<Integer, Integer> inPos = new HashMap<>(in.length);
        for (int i = 0; i < in.length; i++) {
            inPos.put(in[i], i);
        }
        return buildTree(pre, 0, pre.length - 1, 0, inPos);
    }
}
