package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试最小硬币组合
 * {@link LeetCode322}
 *
 * @author ywh
 * @since 19/11/2019
 */
@DisplayName("测试最小硬币组合")
public class LeetCode322Test {

    private static LeetCode322 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode322();
    }

    @ParameterizedTest
    @DisplayName("测试动态规划解法（优化存储空间）")
    @CsvSource({
        "'1,2,5', 11, 3",
        "'2', 3, -1",
        "'1,2,5', 11, 3",
        "'2', 3, -1"
    })
    void testMinCoinCombination(ArgumentsAccessor arguments) {
        int[] coins = StringUtil.strToIntArray(arguments.getString(0));
        int sum = arguments.getInteger(1);
        int expected = arguments.getInteger(2);
        assert coins != null;
        assertEquals(expected, solution.minCoinCombination(coins, sum));
    }

    @ParameterizedTest
    @DisplayName("测试动态规划解法（优化存储空间）")
    @CsvSource({
        "'1,2,5', 11, 3",
        "'2', 3, -1",
        "'1,2,5', 11, 3",
        "'2', 3, -1"
    })
    void testMinCoinCombinationOsum(ArgumentsAccessor arguments) {
        int[] coins = StringUtil.strToIntArray(arguments.getString(0));
        int sum = arguments.getInteger(1);
        int expected = arguments.getInteger(2);
        assert coins != null;
        assertEquals(expected, solution.minCoinCombinationOsum(coins, sum));
    }
}
