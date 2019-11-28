package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 测试除了自身元素的数组乘积
 * {@link LeetCode238}
 *
 * @author ywh
 * @since 28/11/2019
 */
@DisplayName("测试除了自身元素的数组乘积")
public class LeetCode238Test {

    private static LeetCode238 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode238();
    }

    @ParameterizedTest
    @CsvSource({
        "'1,4,2,8', '64,16,32,8'"
    })
    void testProductExceptSelf(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int[] expected = StringUtil.strToIntArray(arguments.getString(1));
        assert nums != null;
        assertArrayEquals(expected, solution.productExceptSelf(nums));
    }

    @ParameterizedTest
    @CsvSource({
        "'1,4,2,8', '64,16,32,8'"
    })
    void testProductExceptSelf01(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int[] expected = StringUtil.strToIntArray(arguments.getString(1));
        assert nums != null;
        assertArrayEquals(expected, solution.productExceptSelfO1(nums));
    }

    @ParameterizedTest
    @CsvSource({
        "'1,4,2,8', '64,16,32,8'"
    })
    void testProductExceptSelfNotChangeInputO1(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int[] expected = StringUtil.strToIntArray(arguments.getString(1));
        assert nums != null;
        assertArrayEquals(expected, solution.productExceptSelfNotChangeInputO1(nums));
    }
}
