# coding=utf-8
from typing import List


class Solution:
    """
    Excel 表列序号
    """

    def majority_element_with_hashmap(self, nums: List[int]) -> int:
        """
        Time: O(n), Space: O(n)
        :param nums:
        :return:
        """
        cache = {}
        max_num, max_count = 0, 0
        for num in nums:
            if num not in cache:
                cache[num] = 1
            else:
                cache[num] += + 1
            if cache[num] > max_count:
                max_count = cache[num]
                max_num = num
        return max_num

    def majority_element(self, nums: List[int]) -> int:
        """
        Time: O(n), Space: O(1)
        :param nums:
        :return:
        """
        cur, count = nums[0], 1
        for i in range(1, len(nums)):
            if nums[i] == cur:
                count += 1
            elif count > 0:
                count -= 1
            else:
                cur = nums[i]
                count = 1
        return cur
