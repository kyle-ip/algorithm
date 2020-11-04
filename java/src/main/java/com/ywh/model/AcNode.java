package com.ywh.model;

/**
 * AC 自动机节点（Aho-Corasick）
 *
 * @author ywh
 * @since 2020/11/4/004
 */

public class AcNode {
    public char data;

    /**
     * 字符集 [a-z]
     */
    public AcNode[] children = new AcNode[26];

    /**
     * 结尾字符为 true
     */
    public boolean end = false;

    /**
     * 当 isEndingChar == true 时记录模式串长度。
     */
    public int length = -1;

    /**
     * 失败指针。
     */
    public AcNode fail;

    public AcNode(char data) {
        this.data = data;
    }
}
