package com.ywh.problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加 II
 * [哈希表] [二分查找]
 *
 * @author ywh
 * @since 2020/11/27 22:23
 */
public class LeetCode454 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        // A、B 和 C、D 分成两组。
        Map<Integer, Integer> countAB = new HashMap<>();

        // 遍历 A、B 所有组合，用 countAB 统计 A、B 之和出现的个数。
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int count = 0;

        // 遍历 C、D 所有组合，如果组合 (C, D) 的相反数存在于 countAB 中，表示存在集合 (C, D, A, B)，使得是数字和为 0，累计个数。
        for (int u : C) {
            for (int v : D) {
                count += countAB.getOrDefault(-u -v, 0);
            }
        }
        return count;
    }

}