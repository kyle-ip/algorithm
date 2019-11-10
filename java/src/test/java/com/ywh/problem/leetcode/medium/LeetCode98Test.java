package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;
import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试检验二叉搜索树
 * {@link LeetCode98}
 *
 * @author ywh
 * @since 10/11/2019
 */
@DisplayName("测试检验二叉搜索树")
class LeetCode98Test {

    private static LeetCode98 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode98();
    }

    @ParameterizedTest
    @DisplayName("测试每轮递归求最值解法")
    @CsvSource({
        "'null', 'true'",
        "'1', 'true'",
        "'4,2,6', 'true'",
        "'4,2,6,null,null,5,8', 'true'",
        "'4,2,6,null,null,3,8', 'false'"
    })
    void testIsValidBSTBound(ArgumentsAccessor arguments) {
        TreeNode tress = StringUtil.strToTree(arguments.getString(0));
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isValidBSTBound(tress));
    }

    @ParameterizedTest
    @DisplayName("测试传递边界值解法")
    @CsvSource({
        "'null', 'true'",
        "'1', 'true'",
        "'4,2,6', 'true'",
        "'4,2,6,null,null,5,8', 'true'",
        "'4,2,6,null,null,3,8', 'false'"
    })
    void testIsValidBST(ArgumentsAccessor arguments) {
        TreeNode tress = StringUtil.strToTree(arguments.getString(0));
        boolean expected = arguments.getBoolean(1);
        assertEquals(expected, solution.isValidBST(tress));
    }
}
