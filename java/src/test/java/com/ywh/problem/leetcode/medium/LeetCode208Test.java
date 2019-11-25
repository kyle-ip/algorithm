package com.ywh.problem.leetcode.medium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 测试实现前缀树
 * {@link LeetCode208}
 *
 * @author ywh
 * @since 25/11/2019
 */
@DisplayName("测试前缀树")
public class LeetCode208Test {

    private static LeetCode208.Trie trie;

    @BeforeAll
    static void init() {
        trie = new LeetCode208.Trie();
    }

    @Test
    @DisplayName("测试前缀树")
    void testGetTwoSumToTarget1() {
        trie.insert("bbc");
        assertTrue(trie.search("bbc"));
        assertFalse(trie.search("b"));
        assertFalse(trie.search("bb"));
        assertFalse(trie.search("bbcd"));
        assertTrue(trie.startsWith("b"));
        assertTrue(trie.startsWith("bb"));
        assertTrue(trie.startsWith("bbc"));
        trie.insert("bb");
        assertTrue(trie.search("bb"));
    }

}
