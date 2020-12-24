package com.ywh.problem.leetcode.medium;

/**
 * 加油站
 * [贪心]
 *
 * @author ywh
 * @since 29/12/2019
 */
public class LeetCode134 {

    /**
     * 暴力解法
     *
     * Time: O(n^2), Space: O(1)
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitBruteForce(int[] gas, int[] cost) {
        int n = gas.length, tank;

        // 逐个加油站开始尝试
        for (int i = 0; i < n; i++) {
            tank = 0;
            // 公路是环形的，到达边界后要返回 0，因此求到达的加油站编号：j % n（i -> i + n - 1）
            for (int j = i; j < i + n; j++) {
                int k = j % n;
                tank += gas[k] - cost[k];
                if (tank < 0) {
                    break;
                }
            }
            // 绕过一圈还有汽油，返回开始位置的下标
            if (tank >= 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 暴力解法（跳过不必要的加油站）
     * 假设从 i 开始出发，可以走到 j、但不能走到 j + 1，此时如使用原解法要从 i + 1 开始尝试；
     * 但实际上此时从 i ~ j 的任何一点出发都无法走到 j + 1，都不能作为起点，因此可以跳过 i + 1 ~ j 的所有加油站、从 j + 1 开始
     *
     * Time: O(n), Space: O(1)
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitSkip(int[] gas, int[] cost) {
        int n = gas.length, j = 0;
        for (int i = 0; i < n; i = j + 1) {
            int tank = 0;
            for (j = i; j < i + n; j++) {
                int k = j % n;
                tank = gas[k] - cost[k];
                if (tank < 0) {
                    break;
                }
            }
            if (tank >= 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 贪心解法
     *
     * Time: O(n), Space: O(1)
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuitGreedy(int[] gas, int[] cost) {
        // 分别记录整个过程的加油量和耗油量、到达当前加油站时的剩余油量、出发的位置（一次遍历中更新 start）
        int total = 0, tank = 0, start = 0, n = gas.length;
        for (int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            tank += gas[i] - cost[i];

            // 如果在某个点剩余油量小于 0，表示前面的位置都不可能是出发的位置，因此更新开始位置为下一个点，并把剩余油量重置
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        // 如果加油站的油量之和，大于等于消耗的油量之和，那么肯定存在一个出发点，可以绕行公路一周
        return total < 0 ? -1 : start;
    }
}
