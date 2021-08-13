package com.ywh.problem.leetcode.hard;

/**
 * 字典序的第 K 小数字
 * [字典树]
 *
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 * 注意：1 ≤ k ≤ n ≤ 10^9。
 * 示例 :
 *      输入:
 *      n: 13   k: 2
 *      输出:
 *      10
 * 解释:
 *      字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 *
 * @author ywh
 * @since 26/02/2021
 */
public class LeetCode440 {

    /**
     * 十叉树解法
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        long num = 1;
        // p 用于计数，num 用于计算结果。
        // 当 p == k，num 即为第 k 小的数字。
        for (long p = 1; p < k; ) {
            // （num 从 1 开始）获取以 num 为前缀、最大不超过 n 的值的个数。
            long cnt = getPrefixNodeCount(num, n);
            // 下移：当前当前位置 p，加上以 num 为前缀的值的个数超过 k，表示第 k 个数在当前前缀下。
            // num * 10（在树的下一层寻找），p 后移 1 位。
            if (p + cnt > k) {
                num *= 10;
                p++;
            }
            // 右移：否则把指针指向下一个前缀（跳过 cnt 个数）。
            else {
                num++;
                p += cnt;
            }
        }
        return (int) num;
    }

    /**
     * 获取前缀 prefix 下所有子节点数，最多不超过 n 个。
     *
     * @param prefix    前缀
     * @param n         上界
     * @return
     */
    private long getPrefixNodeCount(long prefix, long n) {
        long cnt = 0;
        for (long cur = prefix, next = prefix + 1; cur <= n; cur *= 10, next *= 10) {
            // 下一个前缀的起点减去当前前缀的起点。
            // 当 next 的值大于上界时，以该前缀为根节点的十叉树就不是满十叉树，因此取其与上界 +1 的较小者。
            cnt += Math.min(next, n + 1) - cur;

            // 每轮循环 *10，表示以 prefix 为前缀的子节点增加 10 个，十叉树增加一层。
            // 比如 prefix == 3、next == 4：
            // 该层累加 4 - 3 == 1；
            // 下一层变成 30、40，累加 40 - 30 == 10；
            // 再下一次就变成 300、400，累加 400 - 300 == 100...
        }
        return cnt;
    }
}
