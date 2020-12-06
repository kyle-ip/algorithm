package com.ywh.problem.leetcode.easy;

import java.util.*;

/**
 * 帕斯卡三角形
 * [数组]
 *
 * @author ywh
 * @since 2/22/2019
 */
public class LeetCode118 {

    /**
     * Time: O(n^2), Space: O(1)
     *
     * @param n
     * @return
     */
    public List<List<Integer>> generatePascalTriangle(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        if (n < 1) {
            return ret;
        }
        // 一共 n 层。
        for (int i = 0; i < n; i++) {
            List<Integer> list = Arrays.asList(new Integer[i + 1]);
            //              j-1  j
            // i-1:     [1] [ ] [ ]
            // i:       [1] ... [x] ... [1]
            //           0  ...  j  ...  i
            list.set(0, 1);
            list.set(i, 1);
            for (int j = 1; j < i; j++) {
                list.set(j, ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
            }
            ret.add(list);
        }
        return ret;
    }

    /**
     * FIXME 错误
     * Time: O(n^2), Space: O(1)
     *
     * @param n
     * @return
     */
    public List<List<Integer>> generatePascalTriangleUsingQueue(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 1) {
            return result;
        }
        Queue<Integer> queue = new LinkedList<>();

        // 放入三角顶尖上的元素
        queue.add(1);

        // 记录上次从队首取出的元素，第一行队首前面没有元素，置为0
        int prev = 0;
        for (int i = 1; i < n + 2; i++) {
            List<Integer> elem = new ArrayList<>();

            // 在队尾加入0隔开每行：
            // 下一行的最后元素与上一行的最后元素相同，所以+0
            // 0带到下一行又可以与下一行的行首元素相加、组成再下一行的行首元素
            queue.add(0);

            // 求第二行 0+2 次计算，求第三行 1+2 次计算...
            for (int j = 0; j < i + 2; j++) {

                // 取出队首元素，与上次的队首元素相加，放入队尾
                int value = queue.poll();
                queue.add(value + prev);
                prev = value;
                elem.add(value);
            }
            // 忽略加入分隔每行用的0
            elem.remove(elem.size() - 1);
            result.add(elem);
        }
        return result;
    }
}
