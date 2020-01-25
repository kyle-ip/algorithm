# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二叉树的层次遍历
    """

    def level_order_traversal(self, root: TreeNode) -> List[List[int]]:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """

        if not root:
            return []
        result, queue = [], []
        queue.append(root)
        while len(queue) > 0:
            elem = []
            for i in range(len(queue)):
                node = queue.pop(0)
                elem.append(node.val)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            result.append(elem)

        return result
