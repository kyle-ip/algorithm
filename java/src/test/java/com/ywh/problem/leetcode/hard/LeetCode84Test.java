package com.ywh.problem.leetcode.hard;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试直方图中的最大矩形
 *
 * {@link LeetCode84}
 *
 * @author ywh
 * @since 16/11/2019
 */
@DisplayName("测试直方图中的最大矩形")
public class LeetCode84Test {

    private static LeetCode84 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode84();
    }

    @ParameterizedTest
    @DisplayName("测试两边扩展解法")
    @CsvSource({
        "'2', 2",
        "'2,2,2,2', 8",
        "'1,2,4,8', 8",
        "'2,1,3,4,1', 6",
        "'2,1,4,6,2,4', 8",
        "'2,1,0,6,2,4', 6",
        "'2,1,5,3,2,3', 8"
    })
    void testLargestRectangleAreaExpand(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.largestRectangleAreaExpand(nums));
    }

    @ParameterizedTest
    @DisplayName("测试单调栈（数组实现）解法")
    @CsvSource({
        "'2', 2",
        "'2,2,2,2', 8",
        "'1,2,4,8', 8",
        "'2,1,3,4,1', 6",
        "'2,1,4,6,2,4', 8",
        "'2,1,0,6,2,4', 6",
        "'2,1,5,3,2,3', 8"
    })
    void testLargestRectangleAreaArray(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.largestRectangleAreaArray(nums));
    }

    @ParameterizedTest
    @DisplayName("测试单调栈解法")
    @CsvSource({
        "'2', 2",
        "'2,2,2,2', 8",
        "'1,2,4,8', 8",
        "'2,1,3,4,1', 6",
        "'2,1,4,6,2,4', 8",
        "'2,1,0,6,2,4', 6",
        "'2,1,5,3,2,3', 8"
    })
    void testLargestRectangleAreaStack(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int expected = arguments.getInteger(1);
        assertEquals(expected, solution.largestRectangleAreaStack(nums));
    }
}
