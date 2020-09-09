package com.ywh.model;

/**
 * 前缀树节点
 *
 * @author ywh
 * @since 25/11/2019
 */
public class TrieNode {

    /**
     * 当前节点的子节点个数
     */
    public int count;

    /**
     * 标记该节点是否可作为一个单词的结尾
     */
    public boolean endOfWord;

    /**
     * 子节点数组，把 a~z 映射到 [0, 25]
     */
    public TrieNode[] children;

    public TrieNode() {
        this.endOfWord = false;
        children = new TrieNode[26];
        count = 0;
    }
}
