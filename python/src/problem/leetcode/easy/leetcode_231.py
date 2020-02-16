# coding=utf-8
from typing import List


class Solution:
    """
    打家劫舍
    """

    def rob(self, nums: List[int]) -> int:
        """

        :param nums:
        :return:
        """

        if not nums:
            return 0

        x0 = x1 = 0
        for num in nums:
            cur = max(x1, x0 + num)
            x0 = x1
            x1 = cur

        return x1