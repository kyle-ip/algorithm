# coding=utf-8
from typing import List


class BubbleSort(object):

    def sort(self, arr: List[int]):
        """
        Time: O(n^2), Space: O(1)
        :param arr:
        :return:
        """
        if not arr:
            return
        left, right = 0, len(arr) - 1
        while left < right:
            swapped = False

            for i in range(left, right):
                if arr[i] > arr[i + 1]:
                    arr[i], arr[i + 1] = arr[i + 1], arr[i]
                    swapped = True
            right -= 1
            for i in range(right, left, -1):
                if arr[i - 1] > arr[i]:
                    arr[i - 1], arr[i] = arr[i], arr[i - 1]
                    swapped = True
            left += 1
            if not swapped:
                return
