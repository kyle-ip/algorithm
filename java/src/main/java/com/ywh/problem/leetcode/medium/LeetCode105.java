package com.ywh.problem.leetcode.medium;

import com.ywh.ds.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 * [数组] [树] [深度优先搜索]
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 *      你可以假设树中没有重复的元素。
 * 例如给出
 *      前序遍历 preorder = [3,9,20,15,7]
 *      中序遍历 inorder = [9,3,15,20,7]
 *      返回如下的二叉树：
 *          3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 * 提示:
 *      1 <= preorder.length <= 3000
 *      inorder.length == preorder.length
 *      -3000 <= preorder[i], inorder[i] <= 3000
 *      preorder 和 inorder 均无重复元素
 *      inorder 均出现在 preorder
 *      preorder 保证为二叉树的前序遍历序列
 *      inorder 保证为二叉树的中序遍历序列
 * 
 * @author ywh
 * @since 03/11/2019
 */
public class LeetCode105 {

    /**
     * @param preorder      前序遍历数组
     * @param preStartIdx 前序遍历起始位置
     * @param preEndIdx   前序遍历结束位置
     * @param inStartIdx  中序遍历起始位置
     * @param inPos    中序遍历数字下标缓存
     * @return
     */
    private TreeNode buildTree(int[] preorder, int preStartIdx, int preEndIdx, int inStartIdx, Map<Integer, Integer> inPos) {

        // 对于一棵二叉树：
        //       1
        //      / \
        //     2   4
        //        / \       前序遍历：1, 2, 4, 8, 16
        //       8   16     中序遍历：2, 1, 8, 4, 16

        if (preStartIdx > preEndIdx) {
            return null;
        }

        // 通过前序遍历确定树的根节点的值，rootVal = pre[0]。
        // 再根据根节点的值在中序遍历找到下标，rootIdx = inPos.get(rootVal)。
        // pre: [1], 2, 4, 8, 16
        // in: (2), [1], (8, 4, 16)
        int rootVal = preorder[preStartIdx], inRootIdx = inPos.get(rootVal);

        // 由中序遍历中根节点的下标可以得出应该划分给左子树和右子树的节点个数，左子树 rootInIdx - inStart 个，剩余给右子树。
        int leftLen = inRootIdx - inStartIdx;

        // 因此在前序遍历中划分左右子树范围，据此递归构造二叉树。
        // pre: [1], (2), (4, 8, 16)
        //           left   right
        return new TreeNode(rootVal,
            // 左 [preStart+1: preStart+leftLen]
            buildTree(preorder, preStartIdx + 1, preStartIdx + leftLen, inStartIdx, inPos),
            // 右 [preStart+leftLen: preEnd]
            buildTree(preorder, preStartIdx + leftLen + 1, preEndIdx, inRootIdx + 1, inPos)
        );
    }

    /**
     * Time: O(n), Space: O(n)
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 在哈希表中缓存中序遍历中值（key）与下标的对应关系（value），可避免每次都做线性查找。
        Map<Integer, Integer> inPos = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            inPos.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, 0, inPos);
    }
}
