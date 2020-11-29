package com.ywh.ds.tree;

import lombok.Data;

/**
 * 线段树节点
 *
 * @author ywh
 * @since 29/11/2020
 */
@Data
public class SegTreeNode {

    /**
     * 区间左端点
     */
    public int start;

    /**
     * 区间右端点
     */
    public int end;

    public SegTreeNode left;

    public SegTreeNode right;

    /**
     * 权值
     */
    public int val;

    /**
     * 延迟更新标记
     */
    public int mark;

    /**
     * @param start
     * @param end
     */
    public SegTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.mark = 0;
    }

    /**
     * 添加标记
     *
     * @param val
     */
    void addMark(int val) {
        this.mark += val;
    }

    /**
     * 清空标记
     */
    void clearMark() {
        this.mark = 0;
    }
}
