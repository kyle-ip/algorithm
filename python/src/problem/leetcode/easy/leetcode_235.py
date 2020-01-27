# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二叉搜索树的最近公共祖先
    """

    def lowest_common_ancestor_recursive(self, root: TreeNode,
                               p: TreeNode, q: TreeNode) -> TreeNode:
        """
        Time: O(h), Space: O(h)
        :param root:
        :param p:
        :param q:
        :return:
        """

        if p.val < root.val and q.val < root.val:
            return self.lowest_common_ancestor_recursive(root.left, p, q)
        elif p.val > root.val and q.val > root.val:
            return self.lowest_common_ancestor_recursive(root.right, p, q)
        else:
            return root

    def lowest_common_ancestor_iterative(self, root: TreeNode,
                                         p: TreeNode, q: TreeNode) -> TreeNode:
        """

        :param root:
        :param p:
        :param q:
        :return:
        """
        while root:
            if p.val < root.val and q.val < root.val:
                root = root.left
            elif p.val > root.val and q.val > root.val:
                root = root.right
            else:
                return root
        return