package com.ywh.problem.leetcode.medium;

import com.ywh.model.TreeNode;
import com.ywh.util.StringUtil;
import com.ywh.util.TreeUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 二叉树中节点的最近公共祖先
 * {@link LeetCode236}
 *
 * @author ywh
 * @since 12/11/2019
 */
@DisplayName("二叉树中节点的最近公共祖先")
public class LeetCode236Test {

    private static LeetCode236 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode236();
    }

    @ParameterizedTest
    @DisplayName("测试辅助路径解法")
    @CsvSource({
        "'1,2,3,4,5,null,6', 4, 5, 2",
        "'1,2,3,4,5,null,6', 5, 6, 1",
        "'1,2,3,4,5,null,6', 1, 6, 1",
        "'1,2,3,4,5,null,6', 2, 2, 2"
    })
    void testLcaWithPath(ArgumentsAccessor arguments) {
        TreeNode root = StringUtil.strToTree(arguments.getString(0));
        TreeNode p = TreeUtil.findTreeNode(root, arguments.getInteger(1));
        TreeNode q = TreeUtil.findTreeNode(root, arguments.getInteger(2));
        TreeNode expected = TreeUtil.findTreeNode(root, arguments.getInteger(3));
        assertEquals(expected, solution.lcaWithPath(root, p, q));
    }

    @ParameterizedTest
    @DisplayName("测试扩展解法")
    @CsvSource({
        "'1,2,3,4,5,null,6', 4, 5, 2",
        "'1,2,3,4,5,null,6', 5, 6, 1",
        "'1,2,3,4,5,null,6', 1, 6, 1",
        "'1,2,3,4,5,null,6', 2, 2, 2"
    })
    void testLcaExtend(ArgumentsAccessor arguments) {
        TreeNode root = StringUtil.strToTree(arguments.getString(0));
        TreeNode p = TreeUtil.findTreeNode(root, arguments.getInteger(1));
        TreeNode q = TreeUtil.findTreeNode(root, arguments.getInteger(2));
        TreeNode expected = TreeUtil.findTreeNode(root, arguments.getInteger(3));
        assertEquals(expected, solution.lcaExtend(root, p, q));
    }
}
