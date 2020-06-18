# coding=utf-8
from typing import List


class InsertionSort(object):

    def sort(self, arr: List[int]):
        """
        Time: O(n^2), Space: O(1)
        :param arr:
        :return:
        """
        if not arr:
            return
        gap = len(arr) >> 1
        while gap > 0:
            for i in range(gap, len(arr)):
                j, cur = i - gap, arr[i]
                while j >= 0 and arr[i] > cur:
                    arr[j + gap] = arr[j]
                    j -= gap
                arr[j + gap] = cur
            gap >>= 1