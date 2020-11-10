package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;
import com.ywh.util.LinkedListUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 测试判断单链表是否为回文链表
 * {@link LeetCode234}
 *
 * @author ywh
 * @since 2/13/2019
 */
@DisplayName("测试判断单链表是否为回文链表")
class LeetCode234Test {

    private static LeetCode234 solution;

    private static ListNode head;

    @BeforeAll
    static void init() {
        solution = new LeetCode234();
        head = LinkedListUtil.strToList("4,2,3,3,3,2,4");
    }

    @DisplayName("测试使用辅助栈解法")
    @Test
    void isPalindromeUsingStack() {
        assertTrue(solution.isPalindromeUsingStack(head));
    }

    @DisplayName("测试反转链表解法")
    @Test
    void isPalindromeReverseList() {
        assertTrue(solution.isPalindromeReverseList(head));
    }
}