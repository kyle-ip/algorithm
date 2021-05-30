package com.ywh.problem.leetcode.medium;

/**
 * 到达终点数字
 * TODO 暂时未理解
 * [数学]
 *
 * 在一根无限长的数轴上，你站在 0 的位置。终点在 target 的位置。
 * 每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
 * 返回到达终点需要的最小移动次数。
 * 示例 1:
 *      输入: target = 3
 *      输出: 2
 *      解释:
 *          第一次移动，从 0 到 1 。
 *          第二次移动，从 1 到 3 。
 * 示例 2:
 *      输入: target = 2
 *      输出: 3
 *      解释:
 *          第一次移动，从 0 到 1 。
 *          第二次移动，从 1 到 -1 。
 *          第三次移动，从 -1 到 2 。
 * 注意:
 *      target是在[-10^9, 10^9]范围中的非零整数。
 *
 * @author ywh
 * @since 27/12/2019
 */
public class LeetCode754 {

    /**
     * Time: O(sqrt(target)), Space: O(1)
     *
     * @param target
     * @return
     */
    public int reachNumber(int target) {
        target = Math.abs(target);

        // 假设一开始全部向一个方向走，直到超出 target（此时最接近终点，对于超出部分，需要 delta/2 抵消）
        int k = 0;
        while (target > 0) {
            // target = target - 1 - 2 - 3 ...
            target -= ++k;
        }

        // S = 1 + 2 + 3 + ... + k >= target，S - target == delta（超出），令 target = delta。

        // 对于剩余的距离：
        // 如果为偶数，则从 1, 2, ..., k 找到若干个数改为向反方向走即可；
        if (target % 2 == 0) {
            return k;
        }
        // 如果最后一步走的是偶数：
        if (k % 2 == 0) {
            return k + 1;
        }
        // 如果最后一步走的是奇数：
        else {
            return k + 2;
        }
    }

    /**
     * Time: O(n), Space: O(1)
     *
     * @param target
     * @return
     */
    public int reachNumber2(int target) {
        long t = Math.abs((long) target);
        long n = 0, sum = 0;
        while (sum < t || ((sum - t) & 1) == 1) {
            ++n;
            sum += n;
        }
        return (int) n;
    }

    /**
     * Time: O(1), Space: O(1)
     *
     * @param target
     * @return
     */
    public int reachNumberOpt(int target) {
        long t = Math.abs((long) target);
        int n = (int) Math.ceil((Math.sqrt(1 + 8 * t) - 1) / 2);
        long sum = (long) (n + 1) * n / 2;
        long diff = sum - t;
        if ((diff & 1) == 0) {
            return n;
        } else if ((n & 1) == 0) {
            return n + 1;
        } else {
            return n + 2;
        }
    }
}
