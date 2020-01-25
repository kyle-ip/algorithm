# coding=utf-8

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    对称二叉树
    """

    def is_symmetric(self, left: TreeNode, right: TreeNode) -> bool:
        if left and right:
            return left.val == right.val \
                   and self.is_symmetric(left.left, right.right) \
                   and self.is_symmetric(left.right, right.left)
        return not left and not right

    def is_symmetric_tree_recursive(self, root: TreeNode) -> bool:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """
        if not root:
            return True
        else:
            return self.is_symmetric(root.left, root.right)

    def is_symmetric_tree_iterative(self, root: TreeNode) -> bool:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """
        if not root:
            return True
        stack = [root.left, root.right]

        while len(stack) > 0:
            left = stack.pop()
            right = stack.pop()
            if not left and not right:
                continue
            if not left or not right or left.val != right.val:
                return False
            stack.append(left.left)
            stack.append(right.right)
            stack.append(left.right)
            stack.append(right.left)

        return True

