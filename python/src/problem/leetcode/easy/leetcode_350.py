# coding=utf-8
from typing import List


class Solution:
    """
    两个数组的交集 II
    """

    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        """
        Time: O(m+n), Space: O(m+k)
        :param nums1:
        :param nums2:
        :return:
        """
        counter = {}

        for num in nums1:
            cnt = counter[num] if num in counter else 0
            counter[num] = cnt + 1

        l = []
        for num in nums2:
            cnt = counter[num] if num in counter else 0
            if cnt > 0:
                l.append(num)
                counter[num] = cnt - 1

        res = [0 for _ in range(len(l))]
        for i in range(len(l)):
            res[i] = l[i]

        return res

