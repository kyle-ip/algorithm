# coding=utf-8
from typing import List


class Solution:
    """
    旋转数组
    """

    def reverse(self, nums: List[int], i: int, j: int) -> None:
        """

        :param nums:
        :param i:
        :param j:
        :return:
        """
        while i < j:
            nums[i], nums[j] = nums[j], nums[i]
            i += 1
            j -= 1

    def rotate(self, nums: List[int], k: int) -> None:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :param k:
        :return:
        """
        if not nums or len(nums) == 0 or k <= 0:
            return

        n, m = len(nums), k % len(nums)

        self.reverse(nums, 0, n - 1)
        self.reverse(nums, 0, m - 1)
        self.reverse(nums, m, n - 1)