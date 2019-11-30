package com.ywh.problem.leetcode.medium;

import java.util.Random;

/**
 * 用 rand7() 实现 rand10()
 * [数学] [随机]
 *
 * @author ywh
 * @since 30/11/2019
 */
public class LeetCode470 {

    private final Random RANDOM = new Random();

    /**
     * 生成 1 ~ 7 之间的随机数
     *
     * @return
     */
    private int rand7() {
        return RANDOM.nextInt(7) + 1;
    }

    /**
     * 如果 x > y，则一定可以用 randX（等概率生成 1 ~ x 随机数）实现 randY（等概率生成 1 ~ y 随机数）
     * 比如用 rand10 生成 rand7，则生成 1（生成其他同理）的概率：
     * P(x = 1) = 1/10 + 3/30 * 1/10 + (3/10)^2 * 1/10 + ... (3/10)^n * 1/10
     *              （等比数列，即第 1 次取到 1、第 2 次取到 1...第 n 次取到 1 的概率之和）
     *          = 1/10 * [(1-(3/10)^n)]/(1-3/10)
     *          = 1/10 * 10/7 = 1/7
     * 因此，只需要将 rand7() 映射到一个更大的范围（randN，N > 10），则可以用来生成 rand10
     *
     * @return
     */
    public int rand10() {
        int x = Integer.MAX_VALUE;
        while (x > 10) {
            // 7 * (rand7() - 1) 可等概率生成  [0, 7, 14, 21, 28, 35, 42]
            // 再加上 rand7()，则等概率生成 [1~7, 8~14, 15~21, 22~28, 29~35, 36~42, 43~49]，即 [1, 49]
            x = 7 * (rand7() - 1) + rand7();
        }
        return x;
    }

    public int rand10Opt() {
        int x = Integer.MAX_VALUE;

        // 同上，由于 [1, 49] 之间有 4 个长度为 10 的区间，只需要对 [1, 40] 取到的值 % 10 + 1 即可
        while (x > 40) {
            // rand7() - 1 等概率生成 0 ~ 6，因此 7 * (rand7() - 1) 等概率生成  [0, 7, 14, 21, 28, 35, 42]
            // 再加上 rand7()，则等概率生成 [1~7, 8~14, 15~21, 22~28, 29~35, 36~42, 43~49]，即 [1, 49]
            x = 7 * (rand7() - 1) + rand7();
        }
        return x % 10 + 1;
    }
}
