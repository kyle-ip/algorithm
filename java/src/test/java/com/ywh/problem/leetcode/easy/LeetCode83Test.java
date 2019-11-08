package com.ywh.problem.leetcode.easy;

import com.ywh.util.*;
import com.ywh.model.ListNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("测试有序链表去重")
class LeetCode83Test {

    private static LeetCode83 solution;

    private static ListNode head;

    @BeforeAll
    static void init() {
        solution = new LeetCode83();
        head = new ListNode(
            1,
            new ListNode(
                2,
                new ListNode(
                    2,
                    new ListNode(
                        2,
                        new ListNode(
                            3,
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
    void testRemoveDuplicatesInSortedList() {
        assertTrue(
            LinkedListUtil.hasDuplicatedNode(head)
        );

        assertFalse(
            LinkedListUtil.hasDuplicatedNode(
                solution.removeDuplicatesInSortedList(head)
            )
        );
    }
}