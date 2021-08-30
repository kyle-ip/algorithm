package com.ywh.problem.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 按权重随机选择
 * [二分查找] [随机]
 *
 * 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），
 * 请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），
 * 而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 * 示例 1：
 *      输入：
 *      ["Solution","pickIndex"]
 *      [[[1]],[]]
 *      输出：
 *      [null,0]
 *      解释：
 *      Solution solution = new Solution([1]);
 *      solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 * 示例 2：
 *      输入：
 *      ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 *      [[[1,3]],[],[],[],[],[]]
 *      输出：
 *      [null,1,1,1,1,0]
 *      解释：
 *      Solution solution = new Solution([1, 3]);
 *      solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 *      solution.pickIndex(); // 返回 1
 *      solution.pickIndex(); // 返回 1
 *      solution.pickIndex(); // 返回 1
 *      solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 *      [null,1,1,1,1,0]
 *      [null,1,1,1,1,1]
 *      [null,1,1,1,0,0]
 *      [null,1,1,1,0,1]
 *      [null,1,0,1,0,0]
 *      ......
 *      诸若此类。
 * 
 * @author ywh
 * @since 4/26/2021
 */
public class LeetCode528 {

    /**
     * 前缀和
     */
    List<Integer> prefixSum = new ArrayList<>();

    /**
     * 总和
     */
    private int sum = 0;

    private final Random rand = new Random();

    /**
     *
     * @param w
     */
    public LeetCode528(int[] w) {
        for (int x : w) {
            sum += x;
            prefixSum.add(sum);
        }
    }

    /**
     *
     * @return
     */
    public int pickIndex() {
        int target = rand.nextInt(sum), low = 0, high = prefixSum.size() - 1;
        while (low != high) {
            int mid = low + (high - low) / 2;
            if (target >= prefixSum.get(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
