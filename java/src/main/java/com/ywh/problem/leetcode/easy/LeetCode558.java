package com.ywh.problem.leetcode.easy;

import com.ywh.model.QuadTreeNode;

/**
 * 四叉树交集
 * [树]
 *
 * @author ywh
 * @since 2019/12/4/004
 */
public class LeetCode558 {

    /**
     * Time: O(n^4), Space: O(1)
     *
     * @param quadTree1
     * @param quadTree2
     * @return
     */
    public QuadTreeNode intersect(QuadTreeNode quadTree1, QuadTreeNode quadTree2) {

        // 如果其中一个节点是叶节点，该节点值为 true，返回这个节点，否则返回另一个节点
        if (quadTree1.isLeaf) {
            return quadTree1.val? quadTree1: quadTree2;
        }
        if (quadTree2.isLeaf) {
            return quadTree2.val? quadTree2: quadTree1;
        }

        // 两个节点都不是叶节点，则继续递归
        QuadTreeNode topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        QuadTreeNode topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        QuadTreeNode bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        QuadTreeNode bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        // 如果四个子节点都是叶子节点，且都为 true，返回叶子节点
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf && topLeft.val && topRight.val && bottomLeft.val && bottomRight.val) {
            return new QuadTreeNode(true, true, null, null, null, null);
        }

        // 否则返回非叶子节点
        return new QuadTreeNode(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
