# coding=utf-8

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    路径和是否等于给定值
    """

    def has_path_sum_recursive(self, root: TreeNode, sum: int) -> bool:
        if not root:
            return False
        if not root.left and not root.right:
            return sum == root.val
        return self.has_path_sum_recursive(root.left, sum - root.val) \
               or self.has_path_sum_recursive(root.right, sum - root.val)

    def has_path_sum_iterative(self, root: TreeNode, sum: int) -> bool:
        if not root:
            return False
        node_stack, sum_stack = [root], [sum]
        while len(node_stack) > 0:
            node = node_stack.pop()
            s = sum_stack.pop()
            if not node.left and not node.right and node.val == s:
                return True
            if node.left:
                node_stack.append(node.left)
                sum_stack.append(s - node.val)
            if node.right:
                node_stack.append(node.right)
                sum_stack.append(s - node.val)
        return False

