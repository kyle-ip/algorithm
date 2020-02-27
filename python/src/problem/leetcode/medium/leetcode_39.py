# coding=utf-8
from typing import List


class Solution:
    """
    组合总和
    """

    def comb_sum(self, nums: List[int], target: int, start: int, elem: List[int], result: List[List[int]]):
        """

        :param nums:
        :param target:
        :param start:
        :param elem:
        :param result:
        :return:
        """
        if target == 0:
            result.append(list(elem))
            return
        if target < 0:
            return
        for i in range(start, len(nums)):
            elem.append(nums[i])
            self.comb_sum(nums, target - nums[i], i, elem, result)
            elem.pop()

    def combination_sum(self, candidates: List[int], target: int) -> List[List[int]]:
        """

        :param candidates:
        :param target:
        :return:
        """
        result, elem = [], []
        self.comb_sum(candidates, target, 0, elem, result)
        return result

