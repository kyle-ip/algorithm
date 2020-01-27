# coding=utf-8
from typing import List


class MyQueue:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.in_stack = []
        self.out_stack = []

    def in_to_out(self) -> None:
        if len(self.out_stack) > 0:
            return
        while len(self.in_stack) > 0:
            self.out_stack.append(self.in_stack.pop())

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self.in_stack.append(x)

    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        self.in_to_out()
        return self.out_stack.pop()

    def peek(self) -> int:
        """
        Get the front element.
        """
        self.in_to_out()
        return self.out_stack[-1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        return len(self.out_stack) == 0 and len(self.in_stack) == 0

