# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二叉树的最大深度
    """

    def max_depth_recursive(self, root: TreeNode) -> int:
        """
        Time: O(n), Space: (n)
        :param root:
        :return:
        """
        if not root:
            return 0
        return max(self.max_depth(root.left), self.max_depth(root.right)) + 1

    def max_depth_iterative(self, root: TreeNode) -> int:
        """
        Time: O(n), Space: (n)
        :param root:
        :return:
        """
        if not root:
            return 0
        queue = [root]
        depth = 0
        while len(queue) > 0:
            size = len(queue)
            for i in range(size):
                node = queue.pop(0)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            depth += 1

        return depth
