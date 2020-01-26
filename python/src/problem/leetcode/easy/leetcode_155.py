# coding=utf-8


class MinStack:
    """
    带有 min 函数的栈带有 min 函数的栈
    """

    def __init__(self):
        self.min_stack = []
        self.num_stack = []

    def push(self, x: int) -> None:
        if len(self.min_stack) == 0:
            self.min_stack.append(x)
            self.num_stack.append(x)
        else:
            if self.min_stack[-1] > x:
                self.min_stack.append(x)
            else:
                self.min_stack.append(self.min_stack[-1])
            self.num_stack.append(x)

    def pop(self) -> None:
        self.min_stack.pop()
        self.num_stack.pop()

    def top(self) -> int:
        if len(self.num_stack) > 0:
            return self.num_stack[-1]
        return 0

    def getMin(self) -> int:
        if len(self.min_stack) > 0:
            return self.min_stack[-1]
        return 0