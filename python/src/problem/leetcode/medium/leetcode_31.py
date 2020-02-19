# coding=utf-8
from typing import List


class Solution:
    """
    数组的下一个排列
    """

    def next_permutation(self, nums: List[int]) -> None:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :return:
        """
        if not nums:
            return

        k = len(nums) - 2
        while k >= 0 and nums[k + 1] <= nums[k]:
            k -= 1

        if k >= 0:
            i = len(nums) - 1
            while i > k and nums[i] <= nums[k]:
                i -= 1
            nums[i], nums[k] = nums[k], nums[i]

        i, j = k + 1, len(nums) - 1
        while i < j:
            nums[i], nums[j] = nums[j], nums[i]
            i += 1
            j -= 1