# coding=utf-8
from typing import List
import sys


class Solution:
    """
    求和最接近目标值的三个数
    """

    def three_sum_closest(self, nums: List[int], target: int) -> int:
        """
        Time: O(n^2), Space: O(1)
        :param nums:
        :return:
        """
        res = -sys.maxsize
        k = len(nums) - 1
        nums.sort()
        while k >= 2:
            left, right = 0, k - 1
            while left < right:
                s = nums[left] + nums[right] + nums[k]
                if s == target:
                    return target
                if s < target:
                    left += 1
                else:
                    right -= 1
                if abs(target - s) < abs(target - res):
                    res = s
            k -= 1
        return res