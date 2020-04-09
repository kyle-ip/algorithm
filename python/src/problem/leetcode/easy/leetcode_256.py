# coding=utf-8
from typing import List


class Solution:
    """
    粉刷房子
    """

    def min_cost(self, costs: List[List[int]]) -> int:
        """
        Time: O(n), Space: O(1)

        :param costs:
        :return:
        """
        if not costs:
            return 0

        pre0 = pre1 = pre2 = 0
        for i in range(1, len(costs) + 1):
            cur0 = min(pre1, pre2) + costs[i - 1][0]
            cur1 = min(pre0, pre2) + costs[i - 1][1]
            cur2 = min(pre0, pre1) + costs[i - 1][2]
            pre0, pre1, pre2 = cur0, cur1, cur2
        return min(pre0, pre1, pre2)
