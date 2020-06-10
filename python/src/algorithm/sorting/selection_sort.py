# coding=utf-8
from typing import List


class SelectionSort(object):

    def sort(self, arr: List[int]):
        """

        :param arr:
        :return:
        """
        if not arr:
            return
        for i in range(len(arr)):
            min_idx = i
            for j in range(i + 1, len(arr)):
                if arr[j] < arr[min_idx]:
                    min_idx = j
            arr[min_idx], arr[i] = arr[i], arr[min_idx]

    def sort2(self, arr: List[int]):
        """

        :param arr:
        :return:
        """
        if not arr:
            return
        for i in range(len(arr) - 1, 0, -1):
            max_idx = i
            for j in range(0, i):
                if arr[j] > arr[max_idx]:
                    max_idx = j
            arr[max_idx], arr[i] = arr[i], arr[max_idx]