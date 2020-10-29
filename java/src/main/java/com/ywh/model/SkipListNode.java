package com.ywh.model;

import com.ywh.ds.SkipList;

/**
 * 跳表节点
 *
 * @author ywh
 * @since 2020/10/29/029
 */
public class SkipListNode {

    /**
     * 晋升概率
     */
    public static final float SKIPLIST_P = 0.5f;

    /**
     * 最大层次
     */
    public static final int MAX_LEVEL = 16;

    public int data = -1;

    public SkipListNode[] forwards = new SkipListNode[MAX_LEVEL];

    public int maxLevel = 0;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ data: ");
        builder.append(data);
        builder.append("; levels: ");
        builder.append(maxLevel);
        builder.append(" }");

        return builder.toString();
    }
}
