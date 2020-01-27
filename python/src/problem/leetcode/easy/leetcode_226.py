# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    翻转二叉树
    """

    def invert_tree_recursive(self, root: TreeNode) -> TreeNode:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """
        if not root:
            return root
        root.left, root.right = root.right, root.left
        self.invert_tree_recursive(root.left)
        self.invert_tree_recursive(root.right)

        return root

    def invert_tree_iterative(self, root: TreeNode) -> TreeNode:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """
        if not root:
            return root
        queue = [root]
        while len(queue) > 0:
            node = queue.pop(0)
            node.left, node.right = node.right, node.left
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        return root

