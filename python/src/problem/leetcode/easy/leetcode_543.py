# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二叉树的直径
    """

    def max_depth(self, root: TreeNode, d: List[int]) -> int:
        """

        :param root:
        :param d:
        :return:
        """
        if not root:
            return 0
        left = self.max_depth(root.left, d)
        right = self.max_depth(root.right, d)
        d[0] = max(d[0], left + right)
        return max(left, right) + 1


    def diameter_of_binary_tree(self, root: TreeNode) -> int:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """
        d = [0]
        self.max_depth(root, d)
        return d[0]
