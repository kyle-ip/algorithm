# coding=utf-8
from typing import List


class Solution:
    """
    寻找数组的平衡点
    """

    def pivot_index1(self, nums: List[int]) -> int:
        """
        Time: O(n), Space: O(n)
        :param nums:
        :return:
        """
        if not nums:
            return -1
        pre_sum = [0 for _ in range(len(nums) + 1)]
        for i in range(1, len(nums) + 1):
            pre_sum[i] = pre_sum[i - 1] + nums[i - 1]
        for i in range(len(nums)):
            if pre_sum[i] == pre_sum[len(nums)] - pre_sum[i + 1]:
                return i
        return -1

    def pivot_index2(self, nums: List[int]) -> int:
        """
        Time: O(n), Space: O(n)
        :param nums:
        :return:
        """
        if not nums:
            return -1
        s = total = 0
        for num in nums:
            total += num

        for i, num in enumerate(nums):
            if s == total - s - num:
                return i
            s += num
        return -1
