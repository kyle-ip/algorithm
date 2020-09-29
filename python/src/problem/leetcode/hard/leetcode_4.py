# coding=utf-8
from typing import List

class Solution:
    """
    求两个有序数组的中位数
    """

    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        """
        Time: O(m+n) Space: O(n)
        :param nums1:
        :param nums2:
        :return:
        """
        idx = idx1 = idx2 = 0
        half = (len(nums1) + len(nums2)) // 2 + 1
        arr = []
        while idx < half:
            num = 0
            if idx1 < len(nums1) and idx2 < len(nums2):
                if nums1[idx1] < nums2[idx2]:
                    num = nums1[idx1]
                    idx1 += 1
                else:
                    num = nums2[idx2]
                    idx2 += 1
            elif idx1 < len(nums1):
                num = nums1[idx1]
                idx1 += 1
            elif idx2 < len(nums2):
                num = nums2[idx2]
                idx2 += 1
            arr.append(num)
            idx += 1
        if (len(nums1) + len(nums2)) % 2 == 1:
            return arr[-1]
        else:
            return (arr[-2] + arr[-1]) / 2