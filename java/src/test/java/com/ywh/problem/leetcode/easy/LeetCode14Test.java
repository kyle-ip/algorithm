package com.ywh.problem.leetcode.medium;

import com.ywh.problem.leetcode.easy.LeetCode14;
import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试字符串的最长公共前缀
 * {@link LeetCode14}
 *
 * @author ywh
 * @since 19/11/2019
 */
@DisplayName("测试字符串的最长公共前缀")
public class LeetCode14Test {

    private static LeetCode14 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode14();
    }

    @ParameterizedTest
    @CsvSource({
        "'car/cat/care', 'ca'",
        "'car/care', 'car'",
        "'car/bus', ''",
        "'', ''",
        "'null', ''"
    })
    void testLongestCommonPrefix(ArgumentsAccessor arguments) {
        String[] strs = StringUtil.strToStrArray(arguments.getString(0));
        String expected = arguments.getString(1);
        assertEquals(expected, solution.longestCommonPrefix(strs));
    }
}
