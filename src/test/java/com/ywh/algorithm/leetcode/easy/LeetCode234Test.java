package com.ywh.algorithm.leetcode.easy;

import com.ywh.model.ListNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("测试判断单链表是否为回文链表")
class LeetCode234Test {

    private static LeetCode234 solution;

    private static ListNode head;

    @BeforeAll
    static void init() {
        solution = new LeetCode234();
        head = new ListNode(
            4,
            new ListNode(
                2,
                new ListNode(
                    3,
                    new ListNode(
                        3,
                        new ListNode(
                            2,
                            new ListNode(
                                4
                            )
                        )
                    )
                )
            )
        );
    }


    @Test
    void isPalindromeUsingStack() {
        assertTrue(solution.isPalindromeUsingStack(head));
    }

    @Test
    void isPalindromeReverseList() {
        assertTrue(solution.isPalindromeReverseList(head));
    }
}