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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof UndirectedGraphNode)) {
//            return false;
//        }
//        UndirectedGraphNode that = (UndirectedGraphNode) o;
//        return val == that.val && neighbors.equals(that.neighbors);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(val, neighbors);
//    }
}
