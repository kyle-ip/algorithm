# coding=utf-8
from typing import List


class Solution:
    """
    不限次数的买卖股票的最大利润
    """

    def max_profit_greedy(self, prices: List[int]) -> int:
        """
        Time: O(n), Space: O(1)
        :param prices:
        :return:
        """
        if not prices or len(prices) == 0:
            return 0
        profit = 0
        for i in range(1, len(prices)):
            if prices[i] > prices[i - 1]:
                profit += prices[i] - prices[i - 1]
        return profit

    def max_profit_local_max_min(self, prices: List[int]) -> int:
        """
        Time: O(n), Space: O(1)
        :param prices:
        :return:
        """
        if not prices or len(prices) == 0:
            return 0

        profit, i = 0, 0
        while i < len(prices) - 1:
            while i < len(prices) - 1 and prices[i + 1] < prices[i]:
                i += 1
            buy = prices[i]
            while i < len(prices) - 1 and prices[i + 1] >= prices[i]:
                i += 1
            sell = prices[i]
            profit += sell - buy
        return profit