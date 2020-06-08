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
        for i in range(len(arr) - 1, 0, -1):
            for j in range(i):
                if arr[j] > arr[j + 1]:
                    arr[j], arr[j + 1] = arr[j + 1], arr[i]

    def sort2(self, arr: List[int]):
        """
        Time: O(n^2), Space: O(1)
        :param arr:
        :return:
        """
        
        if not arr:
            return
        for i in range(len(arr) - 1, 0, -1):
            swapped = False
            for j in range(i):
                if arr[j] > arr[j + 1]:
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
                    swapped = True
            if not swapped:
                return

    def sort3(self, arr: List[int]):
        """
        Time: O(n^2), Space: O(1)
        :param arr:
        :return:
        """
        if not arr:
            return

        i = len(arr) - 1
        while i > 0:
            new_end = 0
            for j in range(i):
                if arr[j] > arr[j + 1]:
                    arr[j], arr[j + 1] = arr[j + 1], arr[j]
                    new_end = i
            i = new_end