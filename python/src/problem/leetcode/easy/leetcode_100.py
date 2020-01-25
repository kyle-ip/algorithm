# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    判断二叉树是否相同
    """

    def is_same_tree_recursive(self, p: TreeNode, q: TreeNode) -> bool:
        """
        Time: O(n), Space: O(n)
        :param p:
        :param q:
        :return:
        """
        if not p and not q:
            return True
        if not p or not q:
            return False
        return p.val == q.val and self.is_same_tree_recursive(p.left, q.left)\
               and self.is_same_tree_recursive(p.right, q.right)

    def is_same_tree_iterative(self, p: TreeNode, q: TreeNode) -> bool:
        """
        Time: O(n), Space: O(n)
        :param p:
        :param q:
        :return:
        """
        if not p and not q:
            return True
        if not p or not q:
            return False

        stack = [p, q]
        while len(stack) > 0:
            left = stack.pop()
            right = stack.pop()
            if not left and not right:
                continue
            if not left or not right or left.val != right.val:
                return False
            stack.append(left.left)
            stack.append(right.left)
            stack.append(left.right)
            stack.append(right.right)
        return True
