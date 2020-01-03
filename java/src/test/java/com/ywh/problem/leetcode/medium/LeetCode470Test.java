package com.ywh.problem.leetcode.medium;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 测试用 rand7() 实现 rand10()
 * {@link LeetCode470}
 *
 * @author ywh
 * @since 19/11/2019
 */
@DisplayName("测试用 rand7() 实现 rand10()")
public class LeetCode470Test {

    private static LeetCode470 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode470();
    }

    @Test
    void testRand10() {
        int[] count = new int[11];

        // 生成 100 万次，每次把生成的数放置在 1 ~ 10 的区间中，统计数量
        for (int i = 0; i < 10_000_000; ++i) {
            ++count[solution.rand10()];
        }

        // 统计 1 ~ 10 中每个数出现的频率，如果频率极差达到 10% 以上，表示验证失败
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 1; i <= 10; ++i) {
            max = Math.max(max, count[i]);
            min = Math.min(min, count[i]);
        }
//        double delta = (double) (max - min) / min;        // delta < 0.1
        Assert.assertTrue("Oops, you failed", max - min < 1_000_000);
    }

    @Test
    void testRand10Opt() {
        int[] count = new int[11];

        for (int i = 0; i < 10_000_000; ++i) {
            ++count[solution.rand10Opt()];
        }

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 1; i <= 10; ++i) {
            max = Math.max(max, count[i]);
            min = Math.min(min, count[i]);
        }
        double delta = (double) (max - min) / min;
        Assert.assertTrue("Oops, you failed", max - min < 1_000_000);
    }
}
