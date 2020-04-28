# coding=utf-8
from typing import List
from src.data_structure.data_structure import TreeNode

class Solution:
    """
    二叉树的右视图

    """

    def right_side_view(self, root: TreeNode) -> List[int]:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """

        if not root:
            return []
        ret, queue = [], [root]
        while len(queue) > 0:
            ret.append(queue[0].val)
            size = len(queue)
            for i in range(size):
                node = queue.pop(0)
                if node.right:
                    queue.append(node.right)
                if node.left:
                    queue.append(node.left)

        return ret

    def dfs(self, root: TreeNode, ret: List[int], level: int):
        """

        :param root:
        :param ret:
        :param level:
        :return:
        """
        if not root:
            return
        if level == len(ret):
            ret.append(root.val)
        self.dfs(root.right, ret, level + 1)
        self.dfs(root.left, ret, level + 1)


    def right_side_view2(self, root: TreeNode) -> List[int]:
        """
        Time: O(n), Space: O(n)
        :param root:
        :return:
        """
        ret = []
        self.dfs(root, ret, 0)
        return ret

