package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试连续子序列的最大乘积
 * {@link LeetCode152}
 *
 * @author ywh
 * @since 08/11/2019
 */
@DisplayName("测试连续子序列的最大乘积")
class LeetCode152Test {
    private static LeetCode152 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode152();
    }

    @ParameterizedTest
    @DisplayName("测试连续子序列的最大乘积")
    @CsvSource({
        "'null', '0'",
        "'[]', '0'",
        "'2,3,-2,4,0,2,-1', '6'",
        "'-2,3,-2,4,2,-1', '96'",
        "'2,3,-2,4,2,-1', '96'",
        "'2,3,-2,4,2', '8'",
        "'8,1,-2,4,-1', '64'"
    })
    void testIsPalindromeString(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int expect = arguments.getInteger(1);
        assertEquals(expect, solution.maxProductOfSubArray(nums));
    }

}
