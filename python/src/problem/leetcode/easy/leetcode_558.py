# coding=utf-8
from src.data_structure.data_structure import QuadTreeNode


class Solution:
    """
    四叉树交集
    """

    def intersect(self, quadTree1: QuadTreeNode,
                  quadTree2: QuadTreeNode) -> QuadTreeNode:
        """
        Time: O(n^4), Space: O(1)
        :param quadTree1:
        :param quadTree2:
        :return:
        """
        if quadTree1.is_leaf:
            return quadTree1 if quadTree1.val else quadTree2
        if quadTree2.is_leaf:
            return quadTree2 if quadTree2.val else quadTree1
        top_left = self.intersect(quadTree1.top_left, quadTree2.top_left)
        top_right = self.intersect(quadTree1.top_right, quadTree2.top_right)
        bottom_left = self.intersect(quadTree1.bottom_left, quadTree2.bottom_left)
        bottom_right = self.intersect(quadTree1.bottom_right, quadTree2.bottom_right)

        if top_left.is_leaf and top_right.is_leaf and bottom_left.is_leaf and\
                bottom_right.is_leaf and top_left.val and top_right.val and \
                bottom_left.val and bottom_right.val:
            return QuadTreeNode(True, True, None, None, None, None)

        return QuadTreeNode(False, False, top_left, top_right, bottom_left, bottom_right)

