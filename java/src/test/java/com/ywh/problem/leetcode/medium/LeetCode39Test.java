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
 * 测试求和为给定值的组合
 * {@link LeetCode39}
 *
 * @author ywh
 * @since 29/11/2019
 */
@DisplayName("测试求和为给定值的组合")
public class LeetCode39Test {

    private static LeetCode39 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode39();
    }


    @ParameterizedTest
    @DisplayName("测试回溯（排序优化）解法")
    @CsvSource({
        "'4,2,8', 6, '4,2/2,2,2'",
        "'4,2,8', 1, ''"
    })
    void testCombinationSumSort(ArgumentsAccessor arguments) {
        int[] nums = StringUtil.strToIntArray(arguments.getString(0));
        int target = arguments.getInteger(1);
        List<List<Integer>> expected = StringUtil.strTo2DimIntList(arguments.getString(2));
        assert2DimIntListEquals(expected, solution.combinationSumSort(nums, target));
    }
}
