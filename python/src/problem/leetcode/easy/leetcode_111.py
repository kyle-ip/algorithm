# coding=utf-8

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    二叉树的最小深度
    """

    def min_depth_recursive(self, root: TreeNode) -> int:
        """
        Time: O(n), Space: (n)
        :param root:
        :return:
        """
        if not root:
            return 0

        if not root.left and not root.right:
            return 1

        if not root.left:
            return self.min_depth(root.right)

        if not root.right:
            return self.min_depth(root.left)

        return min(self.min_depth(root.left), self.min_depth(root.right))

    def min_depth_iterative(self, root: TreeNode) -> int:
        """
        Time: O(n), Space: (n)
        :param root:
        :return:
        """
        if not root:
            return 0

        queue = [root]
        depth = 1

        while len(queue) > 0:
            size = len(queue)
            for _ in range(size):
                node = queue.pop(0)
                if not node.left and not node.right:
                    return depth
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            depth += 1

        return depth
