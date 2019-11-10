package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;
import com.ywh.problem.leetcode.easy.LeetCode100;
import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试判断二叉树是否相同
 * {@link LeetCode100}
 *
 * @author ywh
 * @since 10/11/2019
 */
@DisplayName("测试判断二叉树是否相同")
class LeetCode100Test {

    private static LeetCode100 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode100();
    }

    @ParameterizedTest
    @DisplayName("测试递归解法")
    @CsvSource({
        "'null', 'null', 'true'",
        "'4,2,6,null,null,5,8', '4,2,6,null,null,5,8', 'true'",
        "'4,2,6,null,null,3,8', '4,2,6,null,null,5,9', 'false'"
    })
    void testIsSameTreeRecursive(ArgumentsAccessor arguments) {
        TreeNode t1 = StringUtil.strToTree(arguments.getString(0));
        TreeNode t2 = StringUtil.strToTree(arguments.getString(1));
        boolean expect = arguments.getBoolean(2);
        assertEquals(expect, solution.isSameTreeRecursive(t1, t2));
    }

    @ParameterizedTest
    @DisplayName("测试迭代解法")
    @CsvSource({
        "'null', 'null', 'true'",
        "'4,2,6,null,null,5,8', '4,2,6,null,null,5,8', 'true'",
        "'4,2,6,null,null,3,8', '4,2,6,null,null,5,9', 'false'"
    })
    void testIsSameTreeIterative(ArgumentsAccessor arguments) {
        TreeNode t1 = StringUtil.strToTree(arguments.getString(0));
        TreeNode t2 = StringUtil.strToTree(arguments.getString(1));
        boolean expect = arguments.getBoolean(2);
        assertEquals(expect, solution.isSameTreeIterative(t1, t2));
    }

}
