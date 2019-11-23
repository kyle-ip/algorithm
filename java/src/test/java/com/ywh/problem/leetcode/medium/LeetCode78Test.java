package com.ywh.problem.leetcode.medium;

import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.ywh.util.AssertUtil.assert2DimIntListEquals;

/**
 * 测试数组的子集
 * {@link LeetCode78}
 *
 * @author ywh
 * @since 23/11/2019
 */
@DisplayName("测试数组的子集")
public class LeetCode78Test {

    private static LeetCode78 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode78();
    }

    @ParameterizedTest
    @DisplayName("测试回溯解法")
    @CsvSource({
        "'1', '/1'",
        "'1,2,4', '/1/2/4/1,2/1,4/2,4/1,2,4'",
    })
    void testSubsetsRecursive(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        List<List<Integer>> expected = StringUtil.strTo2DimIntList(arguments.getString(1));
        assert2DimIntListEquals(expected, solution.subsetsRecursive(nums));
    }

    @ParameterizedTest
    @DisplayName("测试位运算解法")
    @CsvSource({
        "'1', '/1'",
        "'1,2,4', '/1/2/4/1,2/1,4/2,4/1,2,4'",
    })
    void testSubsetsBit(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        List<List<Integer>> expected = StringUtil.strTo2DimIntList(arguments.getString(1));
        assert2DimIntListEquals(expected, solution.subsetsBit(nums));
    }
}
