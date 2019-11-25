package com.ywh.problem.leetcode.medium;

import com.ywh.model.TrieNode;

/**
 * 实现前缀树
 * [设计] [Trie]
 *
 * @author ywh
 * @since 25/11/2019
 */
public class LeetCode208 {

    /**
     * 只存储小写字母，因此是二十六叉树
     * 每个非根节点存放 26 个子节点组成的数组（非根），以及一个是否单词结尾的标识
     */
    public static class Trie {

        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        /**
         * 插入一个单词（非空字符串、只含小写字母）
         *
         * @param word
         */
        public void insert(String word) {
            TrieNode cur = root;

            // 迭代逐层添加 word 中的字符
            for (int i = 0; i < word.length(); i++) {

                // 把字符映射到 [0, 25] 的数值：int idx = c - 'a';
                // 反映射：char c = (char) (idx + 'a');
                int idx = word.charAt(i) - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                }
                cur = cur.children[idx];
            }
            cur.endOfWord = true;
        }

        /**
         * 搜索一个单词是否存在于前缀树中
         *
         * @param word
         * @return
         */
        public boolean search(String word) {
            TrieNode node = searchEndNode(word);
            return node != null && node.endOfWord;
        }

        /**
         * 判断前缀树中是否存在一个包含给定前缀的单词
         *
         * @param prefix
         * @return
         */
        public boolean startsWith(String prefix) {
            return searchEndNode(prefix) != null;
        }

        /**
         *
         * @param str
         * @return
         */
        private TrieNode searchEndNode(String str) {
            TrieNode cur = root;
            for (int i = 0; i < str.length() && cur != null; i++) {
                int idx = str.charAt(i) - 'a';
                cur = cur.children[idx];
            }
            return cur;
        }
    }

}
