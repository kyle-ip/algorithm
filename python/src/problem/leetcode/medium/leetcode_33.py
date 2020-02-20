# coding=utf-8
from typing import List


class Solution:
    """
    搜索旋转排序数组
    """

    def search(self, nums: List[int], target: int) -> int:
        """
        Time: O(log(n)), Space: O(1)
        :param nums:
        :param target:
        :return:
        """

        if not nums:
            return -1

        low, high = 0, len(nums) - 1
        while low <= high:
            mid = low + (high - low) // 2
            if nums[mid] == target:
                return mid
            if nums[mid] >= nums[low]:
                if nums[low] <= target < nums[mid]:
                    high = mid - 1
                else:
                    low = mid + 1
            else:
                if nums[mid] < target <= nums[high]:
                    low = mid + 1
                else:
                    high = mid - 1
        return -1

