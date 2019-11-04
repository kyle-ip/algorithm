package com.ywh.problem.leetcode.easy;

import com.ywh.model.TreeNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("测试判断二叉树是否对称")
class LeetCode101Test {

    private static LeetCode101 solution;

    private static TreeNode root;

    @BeforeAll
    static void init() {
        solution = new LeetCode101();
        root = new TreeNode(
            new TreeNode(
                new TreeNode(4),
                new TreeNode(8),
                2),
            new TreeNode(
                new TreeNode(8),
                new TreeNode(4),
                2),
            1
        );

    }

    @Test
    @DisplayName("[true] 测试递归方法")
    void testIsSymmetricTreeRecursive() {
        assertTrue(solution.isSymmetricTreeRecursive(root));
    }

    @Test
    @DisplayName("[true] 测试迭代方法")
    void isSymmetricTreeIterative() {
        assertTrue(solution.isSymmetricTreeIterative(root));

    }
}