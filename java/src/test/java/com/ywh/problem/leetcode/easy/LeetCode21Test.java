package com.ywh.problem.leetcode.easy;

import com.ywh.model.ListNode;
import com.ywh.util.LinkedListUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

/**
 * 测试合并两个有序链表
 * {@link LeetCode21}
 *
 * @author ywh
 * @since 2019/11/8/008
 */
@DisplayName("测试合并两个有序链表")
class LeetCode21Test {

    private static LeetCode21 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode21();
    }

    @ParameterizedTest
    @DisplayName("测试合并两个有序链表")
    @CsvSource({
        "'1,4,5,8,10', '2,7,9', '1,2,4,5,7,8,9,10'",
        "'', '1,2,3', '1,2,3'",
        "'','',''",
    })
    void testMergeTwoSortedLists(ArgumentsAccessor arguments) {
        ListNode l1 = LinkedListUtil.strToList(arguments.getString(0));
        ListNode l2 = LinkedListUtil.strToList(arguments.getString(1));
        ListNode expected = LinkedListUtil.strToList(arguments.getString(2));
        assertEquals(expected, solution.mergeTwoSortedLists(l1, l2));
    }
}
