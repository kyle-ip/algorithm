package com.ywh.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 无向图节点
 *
 * @author ywh
 * @since 27/11/2019
 */
public class UndirectedGraphNode {

    public int val;

    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        val = x;
        neighbors = new ArrayList<>();
    }
}
