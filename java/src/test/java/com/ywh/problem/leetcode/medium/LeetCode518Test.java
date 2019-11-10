package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试硬币面值组合问题
 * {@link LeetCode518}
 *
 * @author ywh
 * @since 2/13/2019
 */
@DisplayName("测试硬币面值组合问题")
class LeetCode518Test {

    private static LeetCode518 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode518();
    }

    @ParameterizedTest
    @DisplayName("测试递归解法")
    @CsvSource({
        "1, '1,2,5', 1",
        "2, '1,2,5', 2",
        "3, '1,2,5', 2",
        "4, '1,2,5', 3",
        "5, '1,2,5', 4",
        "6, '5', 0",
        "6, '1', 1",
        "4, '1, 2', 3",
    })
    void testNumberOfCoinCombinationRecursive(ArgumentsAccessor arguments) {
        int sum = arguments.getInteger(0);
        int[] coins = StringUtil.strToIntArray(arguments.getString(1));
        int expected = arguments.getInteger(2);
        assertEquals(expected, solution.numberOfCoinCombinationRecursive(sum, coins));
    }

    @ParameterizedTest
    @DisplayName("测试动态规划解法")
    @CsvSource({
        "1, '1,2,5', 1",
        "2, '1,2,5', 2",
        "3, '1,2,5', 2",
        "4, '1,2,5', 3",
        "5, '1,2,5', 4",
        "6, '5', 0",
        "6, '1', 1",
        "4, '1, 2', 3",
    })
    void testNumberOfCoinCombinationDP(ArgumentsAccessor arguments) {
        int sum = arguments.getInteger(0);
        int[] coins = StringUtil.strToIntArray(arguments.getString(1));
        int expected = arguments.getInteger(2);
        assert coins != null;
        assertEquals(expected, solution.numberOfCoinCombinationDP(sum, coins));
    }

    @ParameterizedTest
    @DisplayName("测试动态规划解法（优化存储空间）")
    @CsvSource({
        "1, '1,2,5', 1",
        "2, '1,2,5', 2",
        "3, '1,2,5', 2",
        "4, '1,2,5', 3",
        "5, '1,2,5', 4",
        "6, '5', 0",
        "6, '1', 1",
        "4, '1, 2', 3",
    })
    void testNumberOfCoinCombinationDPOsum(ArgumentsAccessor arguments) {
        int sum = arguments.getInteger(0);
        int[] coins = StringUtil.strToIntArray(arguments.getString(1));
        int expected = arguments.getInteger(2);
        assert coins != null;
        assertEquals(expected, solution.numberOfCoinCombinationDPOsum(sum, coins));
    }
}