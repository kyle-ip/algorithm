# coding=utf-8
from src.data_structure.data_structure import TreeNode


class Solution:
    """
    合并二叉树
    """

    def merge_trees(self, t1: TreeNode, t2: TreeNode) -> TreeNode:
        """
        Time: O(n), Space: O(n)
        :param t1:
        :param t2:
        :return:
        """

        if not t1 and not t2:
            return None

        if not t1 or not t2:
            return t1 if t1 else t2

        t1.val = t1.val + t2.val
        t1.left = self.merge_trees(t1.left, t2.left)
        t1.right = self.merge_trees(t1.right, t2.right)
        return t1

