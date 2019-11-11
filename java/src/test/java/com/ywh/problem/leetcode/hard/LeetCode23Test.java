package com.ywh.problem.leetcode.hard;

import com.ywh.model.ListNode;
import com.ywh.util.LinkedListUtil;
import com.ywh.util.StringUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试合并 K 个有序链表
 * {@link LeetCode23}
 *
 * @author ywh
 * @since 11/11/2019
 */
@DisplayName("测试合并 K 个有序链表")
class LeetCode23Test {

    private static LeetCode23 solution;

    @BeforeAll
    static void init() {
        solution = new LeetCode23();
    }

    @ParameterizedTest
    @DisplayName("测试递归解法")
    @CsvSource({
        "'1,2,4/1,4,8/0,2', '0,1,1,2,2,4,4,8'",
        "'1,2,4/null/0,2', '0,1,2,2,4'",
        "null, null"
    })
    void testMergeKSortedListsOneByOne(ArgumentsAccessor arguments) {
        List<ListNode> listNodeList = StringUtil
            .strToStrList(arguments.getString(0))
            .stream()
            .map(LinkedListUtil::strToList)
            .collect(Collectors.toList());
        ListNode[] listNodes = new ListNode[listNodeList.size()];
        listNodeList.toArray(listNodes);
        ListNode expected = LinkedListUtil.strToList(arguments.getString(1));
        assertEquals(expected, solution.mergeKSortedListsOneByOne(listNodes));
    }

    @ParameterizedTest
    @DisplayName("测试分治解法")
    @CsvSource({
        "'1,2,4/1,4,8/0,2', '0,1,1,2,2,4,4,8'",
        "'1,2,4/null/0,2', '0,1,2,2,4'",
        "null, null"
    })
    void testMergeKSortedListsDivideConquer(ArgumentsAccessor arguments) {
        List<ListNode> listNodeList = StringUtil
            .strToStrList(arguments.getString(0))
            .stream()
            .map(LinkedListUtil::strToList)
            .collect(Collectors.toList());
        ListNode[] listNodes = new ListNode[listNodeList.size()];
        listNodeList.toArray(listNodes);
        ListNode expected = LinkedListUtil.strToList(arguments.getString(1));
        assertEquals(expected, solution.mergeKSortedListsDivideConquer(listNodes));
    }

    @ParameterizedTest
    @DisplayName("测试最小堆解法")
    @CsvSource({
        "'1,2,4/1,4,8/0,2', '0,1,1,2,2,4,4,8'",
        "'1,2,4/null/0,2', '0,1,2,2,4'",
        "null, null"
    })
    void testMergeKSortedListsMinHeap(ArgumentsAccessor arguments) {
        List<ListNode> listNodeList = StringUtil
            .strToStrList(arguments.getString(0))
            .stream()
            .map(LinkedListUtil::strToList)
            .collect(Collectors.toList());
        ListNode[] listNodes = new ListNode[listNodeList.size()];
        listNodeList.toArray(listNodes);
        ListNode expected = LinkedListUtil.strToList(arguments.getString(1));
        assertEquals(expected, solution.mergeKSortedListsMinHeap(listNodes));
    }
}
