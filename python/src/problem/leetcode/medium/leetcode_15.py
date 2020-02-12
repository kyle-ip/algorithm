# coding=utf-8
from typing import List


class Solution:
    """
    三数之和
    """

    def three_sum(self, nums: List[int]) -> List[List[int]]:
        """
        Time: O(n^2), Space: O(1)
        :param nums:
        :return:
        """
        nums.sort()
        res = []
        k = len(nums) - 1
        while k >= 2:
            if nums[k] < 0:
                break
            target, left, right = -nums[k], 0, k - 1
            while left < right:
                if nums[left] + nums[right] < target:
                    left += 1
                elif nums[left] + nums[right] > target:
                    right -= 1
                else:
                    res.append([nums[left], nums[right], nums[k]])
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                    left += 1
                    right -= 1
            while k >= 2 and nums[k] == nums[k - 1]:
                k -= 1
            k -= 1
        return res
