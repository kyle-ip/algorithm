# coding=utf-8

import sys


class MinStack:
    stack = []
    min_num = sys.maxsize

    def push(self, new_num: int):
        """

        :param new_num:
        :return:
        """
        if new_num < self.min_num:
            self.stack.append(self.min_num)
            self.stack.append(new_num)
            self.min_num = new_num
        else:
            self.stack.append(new_num)

    def pop(self) -> int:
        """

        :return:
        """
        if self.stack[-1] != self.min_num:
            return self.stack.pop()
        else:
            ret = self.stack.pop()
            self.min_num = self.stack.pop()
            return ret

    def get_min(self) -> int:
        """

        :return:
        """
        return self.min_num


class MinStack2:
    data_stack, min_stack = [], []

    def push(self, new_num: int):
        """

        :param new_num:
        :return:
        """
        if len(self.min_stack) == 0 or new_num <= self.get_min():
            self.min_stack.append(new_num)
        self.data_stack.append(new_num)

    def pop(self) -> int:
        """

        :return:
        """
        num = self.data_stack.pop()
        if num == self.get_min():
            self.min_stack.pop()
        return num

    def get_min(self) -> int:
        """

        :return:
        """
        return self.min_stack[-1]

class MinStack3:
    data_stack, min_stack = [], []

    def push(self, new_num: int):
        """

        :param new_num:
        :return:
        """
        if len(self.min_stack) == 0 or new_num < self.get_min():
            self.min_stack.append(new_num)
        else:
            self.min_stack.append(self.min_stack[-1])
        self.data_stack.append(new_num)

    def pop(self) -> int:
        """

        :return:
        """
        self.min_stack.pop()
        return self.data_stack.pop()

    def get_min(self) -> int:
        """

        :return:
        """
        return self.min_stack[-1]