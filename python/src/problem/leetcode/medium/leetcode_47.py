# coding=utf-8
from typing import List, Set, Tuple


class Solution:
    """
    数组的全排列
    """

    def permute_rec(self, nums: List[int], start: int, result: Set[Tuple[int]]):
        """

        :param nums:
        :param start:
        :param result:
        :return:
        """
        if start == len(nums):
            result.add(tuple(nums))
            return
        for i in range(start, len(nums)):
            nums[start], nums[i] = nums[i], nums[start]
            self.permute_rec(nums, start + 1, result)
            nums[start], nums[i] = nums[i], nums[start]

    def permute_unique_using_set(self, nums: List[int]) -> List[List[int]]:
        """
        Time: O(n*n!), Space: O(n)
        :param nums:
        :return:
        """
        if not nums:
            return []

        result = set()
        self.permute_rec(nums, 0, result)
        return list(result)

    def next_permutation(self, nums: List[int]) -> bool:
        """

        :param nums:
        :return:
        """
        n, p = len(nums), len(nums) - 2
        while p >= 0 and nums[p] >= nums[p + 1]:
            p -= 1
        if p >= 0:
            i = n - 1
            while i > p and nums[i] <= nums[p]:
                i -= 1
            nums[p], nums[i] = nums[i], nums[p]
        left, right = p + 1, n - 1
        while left < right:
            nums[left], nums[right] = nums[right], nums[left]
            left, right = left + 1, right - 1
        return p != -1

    def permute_unique_using_next_permutation(self, nums: List[int]) -> List[List[int]]:
        """
        Time: O(n*n!), Space: O(1)
        :param nums:
        :return:
        """

        if not nums:
            return []
        nums.sort()
        result = [nums]
        while self.next_permutation(nums):
            result.append(list(nums))
        return result
