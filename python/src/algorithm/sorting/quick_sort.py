# coding=utf-8
from typing import List


class QuickSort(object):

    def partition(self, arr: List[int], low: int, high: int) -> int:
        """

        :param arr:
        :param low:
        :param high:
        :return:
        """
        pivot, i, j = arr[low + (high - low) // 2], low, high
        while True:
            while arr[i] < pivot:
                i += 1
            while arr[j] > pivot:
                j -= 1
            if i >= j:
                return j
            arr[i], arr[j] = arr[j], arr[i]
            i += 1
            j -= 1

    def sort(self, arr: List[int], low: int, high: int):
        """

        :param arr:
        :param low:
        :param high:
        :return:
        """
        if low >= high:
            return
        k = self.partition(arr, low, high)
        self.sort(arr, low, k)
        self.sort(arr, k + 1, high)


    def sort(self, arr: List[int]):
        """

        :param arr:
        :return:
        """
        if not arr:
            return

        self.sort(arr, 0, len(arr) - 1)