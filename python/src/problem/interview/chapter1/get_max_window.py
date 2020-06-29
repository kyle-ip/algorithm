# coding=utf-8

from typing import List


class Solution:

    def get_max_window(self, arr: List[int], w: int) -> List[int]:
        """

        :param arr:
        :param w:
        :return:
        """
        if not arr or w < 1 or len(arr) < w:
            return []

        # 两端队列存放元素下标，从队首到队尾递减
        qmax, ret = [], []

        # 存放结果
        for i in range(len(arr)):

            # 队尾元素比当前元素小，在窗口已经不可能成为最大值，故弹出
            while len(qmax) > 0 and arr[qmax[-1]] <= arr[i]:
                qmax.pop()
            qmax.append(i)

            # 过期（在窗口左边外），弹出队首
            if qmax[0] == i - w:
                qmax.pop(0)

            # 已经过第一个窗口，保存结果（队首）
            if i >= w - 1:
                ret.append(qmax[0])
        return ret
