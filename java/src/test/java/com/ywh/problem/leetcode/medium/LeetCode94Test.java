package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;
import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试二叉树中序遍历
 * {@link LeetCode94}
 *
 * @author ywh
 * @since 10/11/2019
 */
@DisplayName("测试二叉树中序遍历")
class LeetCode94Test {

    private static LeetCode94 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode94();
    }

    @ParameterizedTest
    @DisplayName("测试递归解法")
    @CsvSource({
        "'', null",
        "'1', '1'",
        "'1,2,3,null,4,null,null,5,null', '2,5,4,1,3'"
    })
    void testInorderTraversalRecursive(ArgumentsAccessor arguments) {
        TreeNode tress = StringUtil.strToTree(arguments.getString(0));
        List<Integer> expected = StringUtil.strToIntList(arguments.getString(1));
        assertEquals(expected, solution.inorderTraversalRecursive(tress));
    }

    @ParameterizedTest
    @DisplayName("测试迭代解法")
    @CsvSource({
        "'', null",
        "'1', '1'",
        "'1,2,3,null,4,null,null,5,null', '2,5,4,1,3'"
    })
    void testInorderTraversalIterative(ArgumentsAccessor arguments) {
        TreeNode tress = StringUtil.strToTree(arguments.getString(0));
        List<Integer> expected = StringUtil.strToIntList(arguments.getString(1));
        assertEquals(expected, solution.inorderTraversalIterative(tress));
    }
}
