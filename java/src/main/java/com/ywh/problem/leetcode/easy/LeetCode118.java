package com.ywh.problem.leetcode.easy;

import java.util.*;

/**
 * 杨辉三角
 * [数组]
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 示例:
 *      输入: 5
 *      输出:
 *      [
 *           [1],
 *          [1,1],
 *         [1,2,1],
 *        [1,3,3,1],
 *       [1,4,6,4,1]
 *      ]
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
     * FIXME 错误，参考《数据结构》（严蔚敏）
     * Time: O(n^2), Space: O(1)
     *
     * @param n
     * @return
     */
    public List<List<Integer>> generatePascalTriangleQueue(int n) {
        List<List<Integer>> ret = new ArrayList<>();
        if (n < 1) {
            return ret;
        }
        Queue<Integer> queue = new LinkedList<>();

        // 放入三角顶尖上的元素
        queue.add(1);

        // 记录上次从队首取出的元素，第一行队首前面没有元素，置为 0
        int prev = 0;
        for (int i = 1; i < n + 2; i++) {
            List<Integer> elem = new ArrayList<>();

            // 在队尾加入 0 隔开每行：
            // 下一行的最后元素与上一行的最后元素相同，所以 +0
            // 0 带到下一行又可以与下一行的行首元素相加、组成再下一行的行首元素
            queue.add(0);

            // 求第二行 0+2 次计算，求第三行 1+2 次计算...
            for (int j = 0; j < i + 2; j++) {

                // 取出队首元素，与上次的队首元素相加，放入队尾
                int value = queue.poll();
                queue.add(value + prev);
                prev = value;
                elem.add(value);
            }
            // 忽略加入分隔每行用的 0
            elem.remove(elem.size() - 1);
            ret.add(elem);
        }
        return ret;
    }
}
