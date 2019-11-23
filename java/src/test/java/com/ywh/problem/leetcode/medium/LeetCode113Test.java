package com.ywh.problem.leetcode.medium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 测试二叉树中和为给定值的路径
 * {@link LeetCode113}
 *
 * @author ywh
 * @since 10/11/2019
 */
@DisplayName("测试二叉树中和为给定值的路径")
class LeetCode113Test {

    private static LeetCode113 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode113();
    }

    @ParameterizedTest
    @DisplayName("测试递归解法")
    @CsvSource({
        "''"
    })
    void testIsSameTreeRecursive(ArgumentsAccessor arguments) {

    }

    @ParameterizedTest
    @DisplayName("测试迭代解法")
    @CsvSource({
    })
    void testIsSameTreeIterative(ArgumentsAccessor arguments) {
    }

}
