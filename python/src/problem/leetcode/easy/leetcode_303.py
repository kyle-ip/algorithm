# coding=utf-8

from typing import List


class Solution:
    """
    不可变数组的区间和查询
    """

    class NumArray:

        prefix_sum = []

        def __init__(self, nums: List[int]):
            """

            :param nums:
            """
            self.prefix_sum = [0 for _ in range(len(nums) + 1)]
            for i in range(1, len(nums) + 1):
                self.prefix_sum[i] = self.prefix_sum[i - 1] + nums[i - 1]

        def sumRange(self, i: int, j: int) -> int:
            """

            :param i:
            :param j:
            :return:
            """
            return self.prefix_sum[j + 1] - self.prefix_sum[i]
