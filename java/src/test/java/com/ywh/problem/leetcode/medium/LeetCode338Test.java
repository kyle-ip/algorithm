package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 测试连续自然数二进制中 1 的个数
 * {@link LeetCode338}
 *
 * @author ywh
 * @since 2/13/2019
 */
@DisplayName("测试连续自然数二进制中 1 的个数")
class LeetCode338Test {

    private static LeetCode338 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode338();
    }

    @ParameterizedTest
    @CsvSource({
            "0, '0'",
            "1, '0,1'",
            "2, '0,1,1'",
            "3, '0,1,1,2'",
            "4, '0,1,1,2,1'",
            "5, '0,1,1,2,1,2'"
    })
    void testCountBits(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        int[] expected = StringUtil.strToIntArray(arguments.getString(1));
        assertArrayEquals(expected, solution.countBits(num));
    }

    @ParameterizedTest
    @CsvSource({
            "0, '0'",
            "1, '0,1'",
            "2, '0,1,1'",
            "3, '0,1,1,2'",
            "4, '0,1,1,2,1'",
            "5, '0,1,1,2,1,2'"
    })
    void testCountBitsOn(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        int[] expected = StringUtil.strToIntArray(arguments.getString(1));
        assertArrayEquals(expected, solution.countBitsOn(num));
    }

    @Test
    public void testCross() {
        for (int i = 0; i < 100; ++i) {
            Assert.assertArrayEquals(solution.countBits(i), solution.countBitsOn(i));
        }
    }
}