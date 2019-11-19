package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode367;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试验证完全平方数
 * {@link LeetCode367}
 *
 * @author ywh
 * @since 19/11/2019
 */
@DisplayName("测试验证完全平方数")
public class LeetCode367Test {

    private static LeetCode367 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode367();
    }

    @ParameterizedTest
    @DisplayName("测试牛顿法")
    @CsvSource({
        "0, true",
        "1, true",
        "2, false",
        "99, false",
        "144, true"
    })
    void testIsPerfectSquareNewton(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isPerfectSquareNewton(num));
    }

    @ParameterizedTest
    @DisplayName("测试二分法")
    @CsvSource({
        "0, true",
        "1, true",
        "2, false",
        "99, false",
        "144, true"
    })
    void testIsPerfectSquareBinarySearch(ArgumentsAccessor arguments) {
        int num = arguments.getInteger(0);
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isPerfectSquareBinarySearch(num));
    }
}
