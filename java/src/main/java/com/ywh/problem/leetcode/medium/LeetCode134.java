package com.ywh.problem.leetcode.medium;

/**
 * 加油站
 * [贪心]
 * 
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 说明:
 *      如果题目有解，该答案即为唯一答案。
 *      输入数组均为非空数组，且长度相同。
 *      输入数组中的元素均为非负数。
 * 示例  1:
 *      输入:
 *          gas  = [1,2,3,4,5]
 *          cost = [3,4,5,1,2]
 *          输出: 3
 *      解释:
 *          从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 *          开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 *          开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 *          开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 *          开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 *          开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 *          因此，3 可为起始索引。
 * 示例 2:
 *      输入:
 *          gas  = [2,3,4]
 *          cost = [3,4,3]
 *      输出: -1
 *      解释:
 *          你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 *          我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 *          开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 *          开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 *          你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 *          因此，无论怎样，你都不可能绕环路行驶一周。
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
        // total 为整个过程的加油量和耗油量之差，tank 为到达当前加油站时的剩余油量，start 为出发的位置。
        int total = 0, tank = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            // 如果在某个点剩余油量小于 0，表示前面的位置都不可能是出发的位置，因此更新开始位置、重置剩余油量。
            if (tank < 0) {
                start = i;
                tank = 0;
            }
            total += gas[i] - cost[i];
            tank += gas[i] - cost[i];
        }

        // 如果加油站的油量之和，大于等于消耗的油量之和，那么肯定存在一个出发点，可以绕行公路一周。
        return total < 0 ? -1 : start;
    }
}
