# coding=utf-8
from typing import List, Dict


class Solution:
    """
    设计一个类求和为给定值的两个数
    """

    class TwoSum:

        def __init__(self):
            """
            Initialize your data structure here.
            """
            self.data, self.sum_set = [], set()

        def add(self, number: int) -> None:
            """
            Add the number to an internal data structure..
            """
            for num in self.data:
                self.sum_set.add(num + number)

            self.data.append(number)

        def find(self, value: int) -> bool:
            """
            Find if there exists any pair of numbers which sum is equal to the value.
            """
            if len(self.data) < 2:
                return False
            return value in self.sum_set