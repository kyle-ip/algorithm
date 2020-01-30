# coding=utf-8
from typing import List

from src.data_structure.data_structure import TreeNode


class Solution:
    """
    使用最小花费爬楼梯
    """

    def min_cost_climbing_stairs(self, cost: List[int]) -> int:
        """
        Time: O(n), Space: O(n)
        :param cost:
        :return:
        """

        if not cost or len(cost) == 0:
            return 0

        if len(cost) == 1:
            return cost[0]

        dp = [0 for _ in range(len(cost))]
        dp[0], dp[1] = cost[0], cost[1]
        for i in range(2, len(cost)):
            dp[i] = min(dp[i - 1], dp[i - 2]) + cost[i]
        return min(dp[len(cost) - 1], dp[len(cost) - 2])

