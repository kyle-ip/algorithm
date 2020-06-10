# coding=utf-8
from typing import List


class InsertionSort(object):

    def sort(self, arr: List[int]):
        """

        :param arr:
        :return:
        """
        if not arr:
            return
        for i in range(1, len(arr)):
            j, cur = i - 1, arr[i]
            while j >= 0 and arr[j] > cur:
                arr[j + 1] = arr[j]
                j -= 1
            arr[j + 1] = cur
