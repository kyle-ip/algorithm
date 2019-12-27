package com.ywh.problem.leetcode.medium;

/**
 * 到达终点数字
 * TODO 暂时未理解
 *
 * [数学]
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

        // S = 1 + 2 + 3 + ... + k >= target，S - target == delta（超出）
        // 假设一开始全部向一个方向走，直到超出 target（此时最接近重点，对于超出部分，需要 delta/2 抵消）
        int k = 0;
        while (target > 0) {
            target -= ++k;
        }

        // 对于剩余的距离：
        // 如果为偶数，则从 1, 2, ..., k 找到若干个数改为负号（向反方向走）即可；
        if (target % 2 == 0) {
            return k;
        }
        // 如果是奇数，则判断之前已走的步数：
        //     如果是偶数，则还需要多走一步，否则多走两步（k + 1 + k%2）
        else if (k % 2 == 0) {
            return k + 1;
        } else {
            return k + 2;
        }
    }
}
