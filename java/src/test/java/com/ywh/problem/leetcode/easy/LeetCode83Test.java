package com.ywh.problem.leetcode.easy;

import com.ywh.ds.list.ListNode;
import com.ywh.util.LinkedListUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试有序链表去重
 * {@link LeetCode83}
 *
 * @author ywh
 * @since 2/16/2019
 */
@DisplayName("测试有序链表去重")
class LeetCode83Test {

    private static LeetCode83 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode83();
    }

    @ParameterizedTest
    @DisplayName("测试有序链表去重")
    @CsvSource({
        "'1,2,2,2,3,4', '1,2,3,4'",
        "'1,2,3,4', '1,2,3,4'",
        "'', ''",
        "'1,1,1,2,3,4,4', '1,2,3,4'",
    })
    void testRemoveDuplicatesInSortedList(ArgumentsAccessor arguments) {
        ListNode l =  LinkedListUtil.strToList(arguments.getString(0));
        ListNode expected = LinkedListUtil.strToList(arguments.getString(1));
        assertEquals(expected, solution.removeDuplicatesInSortedList(l));
    }
}