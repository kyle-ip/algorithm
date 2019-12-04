package com.ywh.problem.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 两地调度
 * [贪心]
 *
 * @author ywh
 * @since 03/12/2019
 */
public class LeetCode1029 {

    /**
     * Time: O(n*log(n)), Space: O(n)
     *
     * @param costs
     * @return
     */
    public static int twoCitySchedCost(int[][] costs) {

        // 先假设所有人都去 A 城，把数组根据每个人去 A 城和去 B 城的成本之差排序（排在前面的表示去 A 城成本较低、排在后面的表示去 B 城成本较低）
        Arrays.sort(costs, Comparator.comparingInt(o -> (o[0] - o[1])));
        int sum = 0, n = costs.length / 2;

        // 前一半选择去 A 城、后一半选择去 B 城
        // 比如 [[10,20],[30,200],[400,50],[30,20]]，对于第一个差值为 -10（较小，会被排在前面），表示去 A 城的成本比去 B 城高 -10（低 10），因此选择去 A 城
        for (int i = 0; i < n; ++i) {
            sum += costs[i][0] + costs[i + n][1];
        }
        return sum;
    }

}

