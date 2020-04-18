# coding=utf-8
from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二叉搜索树的范围和
    """

    def range_sum_BST_recursive(self, root: TreeNode, L: int, R: int) -> int:
        """
        Time: O(n), Space: O(n)

        :param root:
        :param L:
        :param R:
        :return:
        """
        if not root:
            return 0
        if root.val < L:
            return self.range_sum_BST_recursive(root.right, L, R)
        if root.val > R:
            return self.range_sum_BST_recursive(root.left, L, R)
        return root.val + self.range_sum_BST_recursive(root.left, L, R) + self.range_sum_BST_recursive(root.right, L, R)

    def range_sum_BST_iterative(self, root: TreeNode, L: int, R: int) -> int:
        """
        Time: O(n), Space: O(n)

        :param root:
        :param L:
        :param R:
        :return:
        """
        stack = [root]
        s = 0
        while len(stack) > 0:
            node = stack.pop()
            if not node:
                continue
            if node.val < L:
                stack.append(node.right)
            elif node.val > R:
                stack.append(node.left)
            else:
                s += node.val
                stack.append(node.left)
                stack.append(node.right)
        return s

