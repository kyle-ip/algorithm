# coding=utf-8
from typing import List
import sys


class Solution:
    """
    四数之和
    """

    def four_sum(self, nums: List[int], target: int) -> List[List[int]]:
        """
        Time: O(n^3), Space: O(1)
        :param nums:
        :return:
        """
        res = []
        if not nums:
            return res
        nums.sort()
        p = len(nums) - 1
        while p >= 3:
            if 4 * nums[p] < target:
                break
            k = p - 1
            while k >= 2:
                if 3 * nums[k] + nums[p] < target:
                    break
                left, right, t = 0, k - 1, target - nums[p] - nums[k]
                while left < right:
                    if nums[left] + nums[right] < t:
                        left += 1
                    elif nums[left] + nums[right] > t:
                        right -= 1
                    else:
                        res.append([nums[left], nums[right], nums[k], nums[p]])
                        while left < right and nums[left + 1] == nums[left]:
                            left += 1
                        while left < right and nums[right - 1] == nums[right]:
                            right -= 1
                        left += 1
                        right -= 1
                while k >= 2 and nums[k - 1] == nums[k]:
                    k -= 1
                k -= 1
                while p >= 3 and nums[p - 1] == nums[p]:
                    p -= 1
            p -= 1
        return res

