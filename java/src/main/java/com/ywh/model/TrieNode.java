package com.ywh.model;

/**
 * 前缀树节点
 *
 * @author ywh
 * @since 25/11/2019
 */
public class TrieNode {
    public boolean endOfWord;

    public TrieNode[] children;

    public TrieNode() {
        this.endOfWord = false;
        children = new TrieNode[26];
    }
}
