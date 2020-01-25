# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二叉树的逆层序遍历
    """

    def level_order_traversal_from_bottom(self, root: TreeNode) -> List[List[int]]:
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

        left, right = 0, len(result) - 1
        while left < right:
            tmp = result[left]
            result[left] = result[right]
            result[right] = tmp
            left += 1
            right -= 1
        return result

