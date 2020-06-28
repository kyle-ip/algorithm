# coding=utf-8

import sys
from typing import List


class Solution:
    
    def sort_stack_by_stack(self, stack: List[int]):
        """

        :param stack:
        :return:
        """
        help = []
        while len(stack) > 0:
            cur = stack.pop()
            while len(help) > 0 and help[-1] < cur:
                stack.append(help.pop())
            help.append(cur)
        while len(help) > 0:
            stack.append(help.pop())
